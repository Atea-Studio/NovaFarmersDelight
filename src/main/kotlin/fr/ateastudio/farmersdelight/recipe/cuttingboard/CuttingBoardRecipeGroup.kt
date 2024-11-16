package fr.ateastudio.farmersdelight.recipe.cuttingboard

import fr.ateastudio.farmersdelight.registry.GuiTextures
import fr.ateastudio.farmersdelight.registry.Items
import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import xyz.xenondevs.commons.collections.mapToArray
import xyz.xenondevs.invui.gui.Gui
import xyz.xenondevs.nova.ui.menu.explorer.recipes.createRecipeChoiceItem
import xyz.xenondevs.nova.ui.menu.explorer.recipes.group.RecipeGroup
import xyz.xenondevs.nova.util.item.toItemStack

object CuttingBoardRecipeGroup : RecipeGroup<CuttingBoardRecipe>() {
    override val priority = 4
    override val icon = Items.CUTTING_BOARD.model.clientsideProvider
    override val texture = GuiTextures.COOKING_POT_RECIPE
    
    override fun createGui(recipe: CuttingBoardRecipe): Gui {
        val resultStacks = recipe.chanceResults.mapToArray { it.getStack() }
        
        fun getSafeResult(index: Int) : ItemStack {
            return if (resultStacks.size > index) {
                resultStacks[index]
            } else {
                Material.AIR.toItemStack()
            }
        }
        
        fun toolItem() : ItemStack {
            @Suppress("DEPRECATION") val item = recipe.tool.itemStack
            val meta = item.itemMeta
            meta.displayName(Component.text(recipe.tool.toString()))
            item.setItemMeta(meta)
            return item
        }
            
        return Gui.normal()
            .setStructure(
                ". . . . . . . . .",
                ". i . t . j k . .",
                ". . . . . l m . ."
            )
            .addIngredient('i', createRecipeChoiceItem(recipe.input))
            .addIngredient('t', toolItem())
            .addIngredient('j', getSafeResult(0))
            .addIngredient('k', getSafeResult(1))
            .addIngredient('l', getSafeResult(2))
            .addIngredient('m', getSafeResult(3))
            .build()
    }
 
}