package fr.ateastudio.farmersdelight.util

import org.bukkit.Bukkit
import org.bukkit.inventory.CampfireRecipe
import org.bukkit.inventory.ItemStack

/**
 * Looks up the vanilla campfire-cooking recipe matching [input], mirroring the original mod's
 * reuse of [net.minecraft.world.item.crafting.RecipeType.CAMPFIRE_COOKING] for both the Skillet
 * and the Stove.
 */
fun findCampfireRecipe(input: ItemStack): CampfireRecipe? {
    if (input.isEmpty) return null
    val iterator = Bukkit.recipeIterator()
    while (iterator.hasNext()) {
        val recipe = iterator.next()
        if (recipe is CampfireRecipe && recipe.inputChoice.test(input)) {
            return recipe
        }
    }
    return null
}
