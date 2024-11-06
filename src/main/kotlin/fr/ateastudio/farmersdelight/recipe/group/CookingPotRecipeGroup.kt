package fr.ateastudio.farmersdelight.recipe.group

import fr.ateastudio.farmersdelight.recipe.CookingPotRecipe
import fr.ateastudio.farmersdelight.registry.GuiItems
import fr.ateastudio.farmersdelight.registry.GuiTextures
import fr.ateastudio.farmersdelight.registry.Items
import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.inventory.RecipeChoice
import xyz.xenondevs.invui.gui.Gui
import xyz.xenondevs.invui.item.Item
import xyz.xenondevs.invui.item.builder.setDisplayName
import xyz.xenondevs.invui.item.impl.SimpleItem
import xyz.xenondevs.nova.ui.menu.explorer.recipes.createRecipeChoiceItem
import xyz.xenondevs.nova.ui.menu.explorer.recipes.group.RecipeGroup
import xyz.xenondevs.nova.util.item.toItemStack
import xyz.xenondevs.nova.world.item.DefaultGuiItems

object CookingPotRecipeGroup : RecipeGroup<CookingPotRecipe>() {
    override val priority = 4
    override val icon = Items.COOKING_POT.model.clientsideProvider
    override val texture = GuiTextures.COOKING_POT_RECIPE
    
    private fun getSafeRecipeChoice(inputs: List<RecipeChoice>, index: Int) : Item {
        return if (inputs.size > index) {
            createRecipeChoiceItem(inputs[index])
        } else {
            SimpleItem(Material.AIR.toItemStack())
        }
    }
    
    override fun createGui(recipe: CookingPotRecipe): Gui {
        return Gui.normal()
            .setStructure(
                ". i j k . t . p .",
                ". l m n . . . . .",
                ". . f . . b . p ."
            )
            .addIngredient('i', getSafeRecipeChoice(recipe.inputs,0))
            .addIngredient('j', getSafeRecipeChoice(recipe.inputs,1))
            .addIngredient('k', getSafeRecipeChoice(recipe.inputs,2))
            .addIngredient('l', getSafeRecipeChoice(recipe.inputs,3))
            .addIngredient('m', getSafeRecipeChoice(recipe.inputs,4))
            .addIngredient('n', getSafeRecipeChoice(recipe.inputs,5))
            .addIngredient('t', DefaultGuiItems.TP_STOPWATCH.model
                .createClientsideItemBuilder()
                .setDisplayName(Component.translatable("menu.nova.recipe.time", Component.text(recipe.time / 20.0)))
            )
            .addIngredient('p', recipe.result)
            .addIngredient('f', GuiItems.COOKING_POT_HEATED.createItemStack())
            .addIngredient('b', if (recipe.container.isEmpty) GuiItems.BOWL_UNDERLAY.createItemStack() else recipe.container )
            .build()
    }
}