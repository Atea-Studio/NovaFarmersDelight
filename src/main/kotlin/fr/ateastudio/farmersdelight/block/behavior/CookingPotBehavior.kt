package fr.ateastudio.farmersdelight.block.behavior

import fr.ateastudio.farmersdelight.block.BlockStateProperties
import fr.ateastudio.farmersdelight.block.CookingPotSupport
import fr.ateastudio.farmersdelight.util.LogDebug
import org.bukkit.Material
import org.bukkit.block.BlockFace
import xyz.xenondevs.nova.context.Context
import xyz.xenondevs.nova.context.intention.DefaultContextIntentions.BlockPlace
import xyz.xenondevs.nova.context.param.DefaultContextParamTypes
import xyz.xenondevs.nova.util.BlockUtils.updateBlockState
import xyz.xenondevs.nova.world.BlockPos
import xyz.xenondevs.nova.world.block.behavior.BlockBehavior
import xyz.xenondevs.nova.world.block.state.NovaBlockState

object CookingPotBehavior : BlockBehavior {
    override fun handlePlace(pos: BlockPos, state: NovaBlockState, ctx: Context<BlockPlace>) {
        val blockFace = ctx[DefaultContextParamTypes.CLICKED_BLOCK_FACE]
        updateBlockState(pos, state.with(BlockStateProperties.SUPPORT, updateSupport(pos, state, blockFace))
            .with(BlockStateProperties.HEATED,updateHeated(pos, state)))
        
    }
    
    override fun handleNeighborChanged(pos: BlockPos, state: NovaBlockState, neighborPos: BlockPos) {
        updateBlockState(pos, state.with(BlockStateProperties.SUPPORT, updateSupport(pos, state))
            .with(BlockStateProperties.HEATED,updateHeated(pos, state)))
    }
    
    private fun updateHeated(pos: BlockPos, state: NovaBlockState) : Boolean {
        return pos.below.block.type == Material.CAMPFIRE
    }
    
    private fun updateSupport(pos: BlockPos, state: NovaBlockState, blockFace: BlockFace? = null) : CookingPotSupport{
        val isSuspended = !pos.add(0,1,0).block.isEmpty
        val isTray = pos.below.block.type == Material.CAMPFIRE
        LogDebug("isSuspended: $isSuspended")
        LogDebug("isTray: $isTray")
        LogDebug("blockFace: $blockFace")
        
        if (isSuspended && isTray && blockFace != null) {
            if (blockFace == BlockFace.DOWN) {
                return CookingPotSupport.HANDLE
            }
            else {
                return CookingPotSupport.TRAY
            }
        }
        else if (isSuspended) {
            return CookingPotSupport.HANDLE
        }
        else if (isTray) {
            return CookingPotSupport.TRAY
        }
        else {
            return CookingPotSupport.NONE
        }
    }
}