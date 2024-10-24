package fr.ateastudio.farmersdelight.registry

import fr.ateastudio.farmersdelight.NovaFarmersDelight
import net.kyori.adventure.text.Component
import xyz.xenondevs.nova.addon.registry.ItemRegistry
import xyz.xenondevs.nova.initialize.Init
import xyz.xenondevs.nova.initialize.InitStage
import xyz.xenondevs.nova.resources.builder.model.ModelBuilder
import xyz.xenondevs.nova.resources.builder.task.model.ItemModelContent
import xyz.xenondevs.nova.resources.layout.block.BlockModelSelectorScope
import xyz.xenondevs.nova.resources.layout.item.ItemModelSelectorScope

@Suppress("unused")
@Init(stage = InitStage.PRE_PACK)
object Items : ItemRegistry by NovaFarmersDelight.registry {
    val TOMATO_SEEDS = item(Blocks.TOMATOES_STAGE0) {
        name(Component.translatable("item.farmersdelight.tomato_seeds"))
        models {
            selectModel {
                createLayeredModel("item/tomato_seeds")
            }
        }
    }
    val TOMATO = registerItem("tomato", )
}