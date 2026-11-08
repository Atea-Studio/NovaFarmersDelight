package fr.ateastudio.farmersdelight.block.behavior

import org.bukkit.block.Block
import org.bukkit.block.BlockFace
import org.bukkit.block.BlockSupport
import xyz.xenondevs.nova.context.Context
import xyz.xenondevs.nova.context.intention.BlockBreak
import xyz.xenondevs.nova.context.intention.BlockPlace
import xyz.xenondevs.nova.util.BlockUtils
import xyz.xenondevs.nova.util.below
import xyz.xenondevs.nova.world.block.NovaBlockState
import xyz.xenondevs.nova.world.block.behavior.BlockBehavior

/** The board needs rigid or upper-face-center support below; losing it removes the board. */
object CuttingBoardBehavior : BlockBehavior {

    override suspend fun canPlace(pos: Block, state: NovaBlockState, ctx: Context<BlockPlace>): Boolean =
        hasSupport(pos)

    override fun handleNeighborChanged(pos: Block, state: NovaBlockState) {
        if (hasSupport(pos)) return
        val context = Context.intention(BlockBreak)
            .param(BlockBreak.BLOCK, pos)
            .param(BlockBreak.BLOCK_BREAK_EFFECTS, true)
            .build()
        BlockUtils.breakBlockNaturally(context)
    }

    private fun hasSupport(pos: Block): Boolean {
        val below = pos.below.blockData
        return below.isFaceSturdy(BlockFace.UP, BlockSupport.RIGID) || below.isFaceSturdy(BlockFace.UP, BlockSupport.CENTER)
    }
}
