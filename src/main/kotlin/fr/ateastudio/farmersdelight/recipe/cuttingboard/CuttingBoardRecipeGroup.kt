package fr.ateastudio.farmersdelight.recipe.cuttingboard

import fr.ateastudio.farmersdelight.registry.GuiTextures
import fr.ateastudio.farmersdelight.registry.Items
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import xyz.xenondevs.commons.collections.mapToArray
import xyz.xenondevs.invui.gui.Gui
import xyz.xenondevs.invui.item.Item
import xyz.xenondevs.invui.item.ItemWrapper
import xyz.xenondevs.nova.ui.menu.explorer.recipes.createRecipeChoiceItem
import xyz.xenondevs.nova.ui.menu.explorer.recipes.group.RecipeGroup
import xyz.xenondevs.nova.world.item.guiItemProvider

object CuttingBoardRecipeGroup : RecipeGroup<CuttingBoardRecipe>() {
    override val priority = 4
    override val icon = Items.CUTTING_BOARD.guiItemProvider
    override val texture = GuiTextures.CUTTING_BOARD_RECIPE
    
    override fun createGui(recipe: CuttingBoardRecipe): Gui {
        val resultStacks = recipe.chanceResults.mapToArray { it.getStack() }
        
        fun getSafeResult(index: Int) : ItemStack {
            return if (resultStacks.size > index) {
                resultStacks[index]
            } else {
                ItemStack(Material.AIR)
            }
        }
        
        fun toolItem(): Item {
            val itemProviders = recipe.tool.getChoices().map { ItemWrapper(it) }
            return createRecipeChoiceItem(itemProviders)
        }
            
        return Gui.builder()
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