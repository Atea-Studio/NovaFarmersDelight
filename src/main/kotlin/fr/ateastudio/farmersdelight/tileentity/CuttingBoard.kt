package fr.ateastudio.farmersdelight.tileentity

import fr.ateastudio.farmersdelight.block.ComparatorSignalSource
import fr.ateastudio.farmersdelight.block.ComparatorSignals
import fr.ateastudio.farmersdelight.block.comparatorFullness
import fr.ateastudio.farmersdelight.block.fullnessSlots
import fr.ateastudio.farmersdelight.recipe.cuttingboard.CuttingBoardRecipe
import fr.ateastudio.farmersdelight.registry.RecipeTypes
import fr.ateastudio.farmersdelight.registry.Sounds
import fr.ateastudio.farmersdelight.tileentity.display.ItemDisplaySpec
import fr.ateastudio.farmersdelight.tileentity.display.PacketItemDisplays
import fr.ateastudio.farmersdelight.tileentity.network.NetworkedInventoryViews
import fr.ateastudio.farmersdelight.util.counterClockWise
import fr.ateastudio.farmersdelight.util.isKnife
import fr.ateastudio.farmersdelight.util.random
import fr.ateastudio.farmersdelight.util.runOnMainThread
import fr.ateastudio.farmersdelight.util.safeGive
import fr.ateastudio.farmersdelight.util.spawnItemEntity
import io.papermc.paper.registry.RegistryAccess
import io.papermc.paper.registry.RegistryKey
import io.papermc.paper.registry.keys.tags.ItemTypeTagKeys
import net.kyori.adventure.text.Component
import org.bukkit.GameMode
import org.bukkit.Location
import org.bukkit.Sound
import org.bukkit.SoundCategory
import org.bukkit.block.Block
import org.bukkit.block.BlockFace
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.ItemType
import org.joml.Matrix4f
import xyz.xenondevs.cbf.Compound
import xyz.xenondevs.invui.inventory.VirtualInventory
import xyz.xenondevs.nova.context.Context
import xyz.xenondevs.nova.context.intention.BlockBreak
import xyz.xenondevs.nova.context.intention.BlockInteract
import xyz.xenondevs.nova.util.CubeFaceMap
import xyz.xenondevs.nova.util.playSound
import xyz.xenondevs.nova.world.InteractionResult
import xyz.xenondevs.nova.world.block.NovaBlockState
import xyz.xenondevs.nova.world.block.blockTypeOrNull
import xyz.xenondevs.nova.world.block.novaTileEntity
import xyz.xenondevs.nova.world.block.state.property.DefaultBlockStateProperties
import xyz.xenondevs.nova.world.block.tileentity.NetworkedTileEntity
import xyz.xenondevs.nova.world.block.tileentity.network.type.NetworkConnectionType
import xyz.xenondevs.nova.world.block.tileentity.network.type.item.inventory.NetworkedInventory
import xyz.xenondevs.nova.world.item.itemType
import xyz.xenondevs.nova.world.item.recipe.RecipeManager

private const val STACK_ITEM_Y_OFFSET = 0.03
private const val STACK_ITEM_HORIZONTAL_OFFSET = 0.075
private const val MAX_CUTTING_BOARD_STACK_SIZE = 64

private val CARVEABLE_TOOL_TYPES by lazy {
    val registry = RegistryAccess.registryAccess().getRegistry(RegistryKey.ITEM)
    listOf(ItemTypeTagKeys.SWORDS, ItemTypeTagKeys.AXES, ItemTypeTagKeys.PICKAXES, ItemTypeTagKeys.HOES, ItemTypeTagKeys.SHOVELS)
        .flatMapTo(HashSet()) { registry.getTagValues(it) }
}

@Suppress("UnstableApiUsage")
class CuttingBoard(
    block: Block,
    state: NovaBlockState,
    data: Compound
) : NetworkedTileEntity(block, state, data), ComparatorSignalSource {

    private val storedItemInventory: VirtualInventory = storedInventory(
        "stored_item",
        1,
        maxStackSizes = intArrayOf(MAX_CUTTING_BOARD_STACK_SIZE),
        postUpdateHandler = { handleStoredItemUpdate() }
    )

    // Both access views are direct inventories; there is no merged multi-inventory transfer adapter.
    private val networkedInventory = NetworkedInventoryViews(storedItemInventory)
    private val itemHolder = storedItemHolder(
        networkedInventory.all to NetworkConnectionType.BUFFER,
        networkedInventory.single to NetworkConnectionType.BUFFER,
        mergedInventory = networkedInventory.all,
        defaultInventoryConfig = CubeFaceMap<NetworkedInventory?>(networkedInventory.all)
    )

    override fun requestsLocalNetwork(face: BlockFace): Boolean =
        itemHolder.connectionConfig[face] != NetworkConnectionType.BUFFER

    // Mirrors the original mod's "isItemCarvingBoard" flag: true while a tool is carved onto an
    // otherwise empty board, blocking normal item placement/recipe processing until removed.
    private var isCarvingBoard: Boolean by storedValue<Boolean>("isItemCarved", true) { false }

    private val displays = PacketItemDisplays()

    override fun handleEnable() {
        super.handleEnable()
        refreshDisplays()
    }

    /**
     * Automation can also empty the board; an empty board is never a carved board.
     * Item networks call this from worker threads, so display entities are refreshed on the server thread.
     */
    private fun handleStoredItemUpdate() {
        if (storedItemInventory.isEmpty) isCarvingBoard = false
        runOnMainThread {
            // Skip if the board was broken or unloaded before the deferred refresh ran.
            if (block.novaTileEntity === this) refreshDisplays()
        }
        ComparatorSignals.notifyChanged(block)
    }

    /** One-slot fullness comparator. */
    override fun comparatorSignal(): Int = comparatorFullness(storedItemInventory.fullnessSlots())

    override fun handleDisable() {
        displays.clear()
        super.handleDisable()
    }

    override fun useItemOn(ctx: Context<BlockInteract>): InteractionResult {
        val player = ctx[BlockInteract.SOURCE_PLAYER] ?: return InteractionResult.Pass
        val facing = blockState[DefaultBlockStateProperties.FACING_HORIZONTAL]

        // Every board interaction inspects the MAIN hand, regardless of which hand triggered it.
        val heldItem = player.inventory.itemInMainHand

        // Sneak + a tool carves it onto an empty board, mirroring the original mod's ToolCarvingEvent.
        if (player.isSneaking && !heldItem.isEmpty && isCarveableTool(heldItem)) {
            if (carveToolOnBoard(heldItem)) {
                if (player.gameMode != GameMode.CREATIVE) heldItem.subtract()
                block.playSound(Sound.BLOCK_WOOD_PLACE, 1f, 0.8f)
                return InteractionResult.Success()
            }
        }

        val storedItem = storedItemInventory.getItem(0)

        if (heldItem.isEmpty) {
            if (storedItem == null || storedItem.isEmpty) return InteractionResult.Pass
            retrieveItem(player)
            return InteractionResult.Success()
        }

        // A carved board rejects both insertion and processing until the tool is removed.
        if (isCarvingBoard) return InteractionResult.Pass

        val inserted = insertableAmount(storedItem, heldItem)
        if (inserted > 0) {
            if (storedItem == null || storedItem.isEmpty) {
                storedItemInventory.setItem(SELF_UPDATE_REASON, 0, heldItem.asQuantity(inserted))
            } else {
                storedItemInventory.addItemAmount(SELF_UPDATE_REASON, 0, inserted)
            }
            if (player.gameMode != GameMode.CREATIVE) heldItem.subtract(inserted)
            block.playSound(Sound.BLOCK_WOOD_PLACE, 1f, 0.8f)
            return InteractionResult.Success()
        }

        if (storedItem == null || storedItem.isEmpty) return InteractionResult.Pass
        doRecipe(player, heldItem, facing)
        return InteractionResult.Success()
    }

    /** How many of [held] fit on the board: same item (or empty board) up to the ordinary stack limit. */
    private fun insertableAmount(stored: ItemStack?, held: ItemStack): Int {
        val limit = minOf(storedItemInventory.getMaxSlotStackSize(0), held.maxStackSize)
        if (stored == null || stored.isEmpty) return minOf(held.amount, limit)
        if (!stored.isSimilar(held)) return 0
        return minOf(held.amount, (limit - stored.amount).coerceAtLeast(0))
    }

    /** Equivalent of the original's `TieredItem || TridentItem || ShearsItem` carving check. */
    private fun isCarveableTool(item: ItemStack): Boolean {
        if (item.isEmpty) return false
        return item.isKnife() ||
            item.itemType == ItemType.SHEARS ||
            item.itemType == ItemType.TRIDENT ||
            item.itemType in CARVEABLE_TOOL_TYPES
    }

    /** Carves [tool] onto the board; only succeeds if the board is currently empty. */
    private fun carveToolOnBoard(tool: ItemStack): Boolean {
        val current = storedItemInventory.getItem(0)
        if (current != null && !current.isEmpty) return false
        storedItemInventory.setItem(SELF_UPDATE_REASON, 0, tool.asOne())
        isCarvingBoard = true
        return true
    }

    override fun handleBreak(ctx: Context<BlockBreak>) {
        displays.clear()
        super.handleBreak(ctx)
    }

    private fun doRecipe(player: Player, tool: ItemStack, blockFace: BlockFace?) {
        val input = storedItemInventory.getItem(0) ?: return
        val fortuneLevel = tool.getEnchantmentLevel(Enchantment.FORTUNE)
        val recipes = getMatchingRecipes(input)
        if (recipes.isEmpty()) {
            player.sendMessage(Component.translatable("farmersdelight.block.cutting_board.invalid_item"))
            return
        }

        val matchingRecipe = recipes.firstOrNull { it.tool.test(tool) }
        if (matchingRecipe == null) {
            player.sendMessage(Component.translatable("farmersdelight.block.cutting_board.invalid_tool"))
            return
        }

        val direction = (blockFace ?: BlockFace.NORTH).counterClockWise()
        for (resultStack in matchingRecipe.rollResults(block.world.random(), fortuneLevel)) {
            resultStack.spawnItemEntity(
                block.location.add(0.5 + direction.modX * 0.2, 0.2, 0.5 + direction.modZ * 0.2),
                direction.modX * 0.2, 0.0, direction.modZ * 0.2
            )
        }

        if (player.gameMode != GameMode.CREATIVE) tool.damage(1, player)
        playProcessingSound(matchingRecipe.soundEvent.ifEmpty { null }, tool)
        storedItemInventory.addItemAmount(SELF_UPDATE_REASON, 0, -1)

        val remainingItems = storedItemInventory.getItem(0)?.amount ?: 0
        if (remainingItems > 0) {
            player.sendActionBar(
                Component.translatable(
                    "farmersdelight.block.cutting_board.remaining_items",
                    Component.text(remainingItems)
                )
            )
        } else {
            player.sendActionBar(Component.empty())
        }
    }

    /** Recipe sound first; otherwise shears, knife chopping, the held block's breaking sound, then wood breaking. */
    private fun playProcessingSound(sound: String?, tool: ItemStack) {
        val itemType = tool.itemType
        val blockType = itemType.blockTypeOrNull
        if (sound != null) {
            block.playSound(sound, SoundCategory.BLOCKS, 1f, 1f)
        } else if (itemType == ItemType.SHEARS) {
            block.world.playSound(block.location, Sound.ENTITY_SHEEP_SHEAR, SoundCategory.BLOCKS, 1f, 1f)
        } else if (tool.isKnife()) {
            block.playSound(Sounds.BLOCK_CUTTING_BOARD_KNIFE, SoundCategory.BLOCKS, 0.8f, 1f)
        } else if (blockType != null) {
            block.world.playSound(block.location, blockType.createBlockData().soundGroup.breakSound, SoundCategory.BLOCKS, 1f, 0.8f)
        } else {
            block.world.playSound(block.location, Sound.BLOCK_WOOD_BREAK, SoundCategory.BLOCKS, 1f, 0.8f)
        }
    }

    private fun getMatchingRecipes(input: ItemStack): List<CuttingBoardRecipe> {
        return RecipeManager.novaRecipes[RecipeTypes.CUTTING_BOARD.get()]?.values?.asSequence()
            ?.map { it as CuttingBoardRecipe }
            ?.filter { it.input.test(input) }
            ?.toList() ?: emptyList()
    }

    private fun refreshDisplays() {
        val item = storedItemInventory.getItem(0)
        if (item == null || item.isEmpty) {
            displays.clear()
            return
        }
        val displayCount = if (item.amount > 1) {
            1 + kotlin.math.ceil(item.amount.toDouble() / item.maxStackSize * 4).toInt()
        } else {
            1
        }
        displays.show(List(displayCount) { index -> displaySpec(item, index) })
    }

    private fun displaySpec(item: ItemStack, index: Int): ItemDisplaySpec {
        val displayItem = item.clone().apply { amount = 1 }
        val location = getStackedItemLocation(displayItem, index)
        val transformation = Matrix4f(
            0.6f, 0f, 0f, 0.5f,
            0f, 0f, -0.6f, 0.8f,
            0f, 0.6f, 0f, 0.5f,
            0f, 0f, 0f, 1f
        )
        val facingRotation = when (blockState[DefaultBlockStateProperties.FACING_HORIZONTAL]) {
            BlockFace.NORTH -> transformation.rotateLocalY(Math.toRadians(180.0).toFloat())
            BlockFace.WEST -> transformation.rotateLocalY(Math.toRadians(-90.0).toFloat())
            BlockFace.EAST -> transformation.rotateLocalY(Math.toRadians(90.0).toFloat())
            else -> transformation
        }
        return ItemDisplaySpec(location, displayItem, facingRotation)
    }

    private fun getStackedItemLocation(item: ItemStack, stackIndex: Int): Location {
        val random = java.util.Random(item.itemType.key.asString().hashCode().toLong())
        var xOffset = 0.0
        var zOffset = 0.0
        repeat(stackIndex + 1) {
            xOffset = (random.nextFloat() * 2.0 - 1.0) * STACK_ITEM_HORIZONTAL_OFFSET
            zOffset = (random.nextFloat() * 2.0 - 1.0) * STACK_ITEM_HORIZONTAL_OFFSET
        }
        return getDisplayLocation().add(
            xOffset,
            STACK_ITEM_Y_OFFSET * (stackIndex + 1),
            zOffset
        )
    }

    private fun retrieveItem(player: Player) {
        val item = storedItemInventory.getItem(0)?.clone() ?: return
        storedItemInventory.setItem(SELF_UPDATE_REASON, 0, ItemStack.empty())
        isCarvingBoard = false
        if (player.gameMode != GameMode.CREATIVE) player.safeGive(item)
        block.playSound(Sound.BLOCK_WOOD_HIT, 0.25f, 0.5f)
    }

    private fun getDisplayLocation(): Location =
        block.location.clone().add(0.5, 0.05, 0.5)
}
