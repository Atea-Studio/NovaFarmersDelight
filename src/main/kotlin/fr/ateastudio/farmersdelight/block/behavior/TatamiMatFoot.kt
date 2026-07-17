package fr.ateastudio.farmersdelight.block.behavior

import fr.ateastudio.farmersdelight.registry.Blocks
import fr.ateastudio.farmersdelight.util.relative
import xyz.xenondevs.nova.context.Context
import xyz.xenondevs.nova.context.intention.BlockBreak
import xyz.xenondevs.nova.util.BlockUtils.breakBlockNaturally
import xyz.xenondevs.nova.world.BlockPos
import xyz.xenondevs.nova.world.block.behavior.BlockBehavior
import xyz.xenondevs.nova.world.block.state.NovaBlockState
import xyz.xenondevs.nova.world.block.state.property.DefaultBlockStateProperties

object TatamiMatFoot : BlockBehavior {
    
    override fun handleBreak(pos: BlockPos, state: NovaBlockState, ctx: Context<BlockBreak>) {
        val face = state[DefaultBlockStateProperties.FACING]!!
        val footPos = pos.relative(face.oppositeFace)
        if (footPos.novaBlockState?.block == Blocks.FULL_TATAMI_MAT_HEAD) {
            val contextFoot = Context.intention(BlockBreak)
                .param(BlockBreak.BLOCK_POS, footPos)
                .param(BlockBreak.SOURCE_ENTITY, ctx[BlockBreak.SOURCE_PLAYER])
                .build()
            breakBlockNaturally(contextFoot)
        }
    }
    
    
}