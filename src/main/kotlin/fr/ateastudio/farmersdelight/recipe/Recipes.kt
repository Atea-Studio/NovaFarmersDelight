package fr.ateastudio.farmersdelight.recipe

import fr.ateastudio.farmersdelight.registry.RecipeTypes
import fr.ateastudio.farmersdelight.util.RecipeMatcher
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.crafting.CraftingInput
import net.minecraft.world.level.Level
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
            choiceList.removeFirstWhere { it.test(matrixStack) }
        } && choiceList.isEmpty()
    }
}