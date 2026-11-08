package fr.ateastudio.farmersdelight.block.behavior

import fr.ateastudio.farmersdelight.block.BlockStateProperties
import fr.ateastudio.farmersdelight.registry.Blocks
import org.bukkit.block.Block
import org.bukkit.block.BlockFace
import xyz.xenondevs.nova.context.Context
import xyz.xenondevs.nova.context.intention.BlockBreak
import xyz.xenondevs.nova.context.intention.BlockPlace
import xyz.xenondevs.nova.world.block.NovaBlockState
import xyz.xenondevs.nova.world.block.behavior.BlockBehavior
import xyz.xenondevs.nova.world.block.blockType
import xyz.xenondevs.nova.world.block.novaBlockState
import xyz.xenondevs.nova.world.block.state.property.DefaultBlockStateProperties

object PairedBlock : BlockBehavior {
    
    override fun handlePlace(pos: Block, state: NovaBlockState, ctx: Context<BlockPlace>) {
        val face = ctx[BlockPlace.CLICKED_BLOCK_FACE]
        pos.blockData = getStateForPlacement(pos, face)
    }
    
    override fun handleBreak(pos: Block, state: NovaBlockState, ctx: Context<BlockBreak>) {
        val face = state[DefaultBlockStateProperties.FACING_CARTESIAN]
        val paired = state[BlockStateProperties.PAIRED]
        
        if (face != null && paired == true) {
            val targetBlock = pos.getRelative(face.oppositeFace)
            val targetState = targetBlock.novaBlockState ?: return
            targetState[BlockStateProperties.PAIRED] = false
            targetBlock.blockData = targetState
        }
    }
    
    private fun freshTatamiState(facing: BlockFace, paired: Boolean) : NovaBlockState {
        val newState = Blocks.TATAMI.get().createBlockData() as NovaBlockState
        newState[BlockStateProperties.PAIRED] = paired
        newState[DefaultBlockStateProperties.FACING_CARTESIAN] = facing
        return newState
    }
    
    private fun getStateForPlacement(pos: Block, face: BlockFace?) : NovaBlockState {
        if (face == null) return freshTatamiState(BlockFace.DOWN, false)
        
        val targetBlock = pos.getRelative(face.oppositeFace)
        val targetState = targetBlock.novaBlockState
        
        if (targetState == null || targetBlock.blockType != Blocks.TATAMI.get())
            return freshTatamiState(face, false)
        val targetPaired = targetState[BlockStateProperties.PAIRED]
        
        if (targetPaired != true) {
            targetBlock.blockData = freshTatamiState(face.oppositeFace, true)
            return freshTatamiState(face, true)
        }
        return freshTatamiState(face, false)
    }
}