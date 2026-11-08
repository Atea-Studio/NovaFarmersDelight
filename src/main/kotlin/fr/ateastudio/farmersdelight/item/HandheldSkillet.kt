package fr.ateastudio.farmersdelight.item

import fr.ateastudio.farmersdelight.block.BlockStateProperties
import fr.ateastudio.farmersdelight.registry.Blocks
import fr.ateastudio.farmersdelight.registry.Sounds
import fr.ateastudio.farmersdelight.tileentity.Skillet
import fr.ateastudio.farmersdelight.tileentity.skilletCookingTime
import fr.ateastudio.farmersdelight.util.Logger
import fr.ateastudio.farmersdelight.util.findCampfireRecipe
import fr.ateastudio.farmersdelight.util.isHeatSourceMember
import fr.ateastudio.farmersdelight.util.isTrayHeatSource
import fr.ateastudio.farmersdelight.util.safeGive
import io.papermc.paper.datacomponent.DataComponentTypes
import net.kyori.adventure.text.Component
import org.bukkit.Fluid
import org.bukkit.NamespacedKey
import org.bukkit.SoundCategory
import org.bukkit.block.Block
import org.bukkit.block.BlockFace
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType
import org.bukkit.util.BoundingBox
import xyz.xenondevs.nova.context.Context
import xyz.xenondevs.nova.context.intention.BlockInteract
import xyz.xenondevs.nova.context.intention.BlockPlace
import xyz.xenondevs.nova.context.intention.ItemUse
import xyz.xenondevs.nova.integration.protection.ProtectionManager
import xyz.xenondevs.nova.util.BlockUtils
import xyz.xenondevs.nova.util.below
import xyz.xenondevs.nova.world.InteractionResult
import xyz.xenondevs.nova.world.block.NovaBlockState
import xyz.xenondevs.nova.world.block.blockType
import xyz.xenondevs.nova.world.block.state.property.DefaultBlockStateProperties
import xyz.xenondevs.nova.world.item.ItemAction
import xyz.xenondevs.nova.world.item.behavior.ItemBehavior
import kotlin.random.Random

private val INGREDIENT_KEY = NamespacedKey("farmersdelight", "skillet_ingredient")
private val COOKING_TIME_KEY = NamespacedKey("farmersdelight", "skillet_cooking_time")
private val PROGRESS_KEY = NamespacedKey("farmersdelight", "skillet_cooking_progress")

/** Width in pixels of the vanilla item bar. */
private const val BAR_WIDTH = 13

/** Long enough never to finish on its own: completion is driven by the stored cooking time. */
private const val USE_DURATION = 72000
private const val STRONG_ATTACK_THRESHOLD = 0.8f

internal data class HandheldCooking(val ingredient: ItemStack, val cookingTime: Int)

internal fun ItemStack.readHandheldCooking(): HandheldCooking? {
    val pdc = persistentDataContainer
    val bytes = pdc.get(INGREDIENT_KEY, PersistentDataType.BYTE_ARRAY) ?: return null
    val cookingTime = pdc.get(COOKING_TIME_KEY, PersistentDataType.INTEGER) ?: return null
    return HandheldCooking(ItemStack.deserializeBytes(bytes), cookingTime)
}

internal fun ItemStack.withHandheldCooking(cooking: HandheldCooking?): ItemStack = clone().apply {
    editPersistentDataContainer { pdc ->
        if (cooking == null) {
            pdc.remove(INGREDIENT_KEY)
            pdc.remove(COOKING_TIME_KEY)
            pdc.remove(PROGRESS_KEY)
        } else {
            pdc.set(INGREDIENT_KEY, PersistentDataType.BYTE_ARRAY, cooking.ingredient.serializeAsBytes())
            pdc.set(COOKING_TIME_KEY, PersistentDataType.INTEGER, cooking.cookingTime)
            pdc.set(PROGRESS_KEY, PersistentDataType.INTEGER, 0)
        }
    }
}

private fun ItemStack.cookingBarProgress(): Int =
    persistentDataContainer.get(PROGRESS_KEY, PersistentDataType.INTEGER) ?: 0

private fun ItemStack.withCookingBarProgress(width: Int): ItemStack = clone().apply {
    editPersistentDataContainer { it.set(PROGRESS_KEY, PersistentDataType.INTEGER, width) }
}

/** Bar width as in the original `getBarWidth`: `round(13 - remaining * 13 / duration)` = `round(13 * elapsed / duration)`. */
internal fun cookingBarWidth(elapsedTicks: Int, cookingTime: Int): Int {
    if (cookingTime <= 0) return BAR_WIDTH
    return Math.round(BAR_WIDTH.toFloat() * elapsedTicks / cookingTime).coerceIn(0, BAR_WIDTH)
}

/** Ticks of held use needed before the stored ingredient is cooked. */
internal fun isHandheldCookingDone(remainingUseTicks: Int, cookingTime: Int): Boolean =
    USE_DURATION - remainingUseTicks >= cookingTime

internal fun isStrongSkilletAttack(attackStrength: Float): Boolean = attackStrength > STRONG_ATTACK_THRESHOLD

/**
 * Handheld skillet: sneak-use on a block places it; otherwise, near heat, it takes one open-fire-cookable
 * ingredient from the other hand and cooks it while the use button is held.
 */
object HandheldSkillet : ItemBehavior {

    override fun useOnBlock(itemStack: ItemStack, block: Block, ctx: Context<BlockInteract>): InteractionResult {
        val player = ctx[BlockInteract.SOURCE_PLAYER] ?: return InteractionResult.Pass
        // Non-sneaking use on a block is not a placement and falls through to handheld cooking.
        if (!player.isSneaking) return InteractionResult.Pass
        return placeSkillet(player, ctx)
    }

    private fun placeSkillet(player: Player, ctx: Context<BlockInteract>): InteractionResult {
        val handItem = ctx[BlockInteract.HELD_ITEM_STACK]
        val clickedFace = ctx[BlockInteract.CLICKED_BLOCK_FACE] ?: BlockFace.UP
        var target = ctx[BlockInteract.BLOCK]
        if (!target.isReplaceable) target = target.getRelative(clickedFace)

        val skilletShape = BoundingBox(
            target.x + 1 / 16.0, target.y.toDouble(), target.z + 1 / 16.0,
            target.x + 15 / 16.0, target.y + 4 / 16.0, target.z + 15 / 16.0
        )
        val refusal = when {
            !target.isReplaceable -> "target ${target.blockType} at ${target.location.toVector()} is not replaceable"
            !ProtectionManager.canPlace(player, handItem, target) -> "a protection plugin denied placement at ${target.location.toVector()}"
            target.world.getNearbyEntities(skilletShape).any { it is LivingEntity } -> "an entity occupies ${target.location.toVector()}"
            else -> null
        }
        if (refusal != null) return refusePlacement(player, refusal)

        // The full placement state is given up front (facing WITH the player, like the original's
        // getHorizontalDirection()), so the tile entity is created with its final state.
        val placeState = (Blocks.SKILLET.get().createBlockData() as NovaBlockState).clone().apply {
            this[DefaultBlockStateProperties.FACING_HORIZONTAL] = player.facing
            this[BlockStateProperties.TRAY] = target.below.isTrayHeatSource()
            this[DefaultBlockStateProperties.WATERLOGGED] =
                target.world.getFluidData(target.x, target.y, target.z).fluidType == Fluid.WATER
        }
        val placeCtx = Context.intention(BlockPlace)
            .param(BlockPlace.BLOCK, target)
            .param(BlockPlace.BLOCK_STATE, placeState)
            .param(BlockPlace.PREVIOUS_BLOCK_STATE, target.blockData)
            .param(BlockPlace.SOURCE_ENTITY, player)
            .param(BlockPlace.CLICKED_BLOCK_FACE, clickedFace)
            .param(BlockPlace.HELD_HAND, ctx[BlockInteract.HELD_HAND])
            .param(BlockPlace.HELD_ITEM_STACK, handItem)
            .build()
        // Nova only accepts BLOCK_ITEM_STACK for block-linked items, so the placed skillet is handed over directly.
        val placed = Skillet.withPlacedItem(target, handItem.clone()) { BlockUtils.placeBlock(placeCtx) }
        if (!placed)
            return refusePlacement(player, "Nova BlockUtils.placeBlock returned false at ${target.location.toVector()}")
        return InteractionResult.Success(swing = true, action = ItemAction.Consume())
    }

    /** Placement failures used to be silent; surface the reason to the player and the console. */
    private fun refusePlacement(player: Player, reason: String): InteractionResult {
        Logger.warn("Skillet placement by ${player.name} refused: $reason")
        player.sendActionBar(Component.translatable("farmersdelight.item.skillet.cannot_place"))
        return InteractionResult.Fail
    }

    override fun use(itemStack: ItemStack, ctx: Context<ItemUse>): InteractionResult {
        val player = ctx[ItemUse.SOURCE_PLAYER] ?: return InteractionResult.Pass
        val hand = ctx[ItemUse.HELD_HAND] ?: return InteractionResult.Pass
        if (!canStartCooking(player)) return InteractionResult.Pass

        // An ingredient left in the skillet (e.g. after switching slots) resumes from the start.
        if (itemStack.readHandheldCooking() != null) {
            player.startUsingItem(hand)
            return InteractionResult.Success(swing = false)
        }

        val otherHand = if (hand == EquipmentSlot.HAND) EquipmentSlot.OFF_HAND else EquipmentSlot.HAND
        val ingredient = player.inventory.getItem(otherHand)
        if (ingredient.isEmpty) return InteractionResult.Pass
        if (player.isUnderWater) {
            player.sendActionBar(Component.translatable("farmersdelight.item.skillet.underwater"))
            return InteractionResult.Fail
        }
        val recipe = findCampfireRecipe(ingredient) ?: return InteractionResult.Pass

        val cookingTime = skilletCookingTime(recipe.cookingTime, itemStack.getEnchantmentLevel(Enchantment.FIRE_ASPECT))
        player.inventory.setItem(hand, itemStack.withHandheldCooking(HandheldCooking(ingredient.asOne(), cookingTime)))
        // The supplied path has no creative exemption: the other-hand ingredient is always consumed.
        player.inventory.setItem(otherHand, ingredient.clone().subtract())
        player.startUsingItem(hand)
        player.world.playSound(player.location, Sounds.BLOCK_SKILLET_ADD_FOOD, SoundCategory.PLAYERS, 0.8f, 1f)
        return InteractionResult.Success(swing = false)
    }

    /** On fire, or any heat-source member (lit or not, no conductor) in the 3x3x3 around the player's block. */
    private fun canStartCooking(player: Player): Boolean {
        if (player.fireTicks > 0) return true
        val origin = player.location.block
        for (x in -1..1) for (y in -1..1) for (z in -1..1) {
            if (origin.getRelative(x, y, z).isHeatSourceMember()) return true
        }
        return false
    }

    override fun modifyUseDuration(entity: LivingEntity, itemStack: ItemStack, duration: Int): Int =
        if (itemStack.readHandheldCooking() != null) USE_DURATION else duration

    override fun handleUseTick(entity: LivingEntity, itemStack: ItemStack, hand: EquipmentSlot, remainingUseTicks: Int): ItemAction? {
        val cooking = itemStack.readHandheldCooking() ?: return null
        if (!isHandheldCookingDone(remainingUseTicks, cooking.cookingTime)) {
            if (Random.nextInt(50) == 0)
                entity.world.playSound(entity.location, Sounds.BLOCK_SKILLET_SIZZLE, SoundCategory.PLAYERS, 0.4f, Random.nextFloat() * 0.2f + 0.9f)
            // Only resend the stack when the visible bar width changes.
            val width = cookingBarWidth(USE_DURATION - remainingUseTicks, cooking.cookingTime)
            if (width == itemStack.cookingBarProgress()) return null
            return ItemAction.ConvertStack(itemStack.withCookingBarProgress(width))
        }

        // A recipe that disappeared mid-cook yields nothing; the stored ingredient is cleared (documented loss boundary).
        findCampfireRecipe(cooking.ingredient)?.result?.clone()?.let { giveOrDrop(entity, it) }
        entity.clearActiveItem()
        return ItemAction.ConvertStack(itemStack.withHandheldCooking(null))
    }

    override fun handleUseStopped(entity: LivingEntity, itemStack: ItemStack, hand: EquipmentSlot, remainingUseTicks: Int): ItemAction? {
        // Early release: no partial progress; the raw ingredient goes back to the player.
        val cooking = itemStack.readHandheldCooking() ?: return null
        giveOrDrop(entity, cooking.ingredient)
        return ItemAction.ConvertStack(itemStack.withHandheldCooking(null))
    }

    private fun giveOrDrop(entity: LivingEntity, stack: ItemStack) {
        if (entity is Player) entity.safeGive(stack)
        else entity.world.dropItem(entity.location, stack)
    }

    /**
     * While an ingredient is cooking, the client-side item bar shows cooking progress instead of wear.
     * Vanilla clients derive the bar colour from the damage ratio, so the original's fixed orange cannot be reproduced.
     */
    override fun modifyClientSideStack(player: Player?, server: ItemStack, client: ItemStack): ItemStack {
        if (server.readHandheldCooking() == null) return client
        client.setData(DataComponentTypes.MAX_DAMAGE, BAR_WIDTH)
        client.setData(DataComponentTypes.DAMAGE, BAR_WIDTH - server.cookingBarProgress())
        return client
    }
}
