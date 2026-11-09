package fr.ateastudio.farmersdelight.recipe.cookingpot

import fr.ateastudio.farmersdelight.registry.RecipeTypes
import net.kyori.adventure.key.Key
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.RecipeChoice
import xyz.xenondevs.nova.world.item.recipe.CustomRecipeChoice
import xyz.xenondevs.nova.world.item.recipe.MultiInputChoiceRecipe
import xyz.xenondevs.nova.world.item.recipe.NovaRecipe
import xyz.xenondevs.nova.world.item.recipe.SingleResultRecipe

class CookingPotRecipe(
    override val id: Key,
    override val inputs: List<RecipeChoice>,
    override val result: ItemStack,
    val time: Int,
    val experience: Float,
    val container: ItemStack? = null
) : NovaRecipe, SingleResultRecipe, MultiInputChoiceRecipe {
    override val type get() = RecipeTypes.COOKING_POT.get()
    
    fun matches(container: List<ItemStack>): Boolean =
        matchesIngredientMultiset(container.filterNot { it.isEmpty }, inputs) { choice, stack ->
            (choice as CustomRecipeChoice).test(stack)
        }
}

/**
 * True when every occupied slot can be paired with a distinct ingredient choice and no choice is left over.
 * Uses augmenting-path bipartite matching so overlapping choices (e.g. a specific item and a group that
 * also contains it) never fail because of slot or choice ordering.
 */
internal fun <I, C> matchesIngredientMultiset(items: List<I>, choices: List<C>, test: (C, I) -> Boolean): Boolean {
    if (items.size != choices.size) return false
    val choiceOwner = IntArray(choices.size) { -1 }
    
    fun assign(itemIndex: Int, visited: BooleanArray): Boolean {
        for (choiceIndex in choices.indices) {
            if (visited[choiceIndex] || !test(choices[choiceIndex], items[itemIndex])) continue
            visited[choiceIndex] = true
            if (choiceOwner[choiceIndex] == -1 || assign(choiceOwner[choiceIndex], visited)) {
                choiceOwner[choiceIndex] = itemIndex
                return true
            }
        }
        return false
    }
    
    return items.indices.all { assign(it, BooleanArray(choices.size)) }
}