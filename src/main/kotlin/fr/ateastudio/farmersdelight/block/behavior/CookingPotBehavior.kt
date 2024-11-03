package fr.ateastudio.farmersdelight.block.behavior

import fr.ateastudio.farmersdelight.block.BlockStateProperties
import fr.ateastudio.farmersdelight.block.CookingPotSupport
import org.bukkit.Material
import xyz.xenondevs.nova.context.Context
import xyz.xenondevs.nova.context.intention.DefaultContextIntentions.BlockPlace
import xyz.xenondevs.nova.util.BlockUtils.updateBlockState
import xyz.xenondevs.nova.world.BlockPos
import xyz.xenondevs.nova.world.block.behavior.BlockBehavior
import xyz.xenondevs.nova.world.block.state.NovaBlockState

object CookingPotBehavior : BlockBehavior {
    override fun handlePlace(pos: BlockPos, state: NovaBlockState, ctx: Context<BlockPlace>) {
        updateSupport(pos, state)
    }
    
    override fun handleNeighborChanged(pos: BlockPos, state: NovaBlockState, neighborPos: BlockPos) {
        updateSupport(pos, state)
    }
    
    private fun updateSupport(pos: BlockPos, state: NovaBlockState) {
        val isSuspended = !pos.add(0,1,0).block.isEmpty && pos.below.block.isEmpty
        val isTray = pos.below.block.type == Material.CAMPFIRE
        if (isSuspended) {
            updateBlockState(pos, state.with(BlockStateProperties.SUPPORT, CookingPotSupport.HANDLE))
        }
        else if (isTray) {
            updateBlockState(pos, state.with(BlockStateProperties.SUPPORT, CookingPotSupport.TRAY))
        }
        else {
            updateBlockState(pos, state.with(BlockStateProperties.SUPPORT, CookingPotSupport.NONE))
        }
    }
}