package fr.ateastudio.farmersdelight.tileentity

import xyz.xenondevs.nova.world.block.NovaBlockState
import xyz.xenondevs.nova.world.block.novaBlockState
import xyz.xenondevs.nova.world.block.tileentity.TileEntity

/**
 * The block state currently in the world. A tile entity's own [TileEntity.blockState] only follows changes
 * made through [TileEntity.updateBlockState]; changes Nova applies itself (e.g. waterlogging with a bucket)
 * are only visible here.
 */
internal val TileEntity.liveBlockState: NovaBlockState
    get() = block.novaBlockState ?: blockState

/**
 * Changes this tile entity's block state the Nova way ([TileEntity.updateBlockState]), starting from the
 * [live state][liveBlockState] so that changes made by Nova (e.g. waterlogging) are never reverted.
 * Does nothing when [change] leaves the state as it is. The tile entity must be enabled.
 */
internal fun TileEntity.updateLiveBlockState(change: NovaBlockState.() -> Unit) {
    val live = liveBlockState
    val updated = live.clone().apply(change)
    if (updated != live) updateBlockState(updated)
}
