package fr.ateastudio.farmersdelight.registry

import fr.ateastudio.farmersdelight.NovaFarmersDelight.registerRecipeType
import fr.ateastudio.farmersdelight.recipe.cookingpot.CookingPotRecipe
import fr.ateastudio.farmersdelight.recipe.cookingpot.CookingPotRecipeDeserializer
import fr.ateastudio.farmersdelight.recipe.cookingpot.CookingPotRecipeGroup
import fr.ateastudio.farmersdelight.recipe.cuttingboard.CuttingBoardRecipe
import fr.ateastudio.farmersdelight.recipe.cuttingboard.CuttingBoardRecipeDeserializer
import fr.ateastudio.farmersdelight.recipe.cuttingboard.CuttingBoardRecipeGroup
import xyz.xenondevs.nova.initialize.Init
import xyz.xenondevs.nova.initialize.InitStage

@Init(stage = InitStage.BOOTSTRAP)
object RecipeTypes {
    val COOKING_POT = registerRecipeType("cooking_pot", CookingPotRecipe::class, CookingPotRecipeGroup, CookingPotRecipeDeserializer)
    val CUTTING_BOARD = registerRecipeType("cutting_board", CuttingBoardRecipe::class, CuttingBoardRecipeGroup, CuttingBoardRecipeDeserializer)
}