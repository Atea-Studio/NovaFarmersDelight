package fr.ateastudio.farmersdelight.tileentity

import fr.ateastudio.farmersdelight.block.BlockStateProperties
import fr.ateastudio.farmersdelight.block.ComparatorSignalSource
import fr.ateastudio.farmersdelight.block.ComparatorSignals
import fr.ateastudio.farmersdelight.block.comparatorFullness
import fr.ateastudio.farmersdelight.block.fullnessSlots
import fr.ateastudio.farmersdelight.tileentity.network.NetworkedInventoryViews
import fr.ateastudio.farmersdelight.util.isRedstonePowered
import xyz.xenondevs.nova.util.CubeFaceMap
import xyz.xenondevs.nova.world.block.tileentity.NetworkedTileEntity
import xyz.xenondevs.nova.world.block.tileentity.network.type.NetworkConnectionType
import xyz.xenondevs.nova.world.block.tileentity.network.type.item.inventory.NetworkedInventory
import org.bukkit.block.Block
import org.bukkit.block.BlockFace
import org.bukkit.entity.Item
import org.bukkit.inventory.ItemStack
import xyz.xenondevs.cbf.Compound
import xyz.xenondevs.invui.gui.Gui
import xyz.xenondevs.invui.inventory.VirtualInventory
import xyz.xenondevs.nova.context.Context
import xyz.xenondevs.nova.context.intention.BlockInteract
import xyz.xenondevs.nova.ui.overlay.guitexture.DefaultGuiTextures
import xyz.xenondevs.nova.ui.overlay.guitexture.getTitle
import xyz.xenondevs.nova.world.InteractionResult
import xyz.xenondevs.nova.world.block.NovaBlockState
import xyz.xenondevs.nova.world.block.name
import xyz.xenondevs.nova.world.block.tileentity.TileEntityMenu

/**
 * A 27-slot storage container mirroring the original mod's Basket. It always faces up: it collects
 * loose items inside its own block and the block directly above. While not [BlockStateProperties.ENABLED]
 * (powered by redstone), collection is paused. Hoppers can insert/extract on
 * every side, and comparators read 27-slot fullness (through the addon's comparator mixin).
 */
class Basket(
    block: Block,
    state: NovaBlockState,
    data: Compound
) : NetworkedTileEntity(block, state, data), ComparatorSignalSource {

    private val inventory: VirtualInventory =
        storedInventory("stored_items", 27, postUpdateHandler = { ComparatorSignals.notifyChanged(block) })

    // Both access views are direct inventories; there is no merged multi-inventory transfer adapter.
    private val networkedInventory = NetworkedInventoryViews(inventory)
    private val itemHolder = storedItemHolder(
        networkedInventory.all to NetworkConnectionType.BUFFER,
        networkedInventory.single to NetworkConnectionType.BUFFER,
        mergedInventory = networkedInventory.all,
        defaultInventoryConfig = CubeFaceMap<NetworkedInventory?>(networkedInventory.all)
    )

    private var cooldown: Int by storedValue<Int>("cooldown", true) { 0 }

    override fun comparatorSignal(): Int = comparatorFullness(inventory.fullnessSlots())

    override fun requestsLocalNetwork(face: BlockFace): Boolean =
        itemHolder.connectionConfig[face] != NetworkConnectionType.BUFFER

    override fun useItemOn(ctx: Context<BlockInteract>): InteractionResult {
        val player = ctx[BlockInteract.SOURCE_PLAYER] ?: return InteractionResult.Pass
        return if (menu.open(player)) InteractionResult.Success(swing = true) else InteractionResult.Pass
    }

    override fun handleTick() {
        // Vanilla `ENABLED = !hasNeighborSignal`, owned by the tile entity so its state never goes stale.
        val enabled = !block.isRedstonePowered()
        updateLiveBlockState { this[BlockStateProperties.ENABLED] = enabled }

        if (cooldown > 0) {
            cooldown--
            return
        }

        if (!enabled || inventory.isFull) return

        if (collectNearbyItems()) cooldown = 8
    }

    private fun collectNearbyItems(): Boolean {
        // The basket's own full cube plus the full cube above it (the opening).
        val location = block.location.add(0.5, 1.0, 0.5)
        val items = block.world.getNearbyEntities(location, 0.5, 1.0, 0.5)
            .filterIsInstance<Item>()
            .filter { !it.isDead && it.pickupDelay <= 0 }

        for (itemEntity in items) {
            if (collectItem(itemEntity)) return true
        }
        return false
    }

    private fun collectItem(itemEntity: Item): Boolean {
        val stack = itemEntity.itemStack
        val leftover = insert(stack.clone())
        return if (leftover.isEmpty) {
            itemEntity.remove()
            true
        } else {
            if (leftover.amount != stack.amount) {
                itemEntity.itemStack = leftover
                true
            } else {
                false
            }
        }
    }

    private fun insert(stack: ItemStack): ItemStack {
        // InvUI's addItem returns the amount that could NOT be added.
        val leftover = inventory.addItem(SELF_UPDATE_REASON, stack)
        return if (leftover <= 0) ItemStack.empty() else stack.clone().apply { amount = leftover }
    }

    private fun basketGui(): Gui = Gui.builder()
        .setStructure(
            "i i i i i i i i i",
            "i i i i i i i i i",
            "i i i i i i i i i"
        )
        .addIngredient('i', inventory)
        .build()

    @OptIn(xyz.xenondevs.invui.dsl.ExperimentalDslApi::class)
    override val menu: TileEntityMenu = TileEntityMenu.cachedWindow(DefaultGuiTextures.GENERIC_9x3) {
        title by DefaultGuiTextures.GENERIC_9x3.getTitle(blockType.name)
        upperGui by basketGui()
    }
}
