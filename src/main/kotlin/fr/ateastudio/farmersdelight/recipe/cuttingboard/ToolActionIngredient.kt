package fr.ateastudio.farmersdelight.recipe.cuttingboard

import xyz.xenondevs.nova.world.item.createItemStack

import fr.ateastudio.farmersdelight.registry.Items
import fr.ateastudio.farmersdelight.util.isKnife
import io.papermc.paper.registry.RegistryAccess
import io.papermc.paper.registry.RegistryKey
import io.papermc.paper.registry.keys.tags.ItemTypeTagKeys
import net.kyori.adventure.key.Key
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.ItemType
import org.bukkit.inventory.RecipeChoice
import xyz.xenondevs.nova.world.item.itemType
import xyz.xenondevs.nova.world.item.tool.VanillaToolCategories

@Suppress("UnstableApiUsage")
private fun ItemStack.hasToolCategory(category: Key): Boolean {
    val tag = when (category) {
        VanillaToolCategories.AXE -> ItemTypeTagKeys.AXES
        VanillaToolCategories.PICKAXE -> ItemTypeTagKeys.PICKAXES
        VanillaToolCategories.SHOVEL -> ItemTypeTagKeys.SHOVELS
        VanillaToolCategories.HOE -> ItemTypeTagKeys.HOES
        VanillaToolCategories.SWORD -> ItemTypeTagKeys.SWORDS
        VanillaToolCategories.SHEARS -> return itemType == ItemType.SHEARS
        else -> return false
    }
    return itemType in RegistryAccess.registryAccess().getRegistry(RegistryKey.ITEM).getTagValues(tag)
}

@Suppress("UnstableApiUsage", "NonExtendableApiUsage")
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
                ToolTag.SHEARS -> ItemType.SHEARS.createItemStack()
                ToolTag.KNIVES -> ItemType.WOODEN_SWORD.createItemStack()
                else -> ItemStack.empty()
            }
        }
        override fun getChoices(): List<ItemStack> {
            return when(tag) {
                ToolTag.SHEARS -> listOf(ItemType.SHEARS.createItemStack())
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
                    "shield" -> itemStack.itemType == ItemType.SHIELD
                    "fishing" -> itemStack.itemType == ItemType.FISHING_ROD
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
                    ItemType.WOODEN_AXE.createItemStack(),
                    ItemType.STONE_AXE.createItemStack(),
                    ItemType.IRON_AXE.createItemStack(),
                    ItemType.GOLDEN_AXE.createItemStack(),
                    ItemType.DIAMOND_AXE.createItemStack(),
                    ItemType.NETHERITE_AXE.createItemStack(),
                )
                "pickaxe" -> listOf(
                    ItemType.WOODEN_PICKAXE.createItemStack(),
                    ItemType.STONE_PICKAXE.createItemStack(),
                    ItemType.IRON_PICKAXE.createItemStack(),
                    ItemType.GOLDEN_PICKAXE.createItemStack(),
                    ItemType.DIAMOND_PICKAXE.createItemStack(),
                    ItemType.NETHERITE_PICKAXE.createItemStack(),
                )
                "shovel" -> listOf(
                    ItemType.WOODEN_SHOVEL.createItemStack(),
                    ItemType.STONE_SHOVEL.createItemStack(),
                    ItemType.IRON_SHOVEL.createItemStack(),
                    ItemType.GOLDEN_SHOVEL.createItemStack(),
                    ItemType.DIAMOND_SHOVEL.createItemStack(),
                    ItemType.NETHERITE_SHOVEL.createItemStack(),
                )
                "hoe", "till" -> listOf(
                    ItemType.WOODEN_HOE.createItemStack(),
                    ItemType.STONE_HOE.createItemStack(),
                    ItemType.IRON_HOE.createItemStack(),
                    ItemType.GOLDEN_HOE.createItemStack(),
                    ItemType.DIAMOND_HOE.createItemStack(),
                    ItemType.NETHERITE_HOE.createItemStack(),
                )
                "sword" -> listOf(
                    ItemType.WOODEN_SWORD.createItemStack(),
                    ItemType.STONE_SWORD.createItemStack(),
                    ItemType.IRON_SWORD.createItemStack(),
                    ItemType.GOLDEN_SWORD.createItemStack(),
                    ItemType.DIAMOND_SWORD.createItemStack(),
                    ItemType.NETHERITE_SWORD.createItemStack(),
                )
                "shears" -> listOf(ItemType.SHEARS.createItemStack())
                "shield" -> listOf(ItemType.SHIELD.createItemStack())
                "fishing" -> listOf(ItemType.FISHING_ROD.createItemStack())
                else -> listOf(ItemStack.empty())
            }
        }
        
        @Deprecated("")
        override fun getItemStack(): ItemStack {
            return when (action.actionName.split("_").getOrNull(0)) {
                "axe" -> ItemType.WOODEN_AXE.createItemStack()
                "pickaxe" -> ItemType.WOODEN_PICKAXE.createItemStack()
                "shovel" -> ItemType.WOODEN_SHOVEL.createItemStack()
                "hoe", "till" -> ItemType.WOODEN_HOE.createItemStack()
                "sword" -> ItemType.WOODEN_SWORD.createItemStack()
                "shears" -> ItemType.SHEARS.createItemStack()
                "shield" -> ItemType.SHIELD.createItemStack()
                "fishing" -> ItemType.FISHING_ROD.createItemStack()
                else -> ItemStack.empty()
            }
        }
    }
}