package fr.ateastudio.farmersdelight.block.behavior

import fr.ateastudio.farmersdelight.registry.Blocks
import org.bukkit.block.Block
import xyz.xenondevs.nova.context.Context
import xyz.xenondevs.nova.context.intention.BlockBreak
import xyz.xenondevs.nova.util.BlockUtils.breakBlockNaturally
import xyz.xenondevs.nova.world.block.NovaBlockState
import xyz.xenondevs.nova.world.block.behavior.BlockBehavior
import xyz.xenondevs.nova.world.block.blockType
import xyz.xenondevs.nova.world.block.state.property.DefaultBlockStateProperties

object TatamiMatFoot : BlockBehavior {
    
    override fun handleBreak(pos: Block, state: NovaBlockState, ctx: Context<BlockBreak>) {
        val face = state[DefaultBlockStateProperties.FACING_HORIZONTAL]!!
        val footPos = pos.getRelative(face.oppositeFace)
        if (footPos.blockType == Blocks.FULL_TATAMI_MAT_HEAD.get()) {
            val contextFoot = Context.intention(BlockBreak)
                .param(BlockBreak.BLOCK, footPos)
                .param(BlockBreak.SOURCE_ENTITY, ctx[BlockBreak.SOURCE_PLAYER])
                .build()
            breakBlockNaturally(contextFoot)
        }
    }
    
    
}