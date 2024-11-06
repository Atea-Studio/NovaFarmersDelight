package fr.ateastudio.farmersdelight.registry

import fr.ateastudio.farmersdelight.NovaFarmersDelight
import fr.ateastudio.farmersdelight.recipe.CookingPotRecipe
import fr.ateastudio.farmersdelight.recipe.CookingPotRecipeDeserializer
import fr.ateastudio.farmersdelight.recipe.group.CookingPotRecipeGroup
import xyz.xenondevs.nova.addon.registry.RecipeTypeRegistry
import xyz.xenondevs.nova.initialize.Init
import xyz.xenondevs.nova.initialize.InitStage

@Init(stage = InitStage.POST_PACK_PRE_WORLD)
object RecipeTypes : RecipeTypeRegistry by NovaFarmersDelight.registry {
    val COOKING_POT = registerRecipeType("cooking_pot", CookingPotRecipe::class, CookingPotRecipeGroup, CookingPotRecipeDeserializer)
}