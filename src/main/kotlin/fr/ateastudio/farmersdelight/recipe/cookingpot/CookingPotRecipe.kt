package fr.ateastudio.farmersdelight.recipe.cookingpot

import fr.ateastudio.farmersdelight.registry.RecipeTypes
import net.kyori.adventure.key.Key
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.RecipeChoice
import xyz.xenondevs.commons.collections.removeFirstWhere
import xyz.xenondevs.nova.world.item.recipe.CustomRecipeChoice
import xyz.xenondevs.nova.world.item.recipe.MultiInputChoiceRecipe
import xyz.xenondevs.nova.world.item.recipe.NovaRecipe
import xyz.xenondevs.nova.world.item.recipe.SingleResultRecipe

class CookingPotRecipe(
    override val id: Key,
    override val inputs: List<RecipeChoice>,
    override val result: ItemStack,
    val time: Int,
    val experience: Float
) : NovaRecipe, SingleResultRecipe, MultiInputChoiceRecipe {
    override val type = RecipeTypes.COOKING_POT
    
    fun matches(container: List<ItemStack>): Boolean {
        val choiceList = java.util.ArrayList(inputs)
        
        return container.filterNot { it.isEmpty }.all { matrixStack ->
            choiceList.removeFirstWhere { (it as CustomRecipeChoice).test(matrixStack) }
        } && choiceList.isEmpty()
    }
}