package fr.ateastudio.farmersdelight.block.behavior

import fr.ateastudio.farmersdelight.registry.Items
import fr.ateastudio.farmersdelight.tileentity.Skillet
import org.bukkit.block.Block
import org.bukkit.inventory.ItemStack
import xyz.xenondevs.nova.context.Context
import xyz.xenondevs.nova.context.intention.BlockInteract
import xyz.xenondevs.nova.context.intention.BlockPlace
import xyz.xenondevs.nova.world.block.NovaBlockState
import xyz.xenondevs.nova.world.block.behavior.BlockBehavior
import xyz.xenondevs.nova.world.block.novaTileEntity
import xyz.xenondevs.nova.world.item.createItemStack

/**
 * Players may only place the skillet while sneaking. Its placement state (facing WITH the player, tray,
 * waterlogging) is computed by [fr.ateastudio.farmersdelight.item.HandheldSkillet]; the tray is then kept
 * up to date by the [Skillet] tile entity, matching the original mod's `SkilletBlock#getTrayState`.
 */
object SkilletBehavior : BlockBehavior {

    override suspend fun canPlace(block: Block, data: NovaBlockState, ctx: Context<BlockPlace>): Boolean {
        val player = ctx[BlockPlace.SOURCE_PLAYER] ?: return true
        return player.isSneaking
    }

    /** The block has no linked item type, so pick-block returns the stored skillet item. */
    override fun pickBlockCreative(block: Block, state: NovaBlockState, ctx: Context<BlockInteract>): ItemStack =
        (block.novaTileEntity as? Skillet)?.skilletItem() ?: Items.SKILLET.createItemStack()
}
