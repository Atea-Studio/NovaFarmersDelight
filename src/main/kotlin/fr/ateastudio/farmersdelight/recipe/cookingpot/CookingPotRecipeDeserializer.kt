package fr.ateastudio.farmersdelight.recipe.cookingpot

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import org.bukkit.inventory.RecipeChoice
import xyz.xenondevs.commons.gson.getFloatOrNull
import xyz.xenondevs.commons.gson.getIntOrNull
import xyz.xenondevs.commons.gson.getStringOrNull
import xyz.xenondevs.nova.serialization.json.serializer.RecipeDeserializer
import xyz.xenondevs.nova.util.item.ItemUtils
import java.io.File

object CookingPotRecipeDeserializer : RecipeDeserializer<CookingPotRecipe> {
    override fun deserialize(json: JsonObject, file: File): CookingPotRecipe {
        
        val time = json.getIntOrNull("time") ?: json.getIntOrNull("cookingtime")!!
        val experience = json.getFloatOrNull("experience") ?: json.getFloatOrNull("xp")!! // legacy support
        
        val ingredientsMap = HashMap<RecipeChoice, Int>()
        val ingredients = json.get("ingredients")
        if (ingredients is JsonObject) {
            ingredients.entrySet().forEach { (key, value) ->
                val choice = ItemUtils.getRecipeChoice(listOf(key))
                ingredientsMap[choice] = value.asInt
            }
        } else if (ingredients is JsonArray) {
            ingredients.forEach {
                it as JsonObject
                
                val items = it.get("item") ?: it.get("items")
                val choice = RecipeDeserializer.parseRecipeChoice(items)
                val current = ingredientsMap[choice] ?: 0
                ingredientsMap[choice] = current + (it.getIntOrNull("amount") ?: 1)
            }
        }
        
        val inputChoice = mutableListOf<RecipeChoice>()
        ingredientsMap.forEach { (material, count) ->
            var amountLeft = count
            while (amountLeft-- > 0) {
                inputChoice.add(material)
            }
        }

        val result = ItemUtils.getItemStack(json.getStringOrNull("result")!!)
        result.amount = json.getIntOrNull("amount") ?: 1
        
        val container = json.getStringOrNull("container")?.let(ItemUtils::getItemStack)
        
        return CookingPotRecipe(RecipeDeserializer.getRecipeKey(file), inputChoice, result, time, experience, container)
    }
}