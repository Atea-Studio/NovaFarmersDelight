package fr.ateastudio.farmersdelight.block.behavior

import org.bukkit.GameMode
import org.bukkit.block.Block
import org.bukkit.inventory.ItemStack
import xyz.xenondevs.nova.context.Context
import xyz.xenondevs.nova.context.intention.BlockBreak
import xyz.xenondevs.nova.world.block.NovaBlockState
import xyz.xenondevs.nova.world.block.behavior.BlockBehavior

/**
 * Cooking pot block rules. Support and heated states are set at placement by their autofillers
 * ([fr.ateastudio.farmersdelight.block.BlockStateProperties]) and afterwards kept up to date by the
 * cooking pot tile entity itself, so its block state never goes stale.
 */
object CookingPotBehavior : BlockBehavior {

    override fun getDrops(block: Block, state: NovaBlockState, ctx: Context<BlockBreak>): List<ItemStack> {
        if (!ctx[BlockBreak.BLOCK_DROPS] || ctx[BlockBreak.SOURCE_PLAYER]?.gameMode == GameMode.CREATIVE)
            return emptyList()
        return super.getDrops(block, state, ctx)
    }
}
