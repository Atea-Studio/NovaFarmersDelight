package fr.ateastudio.farmersdelight.block.behavior

import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import xyz.xenondevs.nova.context.Context
import xyz.xenondevs.nova.context.intention.DefaultContextIntentions
import xyz.xenondevs.nova.context.param.DefaultContextParamTypes
import xyz.xenondevs.nova.util.BlockUtils
import xyz.xenondevs.nova.util.item.toItemStack
import xyz.xenondevs.nova.world.BlockPos
import xyz.xenondevs.nova.world.block.behavior.BlockBehavior
import xyz.xenondevs.nova.world.block.state.NovaBlockState

object MuddyFarmland : BlockBehavior {
    //TODO implement breaking to mud logic
    override fun handleNeighborChanged(pos: BlockPos, state: NovaBlockState, neighborPos: BlockPos) {
        if (neighborPos.below != pos) return
        if (neighborPos.block.isPassable) return
        val context = Context.intention(DefaultContextIntentions.BlockPlace)
            .param(DefaultContextParamTypes.BLOCK_POS, pos)
            .param(DefaultContextParamTypes.BLOCK_TYPE_VANILLA, Material.MUD)
            .param(DefaultContextParamTypes.BLOCK_PLACE_EFFECTS, true)
            .build()
        BlockUtils.placeBlock(context)
    }
    
    override fun getDrops(pos: BlockPos, state: NovaBlockState, ctx: Context<DefaultContextIntentions.BlockBreak>): List<ItemStack> {
        if (ctx[DefaultContextParamTypes.SOURCE_PLAYER]?.gameMode == GameMode.CREATIVE)
            return emptyList()
        return listOf(Material.MUD.toItemStack())
    }
    
}