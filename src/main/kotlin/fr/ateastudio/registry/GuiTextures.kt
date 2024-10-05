package fr.ateastudio.registry

import fr.ateastudio.NovaFarmersDelight
import xyz.xenondevs.nova.addon.registry.GuiTextureRegistry
import xyz.xenondevs.nova.initialize.Init
import xyz.xenondevs.nova.initialize.InitStage

@Suppress("unused")
@Init(stage = InitStage.PRE_PACK)
object GuiTextures : GuiTextureRegistry by NovaFarmersDelight.registry {

}