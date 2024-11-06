package fr.ateastudio.farmersdelight.registry

import fr.ateastudio.farmersdelight.NovaFarmersDelight
import net.kyori.adventure.text.Component
import xyz.xenondevs.nova.addon.registry.ItemRegistry
import xyz.xenondevs.nova.initialize.Init
import xyz.xenondevs.nova.initialize.InitStage
import xyz.xenondevs.nova.world.item.behavior.Consumable

@Suppress("unused")
@Init(stage = InitStage.PRE_PACK)
object Items : ItemRegistry by NovaFarmersDelight.registry {
    
    val COOKING_POT = item(Blocks.COOKING_POT) {
        name(Component.translatable("item.farmersdelight.cooking_pot"))
        models {
            selectModel {
                createLayeredModel("item/cooking_pot")
            }
        }
    }
    
    val BEETROOT_CRATE = registerItem(Blocks.BEETROOT_CRATE)
    val CARROT_CRATE = registerItem(Blocks.CARROT_CRATE)
    val POTATO_CRATE = registerItem(Blocks.POTATO_CRATE)
    
    val TOMATO_SEEDS = item(Blocks.TOMATOES_CROP) {
        name(Component.translatable("item.farmersdelight.tomato_seeds"))
        models {
            selectModel {
                createLayeredModel("item/tomato_seeds")
            }
        }
    }
    val TOMATO = registerItem("tomato", Consumable)
    val ROTTEN_TOMATO = registerItem("rotten_tomato")
    val TOMATO_CRATE = registerItem(Blocks.TOMATO_CRATE)
    
    val RICE = item(Blocks.RICE_CROP) {
        name(Component.translatable("item.farmersdelight.rice"))
        models {
            selectModel {
                createLayeredModel("item/rice")
            }
        }
    }
    val RICE_PANICLE = registerItem("rice_panicle")
    val RICE_BAG = registerItem(Blocks.RICE_BAG)
    val RICE_BALE = registerItem(Blocks.RICE_BALE)
    val MUDDY_FARMLAND = registerItem(Blocks.MUDDY_FARMLAND)
    
    val CABBAGE_SEEDS = item(Blocks.CABBAGES_CROP) {
        name(Component.translatable("item.farmersdelight.cabbage_seeds"))
        models {
            selectModel {
                createLayeredModel("item/cabbage_seeds")
            }
        }
    }
    val CABBAGE = registerItem("cabbage", Consumable)
    val CABBAGE_CRATE = registerItem(Blocks.CABBAGE_CRATE)
    
    val ONION = item(Blocks.ONION_CROP) {
        name(Component.translatable("item.farmersdelight.onion"))
        behaviors(Consumable)
        models {
            selectModel {
                createLayeredModel("item/onion")
            }
        }
    }
    val ONION_CRATE = registerItem(Blocks.ONION_CRATE)
    val COOKED_RICE = registerItem("cooked_rice", Consumable)
    
}