package fr.ateastudio.farmersdelight.tileentity

import fr.ateastudio.farmersdelight.item.readHandheldCooking
import fr.ateastudio.farmersdelight.item.withHandheldCooking
import fr.ateastudio.farmersdelight.registry.Items
import fr.ateastudio.farmersdelight.registry.Sounds
import org.bukkit.enchantments.Enchantment
import xyz.xenondevs.nova.context.intention.BlockPlace
import xyz.xenondevs.nova.world.item.createItemStack
import fr.ateastudio.farmersdelight.util.clockWise
import fr.ateastudio.farmersdelight.util.getItemId
import org.bukkit.inventory.meta.Damageable
import fr.ateastudio.farmersdelight.util.isHeatedFromBelow
import fr.ateastudio.farmersdelight.util.findCampfireRecipe
import fr.ateastudio.farmersdelight.util.safeGive
import fr.ateastudio.farmersdelight.util.random
import fr.ateastudio.farmersdelight.util.spawnItemEntity
import org.bukkit.Location
import org.bukkit.Particle
import org.bukkit.Sound
import org.bukkit.SoundCategory
import org.bukkit.block.Block
import org.bukkit.block.BlockFace
import fr.ateastudio.farmersdelight.block.BlockStateProperties
import fr.ateastudio.farmersdelight.tileentity.display.ItemDisplaySpec
import fr.ateastudio.farmersdelight.tileentity.display.PacketItemDisplays
import fr.ateastudio.farmersdelight.util.isTrayHeatSource
import org.bukkit.entity.Player
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.ItemStack
import org.joml.Matrix4f
import xyz.xenondevs.cbf.Compound
import xyz.xenondevs.invui.inventory.VirtualInventory
import xyz.xenondevs.nova.context.Context
import xyz.xenondevs.nova.context.intention.BlockBreak
import xyz.xenondevs.nova.context.intention.BlockInteract
import xyz.xenondevs.nova.util.below
import xyz.xenondevs.nova.util.playSound
import xyz.xenondevs.nova.world.InteractionResult
import xyz.xenondevs.nova.world.block.NovaBlockState
import xyz.xenondevs.nova.world.block.state.property.DefaultBlockStateProperties
import xyz.xenondevs.nova.world.block.tileentity.TileEntity

private const val MINIMUM_COOKING_TIME = 60
private const val DISPLAY_SCALE = 0.5f
private const val DISPLAY_SCATTER = 0.075

/** Displayed models for a stored stack: empty 0, one item 1, otherwise `1 + ceil(4 * count / stack limit)` (max 5). */
internal fun stackedDisplayCount(count: Int, maxStackSize: Int): Int = when {
    count <= 0 -> 0
    count == 1 -> 1
    else -> 1 + Math.ceil(4.0 * count / maxStackSize.coerceAtLeast(1)).toInt()
}

/** Yaw (degrees) turning displayed ingredients to face the skillet's front: `-toYRot(facing)`. */
internal fun skilletDisplayYaw(facing: BlockFace): Double = when (facing) {
    BlockFace.SOUTH -> 0.0
    BlockFace.WEST -> -90.0
    BlockFace.NORTH -> -180.0
    BlockFace.EAST -> -270.0
    else -> -180.0
}

/**
 * Placed/handheld skillet duration for a recipe time [originalCookingTime] (T) and Fire Aspect level L:
 * `truncate((T / 20) * (0.2 - 0.05 * L)) * 20`, then at least 60 and at most T, applied in that order.
 * Nonpositive or sub-minimum T is an unresolved boundary (CL-006); the literal formula is kept, without
 * substituting a default recipe time.
 */
internal fun skilletCookingTime(originalCookingTime: Int, fireAspectLevel: Int = 0): Int {
    val cookingSeconds = originalCookingTime / 20
    val reduction = 0.2f - 0.05f * fireAspectLevel
    val result = (cookingSeconds * reduction).toInt() * 20
    return minOf(maxOf(result, MINIMUM_COOKING_TIME), originalCookingTime)
}

class Skillet(
    block: Block,
    state: NovaBlockState,
    data: Compound
) : TileEntity(block, state, data) {

    private val inventory: VirtualInventory = storedInventory(
        "stored_item",
        1,
        postUpdateHandler = { refreshDisplay() }
    )

    private var cookingTime: Int by storedValue<Int>("cookingTime", true) { 0 }
    private var cookingTimeTotal: Int by storedValue<Int>("cookingTimeTotal", true) { 0 }

    /** The exact skillet item that was placed, preserving wear, name and enchantments. */
    private var placedSkillet: ItemStack? by storedValue<ItemStack>("placedSkillet")

    private fun isHeated(): Boolean = block.isHeatedFromBelow()

    private val displays = PacketItemDisplays()

    /** Waterlogging is changed by Nova itself (buckets), so it is read from the live world state. */
    private fun isWaterlogged(): Boolean = liveBlockState[DefaultBlockStateProperties.WATERLOGGED] == true

    private fun fireAspectLevel(): Int = placedSkillet?.getEnchantmentLevel(Enchantment.FIRE_ASPECT) ?: 0

    /** The item restored when this skillet is picked up or dropped. */
    fun skilletItem(): ItemStack = placedSkillet?.clone() ?: Items.SKILLET.createItemStack()

    override fun handlePlace(ctx: Context<BlockPlace>) {
        super.handlePlace(ctx)
        val placed = (pendingPlacedItems.remove(block) ?: ctx[BlockPlace.BLOCK_ITEM_STACK])?.asOne() ?: return
        // An ingredient still held in a handheld skillet is returned rather than lost on placement.
        placed.readHandheldCooking()?.let { cooking ->
            val player = ctx[BlockPlace.SOURCE_PLAYER]
            if (player != null) player.safeGive(cooking.ingredient)
            else block.world.dropItem(block.location.add(0.5, 0.5, 0.5), cooking.ingredient)
        }
        placedSkillet = placed.withHandheldCooking(null)
    }

    override fun getDrops(includeSelf: Boolean): List<ItemStack> {
        // The block has no linked item type, so the skillet item is added explicitly.
        val drops = super.getDrops(includeSelf).toMutableList()
        if (includeSelf) drops += skilletItem()
        return drops
    }

    override fun handleEnable() {
        refreshDisplay()
    }

    override fun handleDisable() {
        displays.clear()
        super.handleDisable()
    }

    override fun useItemOn(ctx: Context<BlockInteract>): InteractionResult {
        val player = ctx[BlockInteract.SOURCE_PLAYER] ?: return InteractionResult.Pass
        val hand = ctx[BlockInteract.HELD_HAND]
        val heldItem = if (hand == EquipmentSlot.HAND) {
            player.inventory.itemInMainHand
        } else {
            player.inventory.itemInOffHand
        }

        if (heldItem.isEmpty) {
            return removeItem(player)
        }

        return addItemToCook(heldItem, player)
    }

    private fun removeItem(player: Player): InteractionResult {
        val stored = inventory.getItem(0)?.clone() ?: return InteractionResult.Pass
        inventory.setItem(SELF_UPDATE_REASON, 0, ItemStack.empty())
        cookingTime = 0
        if (player.gameMode != org.bukkit.GameMode.CREATIVE) player.safeGive(stored)
        return InteractionResult.Success()
    }

    private fun addItemToCook(heldItem: ItemStack, player: Player): InteractionResult {
        val current = inventory.getItem(0)
        if (current != null && !current.isEmpty) return InteractionResult.Pass

        if (isWaterlogged()) {
            player.sendActionBar(net.kyori.adventure.text.Component.translatable("farmersdelight.block.skillet.underwater"))
            return InteractionResult.Pass
        }

        val recipe = findCampfireRecipe(heldItem)
        if (recipe == null) {
            player.sendMessage(net.kyori.adventure.text.Component.translatable("farmersdelight.block.skillet.invalid_item"))
            return InteractionResult.Pass
        }

        // Insert the whole held stack, up to its ordinary stack limit.
        val amount = minOf(heldItem.amount, heldItem.maxStackSize, inventory.getMaxSlotStackSize(0))
        inventory.setItem(SELF_UPDATE_REASON, 0, heldItem.asQuantity(amount))
        if (player.gameMode != org.bukkit.GameMode.CREATIVE) heldItem.subtract(amount)

        cookingTimeTotal = skilletCookingTime(recipe.cookingTime, fireAspectLevel())
        cookingTime = 0

        if (isHeated()) {
            block.playSound(Sounds.BLOCK_SKILLET_ADD_FOOD, SoundCategory.BLOCKS, 0.8f, 1f)
        }
        block.playSound(Sound.BLOCK_LANTERN_PLACE, 0.7f, 1f)
        return InteractionResult.Success()
    }

    override fun handleTick() {
        // The tray model follows the block below; the tile entity owns this state change.
        val trayBelow = block.below.isTrayHeatSource()
        updateLiveBlockState { this[BlockStateProperties.TRAY] = trayBelow }

        val heated = isHeated()
        val stack = inventory.getItem(0)
        if (stack == null || stack.isEmpty) {
            if (cookingTime != 0) cookingTime = 0
            return
        }

        // Flooding ejects every stored ingredient, uncooked.
        if (isWaterlogged()) {
            block.world.dropItemNaturally(block.location.add(0.5, 0.5, 0.5), stack.clone())
            inventory.setItem(SELF_UPDATE_REASON, 0, ItemStack.empty())
            cookingTime = 0
            return
        }

        if (heated) {
            cookingTime++
            if (cookingTime >= cookingTimeTotal) {
                finishCooking(stack)
            } else if (block.world.random().nextInt(10) == 0) {
                block.playSound(Sounds.BLOCK_SKILLET_SIZZLE, SoundCategory.BLOCKS, 0.4f, block.world.random().nextFloat() * 0.2f + 0.9f)
            }
            spawnAmbientParticles()
        } else if (cookingTime > 0) {
            cookingTime = (cookingTime - 2).coerceAtLeast(0)
        }
    }

    /** Finishes one unit: eject its result to the right (clockwise from facing) and continue with the rest. */
    private fun finishCooking(stack: ItemStack) {
        val recipe = findCampfireRecipe(stack) ?: return
        val direction = (blockState[DefaultBlockStateProperties.FACING_HORIZONTAL] ?: BlockFace.NORTH).clockWise()
        // The backing block has full-cube collision, and items spawned inside it are pushed out to the north,
        // so the result starts just past the ejection side (or on top if that side is obstructed).
        val side = block.getRelative(direction)
        val spawn = if (side.isPassable)
            block.location.add(0.5 + direction.modX * EJECT_OFFSET, 0.3, 0.5 + direction.modZ * EJECT_OFFSET)
        else
            block.location.add(0.5, 1.0, 0.5)
        recipe.result.clone().spawnItemEntity(spawn, direction.modX * 0.08, 0.25, direction.modZ * 0.08)
        cookingTime = 0
        inventory.addItemAmount(SELF_UPDATE_REASON, 0, -1)
    }

    private fun spawnAmbientParticles() {
        if (block.world.random().nextFloat() >= 0.2f) return
        val location = getDisplayLocation().add(
            block.world.random().nextDouble() * 0.4 - 0.2,
            0.1,
            block.world.random().nextDouble() * 0.4 - 0.2
        )
        block.world.spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, location, 1, 0.0, 0.0, 0.0, 0.0)
    }

    override fun handleBreak(ctx: Context<BlockBreak>) {
        displays.clear()
    }

    private fun refreshDisplay() {
        val item = inventory.getItem(0)
        if (item == null || item.isEmpty) {
            displays.clear()
            return
        }

        val facing = blockState[DefaultBlockStateProperties.FACING_HORIZONTAL] ?: BlockFace.NORTH
        // Stable scatter between refreshes: seeded by item identity and wear, like the original renderer.
        val damage = (item.itemMeta as? Damageable)?.damage ?: 0
        val random = java.util.Random((item.getItemId().hashCode() + damage).toLong())
        val transformation = Matrix4f()
            .rotateY(Math.toRadians(skilletDisplayYaw(facing)).toFloat())
            .rotateX(Math.toRadians(90.0).toFloat())
            .scale(DISPLAY_SCALE)

        displays.show(List(stackedDisplayCount(item.amount, item.maxStackSize)) { index ->
            val xOffset = (random.nextFloat() * 2.0 - 1.0) * DISPLAY_SCATTER
            val zOffset = (random.nextFloat() * 2.0 - 1.0) * DISPLAY_SCATTER
            val location = block.location.add(0.5 + xOffset, 0.1 + 0.03 * (index + 1), 0.5 + zOffset)
            ItemDisplaySpec(location, item, transformation)
        })
    }

    private fun getDisplayLocation(): Location =
        block.location.toCenterLocation().add(0.0, 0.1, 0.0)

    companion object {
        // Half a block plus the item entity's half-width (0.125), so it doesn't overlap the backing block.
        private const val EJECT_OFFSET = 0.65

        // Placement and handlePlace both run synchronously on the server thread.
        private val pendingPlacedItems = HashMap<Block, ItemStack>()

        /** Makes [item] the placed skillet for a tile entity created at [block] while [place] runs. */
        fun <T> withPlacedItem(block: Block, item: ItemStack, place: () -> T): T {
            pendingPlacedItems[block] = item
            try {
                return place()
            } finally {
                pendingPlacedItems.remove(block)
            }
        }
    }
}
