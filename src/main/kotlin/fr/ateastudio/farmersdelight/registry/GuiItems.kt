package fr.ateastudio.farmersdelight.registry

import fr.ateastudio.farmersdelight.NovaFarmersDelight
import org.bukkit.Material
import xyz.xenondevs.nova.addon.registry.ItemRegistry
import xyz.xenondevs.nova.initialize.Init
import xyz.xenondevs.nova.initialize.InitStage
import xyz.xenondevs.nova.world.item.NovaItem

@Suppress("unused")
@Init(stage = InitStage.PRE_PACK)
object GuiItems : ItemRegistry by NovaFarmersDelight.registry {
    val COOKING_POT_HEATED = guiItem("cooking_pot_heated")
    val BOWL_UNDERLAY = guiItem("bowl")
    val ARROW_PROGRESS_FULL = guiItemWithModel("arrow_progress_full")
    
    private fun guiItem(
        name: String,
        localizedName: String? = null,
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
    
    private fun guiItemWithModel(
        name: String,
        localizedName: String? = null,
        stretched: Boolean = false,
        background: Boolean = false
    ): NovaItem = item(name) {
        if (localizedName == null) {
            name(null)
        } else localizedName(localizedName)
        hidden(true)
    }
}