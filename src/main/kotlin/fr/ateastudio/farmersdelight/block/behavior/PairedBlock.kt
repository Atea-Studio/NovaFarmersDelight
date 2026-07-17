package fr.ateastudio.farmersdelight.block.behavior

import fr.ateastudio.farmersdelight.block.BlockStateProperties
import fr.ateastudio.farmersdelight.registry.Blocks
import fr.ateastudio.farmersdelight.util.relative
import org.bukkit.block.BlockFace
import xyz.xenondevs.nova.context.Context
import xyz.xenondevs.nova.context.intention.BlockBreak
import xyz.xenondevs.nova.context.intention.BlockPlace
import xyz.xenondevs.nova.util.BlockUtils.updateBlockState
import xyz.xenondevs.nova.world.BlockPos
import xyz.xenondevs.nova.world.block.behavior.BlockBehavior
import xyz.xenondevs.nova.world.block.state.NovaBlockState
import xyz.xenondevs.nova.world.block.state.property.DefaultBlockStateProperties
import xyz.xenondevs.nova.world.format.WorldDataManager

object PairedBlock : BlockBehavior {
    
    override fun handlePlace(pos: BlockPos, state: NovaBlockState, ctx: Context<BlockPlace>) {
        val face = ctx[BlockPlace.CLICKED_BLOCK_FACE]
        val newState = getStateForPlacement(pos, face)
        updateBlockState(pos, newState)
    }
    
    override fun handleBreak(pos: BlockPos, state: NovaBlockState, ctx: Context<BlockBreak>) {
        val face = state[DefaultBlockStateProperties.FACING]
        val paired = state[BlockStateProperties.PAIRED]
        
        if (face != null && paired == true) {
            val targetPos = pos.relative(face.oppositeFace)
            updateBlockState(targetPos, state
                .with(BlockStateProperties.PAIRED, false))
        }
    }
    
    private fun getStateForPlacement(pos: BlockPos, face: BlockFace?) : NovaBlockState {
        if (face == null) return  Blocks.TATAMI.defaultBlockState
            .with(BlockStateProperties.PAIRED, false)
            .with(DefaultBlockStateProperties.FACING, BlockFace.DOWN)
        
        val defaultState = Blocks.TATAMI.defaultBlockState
            .with(BlockStateProperties.PAIRED, false)
            .with(DefaultBlockStateProperties.FACING, face)
        
        val targetPos = pos.relative(face.oppositeFace)
        val targetState = WorldDataManager.getBlockState(targetPos) ?: return defaultState
        
        if (targetState.block != Blocks.TATAMI)
            return defaultState
        val targetPaired = targetState[BlockStateProperties.PAIRED]
        
        if (targetPaired != true) {
            updateBlockState(targetPos, Blocks.TATAMI.defaultBlockState
                .with(BlockStateProperties.PAIRED, true)
                .with(DefaultBlockStateProperties.FACING, face.oppositeFace))
            return Blocks.TATAMI.defaultBlockState
                .with(BlockStateProperties.PAIRED, true)
                .with(DefaultBlockStateProperties.FACING, face)
        }
        return defaultState
    }
}