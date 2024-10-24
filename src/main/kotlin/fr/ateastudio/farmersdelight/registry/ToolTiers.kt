package fr.ateastudio.farmersdelight.registry

import fr.ateastudio.farmersdelight.NovaFarmersDelight
import xyz.xenondevs.nova.addon.registry.ToolTierRegistry
import xyz.xenondevs.nova.initialize.Init
import xyz.xenondevs.nova.initialize.InitStage

@Suppress("unused")
@Init(stage = InitStage.PRE_PACK)
object ToolTiers : ToolTierRegistry by NovaFarmersDelight.registry {
    
    val FLINT = registerToolTier("flint")
    
}
