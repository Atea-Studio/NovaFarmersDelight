package fr.ateastudio.farmersdelight.recipe.cuttingboard

import fr.ateastudio.farmersdelight.registry.RecipeTypes
import net.kyori.adventure.key.Key
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.RecipeChoice
import xyz.xenondevs.nova.world.item.recipe.MultiResultRecipe
import xyz.xenondevs.nova.world.item.recipe.NovaRecipe
import xyz.xenondevs.nova.world.item.recipe.SingleInputChoiceRecipe
import kotlin.random.Random


class CuttingBoardRecipe(
    override val id: Key,
    override val input: RecipeChoice,
    val tool: ToolActionIngredient,
    val chanceResults: List<ChanceResult>,
    val soundEvent: String
) : NovaRecipe, MultiResultRecipe, SingleInputChoiceRecipe {
    override val type get() = RecipeTypes.CUTTING_BOARD.get()
    
    fun getRollableResults(): List<ChanceResult> {
        return this.chanceResults
    }
    
    fun rollResults(rand: Random?, fortuneLevel: Int): List<ItemStack> {
        val results: MutableList<ItemStack> = ArrayList()
        val rollableResults = getRollableResults()
        for (output in rollableResults) {
            val stack = output.rollOutput(rand!!, fortuneLevel)
            if (!stack.isEmpty) results.add(stack)
        }
        return results
    }
    
    override val results: List<ItemStack>
        get() = chanceResults.map { it.getStack() }
        
}