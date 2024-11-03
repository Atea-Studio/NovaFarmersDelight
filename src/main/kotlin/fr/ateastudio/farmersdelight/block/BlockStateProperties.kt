package fr.ateastudio.farmersdelight.block

import fr.ateastudio.farmersdelight.NovaFarmersDelight
import xyz.xenondevs.nova.util.ResourceLocation
import xyz.xenondevs.nova.world.block.state.property.impl.BooleanProperty
import xyz.xenondevs.nova.world.block.state.property.impl.EnumProperty
import xyz.xenondevs.nova.world.block.state.property.impl.IntProperty

object BlockStateProperties {
    val AGE = IntProperty(ResourceLocation(NovaFarmersDelight, "age"))
    val MAX_AGE = IntProperty(ResourceLocation(NovaFarmersDelight, "max_age"))
    val BUDDING_AGE = IntProperty(ResourceLocation(NovaFarmersDelight, "budding_age"))
    val HEATED = BooleanProperty(ResourceLocation(NovaFarmersDelight, "heated"))
    val SUPPORT = EnumProperty<CookingPotSupport>(ResourceLocation(NovaFarmersDelight, "support"))
}

object ScopedBlockStateProperties {
    val AGE = BlockStateProperties.AGE.scope(0..7)
    val MAX_AGE = BlockStateProperties.MAX_AGE.scope(0..7)
    val BUDDING_AGE = BlockStateProperties.BUDDING_AGE.scope(-1..7)
    val HEATED = BlockStateProperties.HEATED.scope(true, false)
    val SUPPORT = BlockStateProperties.SUPPORT.scope(CookingPotSupport.NONE, CookingPotSupport.HANDLE, CookingPotSupport.TRAY)
}