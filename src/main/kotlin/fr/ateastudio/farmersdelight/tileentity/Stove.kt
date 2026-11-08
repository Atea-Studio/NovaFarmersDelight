package fr.ateastudio.farmersdelight.tileentity

import fr.ateastudio.farmersdelight.block.BlockStateProperties
import fr.ateastudio.farmersdelight.nms.Nms
import fr.ateastudio.farmersdelight.registry.Sounds
import fr.ateastudio.farmersdelight.tileentity.display.ItemDisplaySpec
import fr.ateastudio.farmersdelight.tileentity.display.PacketItemDisplays
import fr.ateastudio.farmersdelight.util.getCraftingRemainingItem
import fr.ateastudio.farmersdelight.util.findCampfireRecipe
import fr.ateastudio.farmersdelight.util.safeGive
import fr.ateastudio.farmersdelight.util.random
import org.bukkit.GameMode
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.Particle
import org.bukkit.Sound
import org.bukkit.SoundCategory
import org.bukkit.Tag
import org.bukkit.block.Block
import org.bukkit.block.BlockFace
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.ItemStack
import org.bukkit.util.BoundingBox
import org.joml.Matrix4f
import xyz.xenondevs.cbf.Compound
import xyz.xenondevs.invui.inventory.VirtualInventory
import xyz.xenondevs.nova.context.Context
import xyz.xenondevs.nova.context.intention.BlockBreak
import xyz.xenondevs.nova.context.intention.BlockInteract
import xyz.xenondevs.nova.util.center
import xyz.xenondevs.nova.util.playSound
import xyz.xenondevs.nova.world.InteractionResult
import xyz.xenondevs.nova.world.block.NovaBlockState
import xyz.xenondevs.nova.world.block.state.property.DefaultBlockStateProperties
import xyz.xenondevs.nova.world.block.tileentity.TileEntity

private const val SLOT_COUNT = 6
private const val DISPLAY_HEIGHT = 1.02
private const val DISPLAY_SCALE = 0.375f
private val SLOT_OFFSETS = arrayOf(
    Pair(0.3, 0.2), Pair(0.0, 0.2), Pair(-0.3, 0.2),
    Pair(0.3, -0.2), Pair(0.0, -0.2), Pair(-0.3, -0.2)
)
/** Grilling area `(3,0,3)-(13,1,13)` in pixels, as block-local coordinates. */
private val GRILLING_AREA = BoundingBox(3.0 / 16, 0.0, 3.0 / 16, 13.0 / 16, 1.0 / 16, 13.0 / 16)

internal fun stoveCompletionOutput(input: ItemStack, recipeOutput: ItemStack?): ItemStack =
    (recipeOutput ?: input).clone()

/** Horizontal-data index used by the original renderer: south=0, west=1, north=2, east=3. */
private fun BlockFace.horizontalIndex(): Int = when (this) {
    BlockFace.SOUTH -> 0
    BlockFace.WEST -> 1
    BlockFace.NORTH -> 2
    BlockFace.EAST -> 3
    else -> 2
}

/** Yaw (degrees) applied to displayed stove items: rotate opposite the stove's facing. */
internal fun stoveDisplayYaw(facing: BlockFace): Double = -facing.oppositeFace.horizontalIndex() * 90.0

/**
 * World-space X/Z offset of a stove slot: the slot offset lies in the item plane, which is laid flat
 * (+90 degrees around X) and then rotated by [stoveDisplayYaw].
 */
internal fun stoveSlotOffset(slot: Int, facing: BlockFace): Pair<Double, Double> {
    val (x, y) = SLOT_OFFSETS[slot]
    val yaw = Math.toRadians(stoveDisplayYaw(facing))
    val cos = Math.cos(yaw)
    val sin = Math.sin(yaw)
    return Pair(x * cos + y * sin, -x * sin + y * cos)
}

class Stove(
    block: Block,
    state: NovaBlockState,
    data: Compound
) : TileEntity(block, state, data) {

    private val inventory: VirtualInventory = storedInventory(
        "stored_items",
        SLOT_COUNT,
        maxStackSizes = IntArray(SLOT_COUNT) { 1 },
        postUpdateHandler = { refreshDisplays() }
    )

    private val cookingProgress: IntArray = retrieveData("cookingTimes") { IntArray(SLOT_COUNT) }.copyOf(SLOT_COUNT)
    private val cookingTimeTotal: IntArray = retrieveData("cookingTotalTimes") { IntArray(SLOT_COUNT) }.copyOf(SLOT_COUNT)

    private val displays = PacketItemDisplays()

    private fun isLit(): Boolean = blockState[BlockStateProperties.LIT] ?: false

    override fun saveData() {
        super.saveData()
        storeData("cookingTimes", cookingProgress)
        storeData("cookingTotalTimes", cookingTimeTotal)
    }

    override fun handleEnable() {
        refreshDisplays()
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

        if (isLit()) {
            val extinguishResult = tryExtinguish(heldItem, player, hand ?: EquipmentSlot.HAND)
            if (extinguishResult != null) return extinguishResult
        } else {
            val igniteResult = tryIgnite(heldItem, player)
            if (igniteResult != null) return igniteResult
        }

        return tryPlaceFood(heldItem, player)
    }

    private fun tryIgnite(heldItem: ItemStack, player: Player): InteractionResult? {
        if (heldItem.type == Material.FLINT_AND_STEEL) {
            block.playSound(Sound.ITEM_FLINTANDSTEEL_USE, 1f, block.world.random().nextFloat() * 0.4f + 0.8f)
            setLit(true)
            if (player.gameMode != GameMode.CREATIVE) heldItem.damage(1, player)
            return InteractionResult.Success()
        }
        if (heldItem.type == Material.FIRE_CHARGE) {
            block.playSound(Sound.ITEM_FIRECHARGE_USE, 1f, 1f)
            setLit(true)
            if (player.gameMode != GameMode.CREATIVE) heldItem.subtract()
            return InteractionResult.Success()
        }
        return null
    }

    private fun tryExtinguish(heldItem: ItemStack, player: Player, hand: EquipmentSlot): InteractionResult? {
        if (Tag.ITEMS_SHOVELS.isTagged(heldItem.type)) {
            block.playSound(Sound.BLOCK_FIRE_EXTINGUISH, 1f, 1f)
            setLit(false)
            if (player.gameMode != GameMode.CREATIVE) heldItem.damage(1, player)
            return InteractionResult.Success()
        }
        if (heldItem.type == Material.WATER_BUCKET) {
            block.playSound(Sound.ITEM_BUCKET_EMPTY, 1f, 1f)
            setLit(false)
            if (player.gameMode != GameMode.CREATIVE) {
                val remaining = heldItem.getCraftingRemainingItem()
                if (heldItem.amount == 1) {
                    player.inventory.setItem(hand, remaining)
                } else {
                    heldItem.subtract()
                    if (!remaining.isEmpty) player.safeGive(remaining)
                }
            }
            return InteractionResult.Success()
        }
        return null
    }

    private fun setLit(lit: Boolean) {
        updateLiveBlockState { this[BlockStateProperties.LIT] = lit }
    }

    private fun tryPlaceFood(heldItem: ItemStack, player: Player): InteractionResult {
        if (heldItem.isEmpty) return InteractionResult.Pass
        if (isBlockedAbove()) return InteractionResult.Pass
        val emptySlot = getNextEmptySlot()
        if (emptySlot < 0) return InteractionResult.Pass

        val recipe = findCampfireRecipe(heldItem) ?: return InteractionResult.Pass

        cookingTimeTotal[emptySlot] = recipe.cookingTime
        cookingProgress[emptySlot] = 0
        inventory.setItem(SELF_UPDATE_REASON, emptySlot, heldItem.clone().apply { amount = 1 })
        if (player.gameMode != GameMode.CREATIVE) heldItem.subtract()
        block.playSound(Sound.BLOCK_LANTERN_PLACE, 0.5f, 1f)
        return InteractionResult.Success()
    }

    private fun getNextEmptySlot(): Int {
        for (i in 0 until SLOT_COUNT) {
            val item = inventory.getItem(i)
            if (item == null || item.isEmpty) return i
        }
        return -1
    }

    override fun handleTick() {
        if (isBlockedAbove()) {
            ejectAllItems()
            return
        }
        if (isLit()) {
            cookItems()
            burnEntitiesAbove()
            if (block.world.random().nextInt(10) == 0) {
                block.playSound(Sounds.BLOCK_STOVE_CRACKLE, SoundCategory.BLOCKS, 1f, 1f)
            }
            spawnFlameParticles()
        } else {
            coolItems()
        }
    }

    /** True when the selection shape of the block above intersects the grilling area `(3,0,3)-(13,1,13)`. */
    private fun isBlockedAbove(): Boolean {
        val above = block.getRelative(BlockFace.UP)
        if (above.isEmpty) return false
        return Nms.outlineIntersects(above, GRILLING_AREA)
    }

    private fun ejectAllItems() {
        var changed = false
        for (i in 0 until SLOT_COUNT) {
            val stack = inventory.getItem(i)
            if (stack == null || stack.isEmpty) continue
            block.world.dropItemNaturally(block.location.add(0.5, 0.5, 0.5), stack.clone())
            inventory.setItem(SELF_UPDATE_REASON, i, ItemStack.empty())
            cookingProgress[i] = 0
            changed = true
        }
        if (changed) refreshDisplays()
    }

    private fun cookItems() {
        var changed = false
        for (i in 0 until SLOT_COUNT) {
            val stack = inventory.getItem(i)
            if (stack == null || stack.isEmpty) continue
            cookingProgress[i]++
            if (cookingProgress[i] < cookingTimeTotal[i]) continue

            val recipe = findCampfireRecipe(stack)
            val output = stoveCompletionOutput(stack, recipe?.result)
            block.world.dropItem(block.location.add(0.5, 1.0, 0.5), output)
            inventory.setItem(SELF_UPDATE_REASON, i, ItemStack.empty())
            cookingProgress[i] = 0
            changed = true
        }
        // Displays only change when an item leaves; respawning them every tick makes them flicker.
        if (changed) refreshDisplays()
    }

    private fun coolItems() {
        for (i in 0 until SLOT_COUNT) {
            if (cookingProgress[i] <= 0) continue
            cookingProgress[i] = (cookingProgress[i] - 2).coerceIn(0, cookingTimeTotal[i])
        }
    }

    private fun burnEntitiesAbove() {
        // Grilling region (3,0,3)-(13,1,13) of the block space directly above the stove.
        val grillingArea = block.location.add(0.5, 1.0 + 0.5 / 16.0, 0.5)
        block.world.getNearbyEntities(grillingArea, 5.0 / 16.0, 0.5 / 16.0, 5.0 / 16.0)
            .filterIsInstance<LivingEntity>()
            .filter { !it.isSneaking }
            .forEach { it.damage(1.0) }
    }

    private fun spawnFlameParticles() {
        val random = block.world.random()
        val facing = blockState[DefaultBlockStateProperties.FACING_HORIZONTAL]
        val horizontalOffset = random.nextDouble() * 0.6 - 0.3
        val xOffset = if (facing == BlockFace.EAST || facing == BlockFace.WEST) facing.direction.x * 0.52 else horizontalOffset
        val zOffset = if (facing == BlockFace.NORTH || facing == BlockFace.SOUTH) facing.direction.z * 0.52 else horizontalOffset
        val yOffset = random.nextDouble() * 6.0 / 16.0
        val location = block.location.center().add(xOffset, yOffset - 0.5, zOffset)
        block.world.spawnParticle(Particle.SMOKE, location, 1, 0.0, 0.0, 0.0, 0.0)
        block.world.spawnParticle(Particle.FLAME, location, 1, 0.0, 0.0, 0.0, 0.0)
    }

    override fun handleBreak(ctx: Context<BlockBreak>) {
        displays.clear()
    }

    private fun refreshDisplays() {
        val facing = blockState[DefaultBlockStateProperties.FACING_HORIZONTAL] ?: BlockFace.NORTH
        val transformation = Matrix4f()
            .rotateY(Math.toRadians(stoveDisplayYaw(facing)).toFloat())
            .rotateX(Math.toRadians(90.0).toFloat())
            .scale(DISPLAY_SCALE)
        displays.show((0 until SLOT_COUNT).mapNotNull { i ->
            val item = inventory.getItem(i)?.takeUnless { it.isEmpty } ?: return@mapNotNull null
            val (xOffset, zOffset) = stoveSlotOffset(i, facing)
            ItemDisplaySpec(getDisplayLocation().add(xOffset, 0.0, zOffset), item, transformation)
        })
    }

    private fun getDisplayLocation(): Location =
        block.location.add(0.5, DISPLAY_HEIGHT, 0.5)
}
