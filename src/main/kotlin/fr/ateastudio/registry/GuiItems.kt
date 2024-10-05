package fr.ateastudio.registry

import fr.ateastudio.NovaFarmersDelight
import org.bukkit.Material
import xyz.xenondevs.nova.addon.registry.ItemRegistry
import xyz.xenondevs.nova.initialize.Init
import xyz.xenondevs.nova.initialize.InitStage
import xyz.xenondevs.nova.world.item.NovaItem

@Suppress("unused")
@Init(stage = InitStage.PRE_PACK)
object GuiItems : ItemRegistry by NovaFarmersDelight.registry {
    
    private fun guiItem(
        name: String,
        localizedName: String? = "",
        stretched: Boolean = false,
        background: Boolean = false
    ): NovaItem = item("gui/opaque/$name") {
        if (localizedName == null) {
            name(null)
        } else localizedName(localizedName)
        hidden(true)
        models {
            itemType(Material.SHULKER_SHELL)
            selectModel { createGuiModel(background, stretched, "item/gui/$name") }
        }
    }
}