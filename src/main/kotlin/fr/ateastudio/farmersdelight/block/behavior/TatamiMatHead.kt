package fr.ateastudio.farmersdelight.block.behavior

import fr.ateastudio.farmersdelight.registry.Blocks
import org.bukkit.Tag.REPLACEABLE
import org.bukkit.block.Block
import xyz.xenondevs.nova.context.Context
import xyz.xenondevs.nova.context.intention.BlockBreak
import xyz.xenondevs.nova.context.intention.BlockPlace
import xyz.xenondevs.nova.integration.protection.ProtectionManager
import xyz.xenondevs.nova.util.BlockUtils.breakBlockNaturally
import xyz.xenondevs.nova.util.BlockUtils.placeBlock
import xyz.xenondevs.nova.world.block.NovaBlockState
import xyz.xenondevs.nova.world.block.behavior.BlockBehavior
import xyz.xenondevs.nova.world.block.blockType
import xyz.xenondevs.nova.world.block.novaBlockState
import xyz.xenondevs.nova.world.block.state.property.DefaultBlockStateProperties

object TatamiMatHead : BlockBehavior {
    
    override fun handlePlace(pos: Block, state: NovaBlockState, ctx: Context<BlockPlace>) {
        val face = state[DefaultBlockStateProperties.FACING_HORIZONTAL]!!
        val footPos = pos.getRelative(face.oppositeFace)
        val footMat = Blocks.FULL_TATAMI_MAT_FOOT.get().createBlockData() as NovaBlockState
        footMat[DefaultBlockStateProperties.FACING_HORIZONTAL] = face.oppositeFace
        val contextFoot = Context.intention(BlockPlace)
            .param(BlockPlace.BLOCK, footPos)
            .param(BlockPlace.BLOCK_STATE, footMat)
            .build()
        placeBlock(contextFoot)
    }
    
    override suspend fun canPlace(pos: Block, state: NovaBlockState, ctx: Context<BlockPlace>): Boolean {
        if (!REPLACEABLE.isTagged(pos.type) || pos.novaBlockState != null) {
            return false
        }
        
        if (!ProtectionManager.canPlace(ctx))
            return false
        
        val face = state[DefaultBlockStateProperties.FACING_HORIZONTAL]!!
        val footPos = pos.getRelative(face.oppositeFace)
        return !(!REPLACEABLE.isTagged(footPos.type) || footPos.novaBlockState != null)
    }
    
    override fun handleBreak(pos: Block, state: NovaBlockState, ctx: Context<BlockBreak>) {
        val face = state[DefaultBlockStateProperties.FACING_HORIZONTAL]!!
        val footPos = pos.getRelative(face.oppositeFace)
        if (footPos.blockType == Blocks.FULL_TATAMI_MAT_FOOT.get()) {
            val contextFoot = Context.intention(BlockBreak)
                .param(BlockBreak.BLOCK, footPos)
                .param(BlockBreak.SOURCE_ENTITY, ctx[BlockBreak.SOURCE_PLAYER])
                .build()
            breakBlockNaturally(contextFoot)
        }
    }
    
    
}