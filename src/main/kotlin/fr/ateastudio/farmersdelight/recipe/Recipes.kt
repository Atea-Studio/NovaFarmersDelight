package fr.ateastudio.farmersdelight.recipe

import fr.ateastudio.farmersdelight.registry.RecipeTypes
import net.minecraft.resources.ResourceLocation
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.RecipeChoice
import xyz.xenondevs.commons.collections.removeFirstWhere
import xyz.xenondevs.nova.world.item.recipe.MultiInputChoiceRecipe
import xyz.xenondevs.nova.world.item.recipe.NovaRecipe
import xyz.xenondevs.nova.world.item.recipe.SingleResultRecipe

class CookingPotRecipe(
    override val id: ResourceLocation,
    override val inputs: List<RecipeChoice>,
    override val result: ItemStack,
    val container: ItemStack,
    val time: Int
) : NovaRecipe, SingleResultRecipe, MultiInputChoiceRecipe {
    override val type = RecipeTypes.COOKING_POT
    
    fun matches(container: List<ItemStack>): Boolean {
        val choiceList = java.util.ArrayList(inputs)
        
        return container.filterNot { it.isEmpty }.all { matrixStack ->
            choiceList.removeFirstWhere { (it as RecipeChoice.ExactChoice).test(matrixStack) }
        } && choiceList.isEmpty()
    }
}