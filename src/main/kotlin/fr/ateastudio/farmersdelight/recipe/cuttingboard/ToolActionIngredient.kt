package fr.ateastudio.farmersdelight.recipe.cuttingboard

import xyz.xenondevs.nova.world.item.createItemStack

import fr.ateastudio.farmersdelight.registry.Items
import fr.ateastudio.farmersdelight.util.isKnife
import net.kyori.adventure.key.Key
import org.bukkit.Material
import org.bukkit.Tag as BukkitTag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.RecipeChoice
import xyz.xenondevs.nova.world.item.tool.VanillaToolCategories

private fun ItemStack.hasToolCategory(category: Key): Boolean {
    val type = this.type
    return when (category) {
        VanillaToolCategories.AXE -> BukkitTag.ITEMS_AXES.isTagged(type)
        VanillaToolCategories.PICKAXE -> BukkitTag.ITEMS_PICKAXES.isTagged(type)
        VanillaToolCategories.SHOVEL -> BukkitTag.ITEMS_SHOVELS.isTagged(type)
        VanillaToolCategories.HOE -> BukkitTag.ITEMS_HOES.isTagged(type)
        VanillaToolCategories.SWORD -> BukkitTag.ITEMS_SWORDS.isTagged(type)
        VanillaToolCategories.SHEARS -> type == Material.SHEARS
        else -> false
    }
}

sealed class ToolActionIngredient : RecipeChoice {
    abstract fun getChoices() : List<ItemStack>
    
    /**
     * Represents a tool identified by its tag.
     */
    class Tag(private val tag: ToolTag) : ToolActionIngredient() {
        override fun test(itemStack: ItemStack): Boolean {
            return when(tag) {
                ToolTag.SHEARS -> itemStack.hasToolCategory(VanillaToolCategories.SHEARS)
                ToolTag.KNIVES -> itemStack.isKnife()
                else -> true
            }
        }
        
        override fun clone(): RecipeChoice {
            return Tag(tag)
        }
        
        @Deprecated("")
        override fun getItemStack(): ItemStack {
            return when(tag) {
                ToolTag.SHEARS -> ItemStack(Material.SHEARS)
                ToolTag.KNIVES -> ItemStack(Material.WOODEN_SWORD)
                else -> ItemStack.empty()
            }
        }
        override fun getChoices(): List<ItemStack> {
            return when(tag) {
                ToolTag.SHEARS -> listOf(ItemStack(Material.SHEARS))
                ToolTag.KNIVES -> listOf(
                    Items.FLINT_KNIFE.createItemStack(),
                    Items.IRON_KNIFE.createItemStack(),
                    Items.GOLDEN_KNIFE.createItemStack(),
                    Items.DIAMOND_KNIFE.createItemStack(),
                    Items.NETHERITE_KNIFE.createItemStack(),
                )
                else -> listOf(ItemStack.empty())
            }
        }
        
    }
    
    /**
     * Represents a tool identified by a specific action type.
     */
    class Action(private val type: ToolType, private val action: ToolAction) : ToolActionIngredient() {
        override fun test(itemStack: ItemStack): Boolean {
            return if (action.toolCategory != null && itemStack.hasToolCategory(action.toolCategory)) {
                true
            } else {
                when (action.actionName.split("_").getOrNull(0)) {
                    "shield" -> itemStack.type == Material.SHIELD
                    "fishing" -> itemStack.type == Material.FISHING_ROD
                    else -> false
                }
            }
        }
        
        override fun clone(): RecipeChoice {
            return Action(type, action)
        }
        
        override fun getChoices(): List<ItemStack> {
            return when (action.actionName.split("_").getOrNull(0)) {
                "axe" -> listOf(
                    ItemStack(Material.WOODEN_AXE),
                    ItemStack(Material.STONE_AXE),
                    ItemStack(Material.IRON_AXE),
                    ItemStack(Material.GOLDEN_AXE),
                    ItemStack(Material.DIAMOND_AXE),
                    ItemStack(Material.NETHERITE_AXE),
                )
                "pickaxe" -> listOf(
                    ItemStack(Material.WOODEN_PICKAXE),
                    ItemStack(Material.STONE_PICKAXE),
                    ItemStack(Material.IRON_PICKAXE),
                    ItemStack(Material.GOLDEN_PICKAXE),
                    ItemStack(Material.DIAMOND_PICKAXE),
                    ItemStack(Material.NETHERITE_PICKAXE),
                )
                "shovel" -> listOf(
                    ItemStack(Material.WOODEN_SHOVEL),
                    ItemStack(Material.STONE_SHOVEL),
                    ItemStack(Material.IRON_SHOVEL),
                    ItemStack(Material.GOLDEN_SHOVEL),
                    ItemStack(Material.DIAMOND_SHOVEL),
                    ItemStack(Material.NETHERITE_SHOVEL),
                )
                "hoe", "till" -> listOf(
                    ItemStack(Material.WOODEN_HOE),
                    ItemStack(Material.STONE_HOE),
                    ItemStack(Material.IRON_HOE),
                    ItemStack(Material.GOLDEN_HOE),
                    ItemStack(Material.DIAMOND_HOE),
                    ItemStack(Material.NETHERITE_HOE),
                )
                "sword" -> listOf(
                    ItemStack(Material.WOODEN_SWORD),
                    ItemStack(Material.STONE_SWORD),
                    ItemStack(Material.IRON_SWORD),
                    ItemStack(Material.GOLDEN_SWORD),
                    ItemStack(Material.DIAMOND_SWORD),
                    ItemStack(Material.NETHERITE_SWORD),
                )
                "shears" -> listOf(ItemStack(Material.SHEARS))
                "shield" -> listOf(ItemStack(Material.SHIELD))
                "fishing" -> listOf(ItemStack(Material.FISHING_ROD))
                else -> listOf(ItemStack.empty())
            }
        }
        
        @Deprecated("")
        override fun getItemStack(): ItemStack {
            return when (action.actionName.split("_").getOrNull(0)) {
                "axe" -> ItemStack(Material.WOODEN_AXE)
                "pickaxe" -> ItemStack(Material.WOODEN_PICKAXE)
                "shovel" -> ItemStack(Material.WOODEN_SHOVEL)
                "hoe", "till" -> ItemStack(Material.WOODEN_HOE)
                "sword" -> ItemStack(Material.WOODEN_SWORD)
                "shears" -> ItemStack(Material.SHEARS)
                "shield" -> ItemStack(Material.SHIELD)
                "fishing" -> ItemStack(Material.FISHING_ROD)
                else -> ItemStack.empty()
            }
        }
    }
}