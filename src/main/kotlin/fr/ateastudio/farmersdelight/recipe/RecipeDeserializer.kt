package fr.ateastudio.farmersdelight.recipe

import com.google.gson.JsonObject
import org.bukkit.Material
import xyz.xenondevs.commons.gson.getFloatOrNull
import xyz.xenondevs.commons.gson.getIntOrNull
import xyz.xenondevs.commons.gson.getStringOrNull
import xyz.xenondevs.nova.serialization.json.serializer.RecipeDeserializer
import xyz.xenondevs.nova.serialization.json.serializer.RecipeDeserializer.Companion.getRecipeId
import xyz.xenondevs.nova.serialization.json.serializer.RecipeDeserializer.Companion.parseRecipeChoice
import xyz.xenondevs.nova.util.item.ItemUtils.getItemStack
import xyz.xenondevs.nova.util.item.toItemStack
import java.io.File

object CookingPotRecipeDeserializer : RecipeDeserializer<CookingPotRecipe> {
    override fun deserialize(json: JsonObject, file: File): CookingPotRecipe {
        val inputChoice = json.get("inputs").asJsonArray.map { parseRecipeChoice(it) }
        val result = getItemStack(json.getStringOrNull("result")!!)
        result.amount = json.getIntOrNull("amount") ?: 1
        
        val jsonContainer = json.getStringOrNull("container")
        val container = if (jsonContainer != null) getItemStack(jsonContainer) else Material.AIR.toItemStack()
        
        val time = json.getIntOrNull("time") ?: json.getIntOrNull("cookingtime")!! // legacy support
        
        return CookingPotRecipe(getRecipeId(file), inputChoice, result, container, time)
    }
    
}