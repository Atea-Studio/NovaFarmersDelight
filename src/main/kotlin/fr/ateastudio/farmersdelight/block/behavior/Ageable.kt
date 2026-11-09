package fr.ateastudio.farmersdelight.block.behavior

import fr.ateastudio.farmersdelight.block.BlockStateProperties
import org.bukkit.block.Block
import xyz.xenondevs.nova.context.Context
import xyz.xenondevs.nova.context.intention.BlockPlace
import xyz.xenondevs.nova.world.block.NovaBlockState
import xyz.xenondevs.nova.world.block.behavior.BlockBehavior

open class Ageable(private val maxAge: Int, private val buddingAge: Int = 0) : BlockBehavior {
    
    override fun handlePlace(block: Block, state: NovaBlockState, ctx: Context<BlockPlace>) {
        state[BlockStateProperties.MAX_AGE] = maxAge
        state[BlockStateProperties.BUDDING_AGE] = buddingAge.coerceAtLeast(0)
        block.blockData = state
    }
    
}