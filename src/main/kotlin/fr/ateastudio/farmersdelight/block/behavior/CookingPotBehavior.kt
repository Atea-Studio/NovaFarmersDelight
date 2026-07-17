package fr.ateastudio.farmersdelight.block.behavior

import fr.ateastudio.farmersdelight.block.BlockStateProperties
import fr.ateastudio.farmersdelight.block.CookingPotSupport
import fr.ateastudio.farmersdelight.registry.Tags
import fr.ateastudio.farmersdelight.util.isTag
import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.block.BlockFace
import org.bukkit.block.data.type.Campfire
import org.bukkit.inventory.ItemStack
import xyz.xenondevs.nova.context.Context
import xyz.xenondevs.nova.context.intention.BlockBreak
import xyz.xenondevs.nova.context.intention.BlockPlace
import xyz.xenondevs.nova.util.BlockUtils.updateBlockState
import xyz.xenondevs.nova.world.BlockPos
import xyz.xenondevs.nova.world.block.behavior.BlockBehavior
import xyz.xenondevs.nova.world.block.state.NovaBlockState

object CookingPotBehavior : BlockBehavior {
    
    override fun handlePlace(pos: BlockPos, state: NovaBlockState, ctx: Context<BlockPlace>) {
        val blockFace = ctx[BlockPlace.CLICKED_BLOCK_FACE]
        updateBlockState(pos, state.with(BlockStateProperties.SUPPORT, updateSupport(pos, blockFace))
            .with(BlockStateProperties.HEATED,updateHeated(pos)))
        
    }
    
    override fun handleNeighborChanged(pos: BlockPos, state: NovaBlockState) {
        updateBlockState(pos, state.with(BlockStateProperties.SUPPORT, updateSupport(pos))
            .with(BlockStateProperties.HEATED,updateHeated(pos)))
    }
    
    override fun getDrops(pos: BlockPos, state: NovaBlockState, ctx: Context<BlockBreak>): List<ItemStack> {
        if (!ctx[BlockBreak.BLOCK_DROPS] || ctx[BlockBreak.SOURCE_PLAYER]?.gameMode == GameMode.CREATIVE)
            return emptyList()
        return super.getDrops(pos, state, ctx)
    }
        
        
        private fun updateHeated(pos: BlockPos) : Boolean {
        if (pos.below.block.isTag(Tags.HEAT_SOURCE) || pos.below.block.isTag(Tags.TRAY_HEAT_SOURCE)) {
            if (pos.below.block.type == Material.CAMPFIRE || pos.below.block.type == Material.SOUL_CAMPFIRE) {
                val campFire = pos.below.block.blockData as Campfire
                return campFire.isLit
            }
            return true
        }
        return false
    }
    
    private fun updateSupport(pos: BlockPos, blockFace: BlockFace? = null) : CookingPotSupport{
        val isSuspended = !pos.add(0,1,0).block.isEmpty
        val isTray = pos.below.block.isTag(Tags.TRAY_HEAT_SOURCE)
        
        if (isSuspended && isTray && blockFace != null) {
            return if (blockFace == BlockFace.DOWN) {
                CookingPotSupport.HANDLE
            } else {
                CookingPotSupport.TRAY
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