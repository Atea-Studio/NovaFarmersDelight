package fr.ateastudio.farmersdelight.block

import fr.ateastudio.farmersdelight.NovaFarmersDelight
import xyz.xenondevs.nova.util.ResourceLocation
import xyz.xenondevs.nova.world.block.state.property.impl.IntProperty

object BlockStateProperties {
    val AGE = IntProperty(ResourceLocation(NovaFarmersDelight, "age"))
    val MAX_AGE = IntProperty(ResourceLocation(NovaFarmersDelight, "max_age"))
}

object ScopedBlockStateProperties {
    val AGE = BlockStateProperties.AGE.scope(0..7)
    val MAX_AGE = BlockStateProperties.MAX_AGE.scope(0..7)
}