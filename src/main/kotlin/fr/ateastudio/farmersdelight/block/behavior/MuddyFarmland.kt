package fr.ateastudio.farmersdelight.block.behavior

import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.block.BlockType
import org.bukkit.inventory.ItemStack
import xyz.xenondevs.nova.context.Context
import xyz.xenondevs.nova.context.intention.BlockBreak
import xyz.xenondevs.nova.context.intention.BlockPlace
import xyz.xenondevs.nova.util.BlockUtils
import xyz.xenondevs.nova.util.above
import xyz.xenondevs.nova.util.item.toItemStack
import xyz.xenondevs.nova.world.BlockPos
import xyz.xenondevs.nova.world.block.behavior.BlockBehavior
import xyz.xenondevs.nova.world.block.state.NovaBlockState

object MuddyFarmland : BlockBehavior {
    //TODO implement breaking to mud logic
    override fun handleNeighborChanged(pos: BlockPos, state: NovaBlockState) {
        if (pos.block.above.isPassable || pos.block.above.isEmpty) return
        val context = Context.intention(BlockPlace)
            .param(BlockPlace.BLOCK_POS, pos)
            .param(BlockPlace.BLOCK_TYPE_VANILLA, BlockType.MUD)
            .param(BlockPlace.BLOCK_PLACE_EFFECTS, true)
            .build()
        BlockUtils.placeBlock(context)
    }
    
    override fun getDrops(pos: BlockPos, state: NovaBlockState, ctx: Context<BlockBreak>): List<ItemStack> {
        if (!ctx[BlockBreak.BLOCK_DROPS] || ctx[BlockBreak.SOURCE_PLAYER]?.gameMode == GameMode.CREATIVE)
            return emptyList()
        return listOf(Material.MUD.toItemStack())
    }
    
}