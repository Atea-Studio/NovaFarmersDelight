package fr.ateastudio.farmersdelight.block.behavior

import fr.ateastudio.farmersdelight.block.BlockStateProperties
import xyz.xenondevs.nova.context.Context
import xyz.xenondevs.nova.context.intention.DefaultContextIntentions.BlockPlace
import xyz.xenondevs.nova.util.BlockUtils.updateBlockState
import xyz.xenondevs.nova.world.BlockPos
import xyz.xenondevs.nova.world.block.behavior.BlockBehavior
import xyz.xenondevs.nova.world.block.state.NovaBlockState

open class Ageable(private val maxAge: Int, private val buddingAge: Int = 0) : BlockBehavior {
    
    override fun handlePlace(pos: BlockPos, state: NovaBlockState, ctx: Context<BlockPlace>) {
        updateBlockState(pos, state.with(BlockStateProperties.MAX_AGE, maxAge)
            .with(BlockStateProperties.BUDDING_AGE, buddingAge))
    }
    
}