package fr.ateastudio.farmersdelight.registry

import fr.ateastudio.farmersdelight.NovaFarmersDelight.item
import org.bukkit.inventory.ItemType
import xyz.xenondevs.nova.initialize.Init
import xyz.xenondevs.nova.initialize.InitStage
import xyz.xenondevs.nova.registry.RegistryEntry

@Suppress("unused")
@Init(stage = InitStage.PRE_PACK)
object GuiItems {
    val COOKING_POT_HEATED = guiItem("cooking_pot_heated", "farmersdelight.container.cooking_pot.heated")
    val COOKING_POT_NOT_HEATED = item("gui/opaque/cooking_pot_not_heat") {
        localizedName("farmersdelight.container.cooking_pot.not_heated")
        hidden(true)
        modelDefinition {
            model = buildModel {
                getModel("item/gui/cooking_pot_not_heated")
            }
        }
    }
    val BOWL_UNDERLAY = guiItem("bowl")
    val ARROW_PROGRESS = item("gui/opaque/progress/arrow") {
        localizedName("")
        hidden(true)
        modelDefinition {
            model = rangedModels(23) {
                getModel("item/gui/progress/arrow/$it")
            }
        }
    }
    
    private fun guiItem(
        name: String,
        localizedName: String? = null,
        stretched: Boolean = false,
        background: Boolean = false
    ): RegistryEntry.Paper<ItemType> = item("gui/opaque/$name") {
        if (localizedName == null) {
            name(net.kyori.adventure.text.Component.empty())
        } else localizedName(localizedName)
        hidden(true)
        modelDefinition {
            model = buildModel {
                createGuiModel(background, stretched, "item/gui/$name")
            }
        }
    }
    
}