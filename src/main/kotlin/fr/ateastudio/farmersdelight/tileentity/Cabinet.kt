package fr.ateastudio.farmersdelight.tileentity

import fr.ateastudio.farmersdelight.block.BlockStateProperties
import fr.ateastudio.farmersdelight.block.ComparatorSignalSource
import fr.ateastudio.farmersdelight.block.ComparatorSignals
import fr.ateastudio.farmersdelight.block.comparatorFullness
import fr.ateastudio.farmersdelight.block.fullnessSlots
import fr.ateastudio.farmersdelight.registry.Sounds
import fr.ateastudio.farmersdelight.tileentity.network.NetworkedInventoryViews
import fr.ateastudio.farmersdelight.util.random
import xyz.xenondevs.nova.util.CubeFaceMap
import xyz.xenondevs.nova.world.block.tileentity.NetworkedTileEntity
import xyz.xenondevs.nova.world.block.tileentity.network.type.NetworkConnectionType
import xyz.xenondevs.nova.world.block.tileentity.network.type.item.inventory.NetworkedInventory
import org.bukkit.SoundCategory
import org.bukkit.block.Block
import org.bukkit.block.BlockFace
import xyz.xenondevs.cbf.Compound
import xyz.xenondevs.invui.gui.Gui
import xyz.xenondevs.invui.inventory.VirtualInventory
import xyz.xenondevs.nova.context.Context
import xyz.xenondevs.nova.context.intention.BlockInteract
import xyz.xenondevs.nova.ui.overlay.guitexture.DefaultGuiTextures
import xyz.xenondevs.nova.ui.overlay.guitexture.getTitle
import xyz.xenondevs.nova.util.playSound
import xyz.xenondevs.nova.world.InteractionResult
import xyz.xenondevs.nova.world.block.NovaBlockState
import xyz.xenondevs.nova.world.block.name
import xyz.xenondevs.nova.world.block.tileentity.TileEntityMenu

/**
 * A 27-slot storage container, mirroring the original mod's Cabinet (a barrel-like block
 * available in all wood variants). Hoppers can insert/extract on every side, and comparators
 * read 27-slot fullness (through the addon's comparator mixin).
 */
class Cabinet(
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

    override fun comparatorSignal(): Int = comparatorFullness(inventory.fullnessSlots())

    override fun requestsLocalNetwork(face: BlockFace): Boolean =
        itemHolder.connectionConfig[face] != NetworkConnectionType.BUFFER

    private var openerCount = 0

    override fun useItemOn(ctx: Context<BlockInteract>): InteractionResult {
        val player = ctx[BlockInteract.SOURCE_PLAYER] ?: return InteractionResult.Pass
        return if (menu.open(player)) InteractionResult.Success(swing = true) else InteractionResult.Pass
    }

    private fun setOpen(open: Boolean) {
        // A fresh state is required: updateBlockState ignores the tile entity's own (mutated) state object.
        updateLiveBlockState { this[BlockStateProperties.OPEN] = open }
    }

    private fun onPlayerOpened() {
        openerCount++
        if (openerCount == 1) {
            setOpen(true)
            block.playSound(Sounds.BLOCK_CABINET_OPEN, SoundCategory.BLOCKS, 0.5f, block.world.random().nextFloat() * 0.1f + 0.9f)
        }
    }

    private fun onPlayerClosed() {
        openerCount = (openerCount - 1).coerceAtLeast(0)
        if (openerCount == 0) {
            setOpen(false)
            block.playSound(Sounds.BLOCK_CABINET_CLOSE, SoundCategory.BLOCKS, 0.5f, block.world.random().nextFloat() * 0.1f + 0.9f)
        }
    }

    private fun cabinetGui(): Gui = Gui.builder()
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
        upperGui by cabinetGui()
        onOpen { onPlayerOpened() }
        onClose { onPlayerClosed() }
    }
}
