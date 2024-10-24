package fr.ateastudio.registry

import fr.ateastudio.farmersdelight.NovaFarmersDelight
import xyz.xenondevs.nova.addon.registry.ToolCategoryRegistry
import xyz.xenondevs.nova.initialize.Init
import xyz.xenondevs.nova.initialize.InitStage

@Suppress("unused")
@Init(stage = InitStage.PRE_PACK)
object ToolCategories : ToolCategoryRegistry by NovaFarmersDelight.registry {
    val KNIFE = registerToolCategory("knife")
}