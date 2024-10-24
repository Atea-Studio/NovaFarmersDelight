package fr.ateastudio.farmersdelight.registry

import fr.ateastudio.farmersdelight.NovaFarmersDelight
import fr.ateastudio.farmersdelight.WailaToolIconProvider
import xyz.xenondevs.nova.addon.registry.WailaToolIconProviderRegistry
import xyz.xenondevs.nova.initialize.Init
import xyz.xenondevs.nova.initialize.InitStage

@Suppress("unused")
@Init(stage = InitStage.PRE_WORLD)
object WailaToolIconProviders : WailaToolIconProviderRegistry by NovaFarmersDelight.registry {
    val ACTIProvider = registerWailaToolIconProvider("farmersdelight", WailaToolIconProvider)
}
