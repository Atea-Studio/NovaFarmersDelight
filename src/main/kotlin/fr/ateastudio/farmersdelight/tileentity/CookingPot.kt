package fr.ateastudio.farmersdelight.tileentity

import fr.ateastudio.farmersdelight.block.BlockStateProperties
import fr.ateastudio.farmersdelight.gui.ProgressArrowItem
import fr.ateastudio.farmersdelight.recipe.CookingPotRecipe
import fr.ateastudio.farmersdelight.registry.GuiItems
import fr.ateastudio.farmersdelight.registry.GuiTextures
import fr.ateastudio.farmersdelight.registry.RecipeTypes
import fr.ateastudio.farmersdelight.registry.Sounds
import fr.ateastudio.farmersdelight.util.getCraftingRemainingItem
import fr.ateastudio.farmersdelight.util.hasCraftingRemainingItem
import fr.ateastudio.farmersdelight.util.replacePlaceholders
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import net.minecraft.core.component.DataComponents
import net.minecraft.resources.ResourceLocation
import org.bukkit.Material
import org.bukkit.SoundCategory
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.util.Vector
import xyz.xenondevs.cbf.Compound
import xyz.xenondevs.commons.provider.mutable.mapNonNull
import xyz.xenondevs.invui.gui.Gui
import xyz.xenondevs.invui.inventory.event.ItemPostUpdateEvent
import xyz.xenondevs.invui.inventory.event.ItemPreUpdateEvent
import xyz.xenondevs.invui.inventory.get
import xyz.xenondevs.invui.item.ItemProvider
import xyz.xenondevs.invui.item.builder.ItemBuilder
import xyz.xenondevs.invui.item.builder.setDisplayName
import xyz.xenondevs.invui.item.impl.AbstractItem
import xyz.xenondevs.nova.util.item.novaItem
import xyz.xenondevs.nova.util.item.toItemStack
import xyz.xenondevs.nova.util.unwrap
import xyz.xenondevs.nova.world.BlockPos
import xyz.xenondevs.nova.world.block.state.NovaBlockState
import xyz.xenondevs.nova.world.block.tileentity.TileEntity
import xyz.xenondevs.nova.world.block.tileentity.menu.TileEntityMenuClass
import xyz.xenondevs.nova.world.item.recipe.NovaRecipe
import xyz.xenondevs.nova.world.item.recipe.RecipeManager
import kotlin.math.min
import kotlin.random.Random

class CookingPot(
    pos: BlockPos,
    state: NovaBlockState,
    data: Compound
) : TileEntity(pos, state, data) {
    private val validContainer = listOf(Material.BOWL, Material.BUCKET, Material.GLASS_BOTTLE)
    
    private val ingredientsInventory = storedInventory("ingredients_inventory", 6, postUpdateHandler = ::validateRecipe)
    private val mealStorageInventory = storedInventory("meal_display_slot", 1, true, IntArray(1) {99}, preUpdateHandler =  ::preventSteal)
    private val containerInventory = storedInventory("container_slot", 1, ::validateContainer, ::useContainer)
    private val outputInventory = storedInventory("output_slot", 1, ::preventOutputInput)
    
    private var currentRecipe: CookingPotRecipe? by storedValue<ResourceLocation>("currentRecipe", true).mapNonNull(
        { RecipeManager.getRecipe(RecipeTypes.COOKING_POT, it) },
        NovaRecipe::id
    )
    
    private var cookTime = 0
    private val cookTimeTotal: Int
        get() {
            return currentRecipe?.time ?: 0
        }
    
    private val mealContainerStack: ItemStack
        get() {
            return currentRecipe?.container ?: mealStorageInventory[0]?.getCraftingRemainingItem() ?: ItemStack.empty()
        }
    
    override fun getDrops(includeSelf: Boolean): List<ItemStack> {
        val drop = super.getDrops(includeSelf)
        if (!includeSelf) return drop
        
        val self = drop[0]
        if (mealStorageInventory.isEmpty) {
            val lore = Component.translatable("menu.farmersdelight.empty")
                .color(NamedTextColor.GRAY)
                .decoration(TextDecoration.ITALIC, false)
            self.lore(listOf(lore))
        }
        else {
            val storedStack = mealStorageInventory[0]!!
            val placeholders = mapOf(
                "amount" to "${storedStack.amount}",
                "s" to if (storedStack.amount != 1) "s" else ""
            )
            val holdComponent = Component.translatable("menu.farmersdelight.hold_serving")
                .replacePlaceholders(placeholders)
                .color(NamedTextColor.GRAY)
                .decoration(TextDecoration.ITALIC, false)
                .decoration(TextDecoration.ITALIC, false)
            val displayNameComponent = (storedStack.novaItem?.name ?: storedStack.displayName())
                .color(NamedTextColor.WHITE)
                .decoration(TextDecoration.ITALIC, false)
            self.lore(listOf(holdComponent, displayNameComponent))
            val damage = min(mealStorageInventory.getMaxSlotStackSize(0) - storedStack.amount, mealStorageInventory.getMaxSlotStackSize(0))
            self.unwrap().set(DataComponents.MAX_DAMAGE, mealStorageInventory.getMaxSlotStackSize(0))
            self.unwrap().set(DataComponents.DAMAGE, damage)
        }
        return drop
    }
    
    private fun validateRecipe(event: ItemPostUpdateEvent) {
        val recipe = getMatchingRecipe(ingredientsInventory.items.toList())
        if (recipe != null && recipe != currentRecipe && canCook(recipe)) {
            currentRecipe = recipe
            cookTime = 0
        }
        
    }
    
    private fun preventSteal(event: ItemPreUpdateEvent) {
        event.isCancelled = event.updateReason != SELF_UPDATE_REASON
    }
    
    private fun validateContainer(event: ItemPreUpdateEvent) {
        if (event.updateReason != SELF_UPDATE_REASON && event.newItem != null && !validContainer.contains(event.newItem!!.type)) {
            event.isCancelled = true
        }
    }
    
    private fun useContainer(event: ItemPostUpdateEvent) {
        useStoredContainersOnMeal()
    }
    
    private fun preventOutputInput(event: ItemPreUpdateEvent) {
        event.isCancelled = !event.isRemove && event.updateReason != SELF_UPDATE_REASON
    }
    
    private fun updateResult(result: ItemStack?): ItemStack {
        if (result == null || result.isEmpty) return  ItemStack.empty()
        val meta = result.clone().itemMeta
        meta.setMaxStackSize(mealStorageInventory.getMaxSlotStackSize(0))
        val newStack = result.clone()
        newStack.setItemMeta(meta)
        val container = currentRecipe?.container ?: ItemStack.empty()
        if (!container.isEmpty) {
            val servedOnComponent = Component.translatable("menu.farmersdelight.served_on")
            val displayNameComponent = container.displayName()
                .color(NamedTextColor.GRAY)
            val lore = servedOnComponent
                .append(Component.text(" "))
                .append(displayNameComponent)
                .color(NamedTextColor.GRAY)
                .decoration(TextDecoration.ITALIC, false)
            newStack.lore(listOf(lore))
        }
        return newStack
    }
    
    private fun isHeated(): Boolean { return blockState[BlockStateProperties.HEATED] == true}
    
    private fun hasInput(): Boolean {
        return !ingredientsInventory.isEmpty
    }
    
    private fun canCook(recipe: CookingPotRecipe): Boolean {
        if (hasInput()) {
            val resultStack = updateResult(recipe.result)
            if (resultStack.isEmpty) {
                return false
            } else {
                val storedMealStack = mealStorageInventory[0] ?: ItemStack.empty()
                return if (storedMealStack.isEmpty) {
                    true
                } else if (!storedMealStack.isSimilar(resultStack)) {
                    false
                } else if (storedMealStack.amount + resultStack.amount <= mealStorageInventory.getMaxSlotStackSize(0)) {
                    true
                } else {
                    return storedMealStack.amount + resultStack.amount <= resultStack.maxStackSize
                }
            }
        } else {
            return false
        }
    }
    
    private fun getMeal(): ItemStack {
        return mealStorageInventory[0] ?: ItemStack.empty()
    }
    
    private fun doesMealHaveContainer(meal: ItemStack): Boolean {
        return !mealContainerStack.isEmpty || meal.hasCraftingRemainingItem()
        
    }
    
    private fun isContainerValid(containerItem: ItemStack): Boolean {
        if (containerItem.isEmpty) return false
        if (!mealContainerStack.isEmpty) return mealContainerStack.isSimilar(containerItem)
        return getMeal().isSimilar(containerItem)
    }
    
    private fun ejectIngredientRemainder(remainderStack: ItemStack) {
        val location = pos.location.add(0.0, 0.7, 0.0)
        val itemEntity = pos.world.dropItem(location, remainderStack)
        itemEntity.velocity = Vector(0,1,0)
    }
    
    private fun moveMealToOutput() {
        val mealStack = mealStorageInventory[0] ?: ItemStack.empty()
        val outputStack = outputInventory[0] ?: ItemStack.empty()
        val result = currentRecipe?.result ?: ItemStack.empty()
        
        val mealCount = min(mealStack.amount, (result.maxStackSize - outputStack.amount))
        if (outputStack.isEmpty) {
            mealStorageInventory.addItemAmount(SELF_UPDATE_REASON, 0, -mealCount)
            outputInventory.setItem(SELF_UPDATE_REASON,0, result.asQuantity(mealCount))
        } else if (outputStack.isSimilar(result)) {
            mealStorageInventory.addItemAmount(SELF_UPDATE_REASON, 0, -mealCount)
            outputInventory.addItemAmount(SELF_UPDATE_REASON, 0, mealCount)
        }
    }
    
    private fun useStoredContainersOnMeal() {
        val mealStack = mealStorageInventory[0] ?: ItemStack.empty()
        val containerInputStack = containerInventory[0] ?: ItemStack.empty()
        val outputStack = outputInventory[0] ?: ItemStack.empty()
        val result = currentRecipe?.result ?: ItemStack.empty()
        
        if (isContainerValid(containerInputStack) && outputStack.amount < result.maxStackSize) {
            val smallerStackCount = min(mealStack.amount, result.maxStackSize)
            var mealCount = min(smallerStackCount, (result.maxStackSize - outputStack.amount))
            mealCount = min(mealCount, containerInputStack.amount)
            if (outputStack.isEmpty) {
                mealStorageInventory.addItemAmount(SELF_UPDATE_REASON, 0, -mealCount)
                containerInventory.addItemAmount(SELF_UPDATE_REASON, 0, -mealCount)
                outputInventory.setItem(SELF_UPDATE_REASON,0, result.asQuantity(mealCount))
            } else if (outputStack.isSimilar(result)) {
                mealStorageInventory.addItemAmount(SELF_UPDATE_REASON, 0, -mealCount)
                containerInventory.addItemAmount(SELF_UPDATE_REASON, 0, -mealCount)
                outputInventory.addItemAmount(SELF_UPDATE_REASON, 0, mealCount)
            }
        }
    }
    
    private fun processCooking(recipe: CookingPotRecipe): Boolean {
        ++cookTime
        if (cookTime < cookTimeTotal) {
            return false
        }
        
        cookTime = 0
        val resultStack = updateResult(recipe.result)
        val storedMealStack = mealStorageInventory[0] ?: ItemStack.empty()
        if (storedMealStack.isEmpty) {
            mealStorageInventory.setItem(SELF_UPDATE_REASON, 0, updateResult(resultStack.clone()))
        } else if (storedMealStack.isSimilar(resultStack)) {
            storedMealStack.amount++
            mealStorageInventory.setItem(SELF_UPDATE_REASON, 0, updateResult(storedMealStack))
        }
        
        for (i in 0..<ingredientsInventory.size) {
            val slotStack = ingredientsInventory[i] ?: ItemStack.empty()
            if (slotStack.hasCraftingRemainingItem()) {
                ejectIngredientRemainder(slotStack.getCraftingRemainingItem())
            }
            if (!slotStack.isEmpty) ingredientsInventory.addItemAmount(SELF_UPDATE_REASON, i, -1)
        }
        return true
    }
    
    private fun getMatchingRecipe(inputs: List<ItemStack?>): CookingPotRecipe? {
        return RecipeManager.novaRecipes[RecipeTypes.COOKING_POT]?.values?.asSequence()
            ?.map { it as CookingPotRecipe }
            ?.firstOrNull { recipe ->
                recipe.matches(inputs.filterNotNull())
            }
    }
    
    override fun handleTick() {
        cookingTick()
        //animateTick()
    }
    
    private fun cookingTick() {
        val isHeated = isHeated()
        val recipe = currentRecipe
        if (isHeated && hasInput() && recipe != null) {
            if (canCook(recipe)) {
                processCooking(recipe)
            }
            else {
                --cookTime
            }
        }
        else if (cookTime > 0) {
            cookTime = Math.clamp(cookTime - 2L, 0, cookTimeTotal)
        }
        
        val mealStack = getMeal()
        if (!mealStack.isEmpty) {
            if (!doesMealHaveContainer(mealStack)) {
                moveMealToOutput()
            }
            else if (!containerInventory.isEmpty) {
                useStoredContainersOnMeal()
            }
        }
        
        menuContainer.forEachMenu(CookingPotMenu::updateProgress)
    }
    
    
    
    private fun animateTick() {
        if (isHeated()) {
            val soundEffect = if (!getMeal().isEmpty) Sounds.BLOCK_COOKING_POT_BOIL_SOUP else Sounds.BLOCK_COOKING_POT_BOIL
            val location = pos.location.add(0.5, 0.0, 0.5)
            if (Random.nextInt(5) == 0) {
                location.world.playSound(location, soundEffect, SoundCategory.BLOCKS, 1f,1f)
            }
        }
    }
    
    @TileEntityMenuClass
    private inner class CookingPotMenu : GlobalTileEntityMenu(GuiTextures.COOKING_POT) {
        override fun getTitle(): Component {
            return texture?.getTitle(Component.text("    ").append(block.name)) ?: Component.text("    ").append(block.name)
        }
        
        private inner class HeatedInfoItem : AbstractItem() {
            
            var heated: Boolean = false
                set(value) {
                    if (field != value) {
                        field = value
                        notifyWindows()
                    }
                }
            
            override fun getItemProvider(): ItemProvider {
                val itemBuilder = ItemBuilder(if (heated) GuiItems.COOKING_POT_HEATED.createItemStack() else Material.AIR.toItemStack())
                    .setDisplayName(Component.translatable(if (heated) "menu.farmersdelight.heated" else "menu.farmersdelight.need_heat"))
                    .clearModifiers()
                return itemBuilder
            }
            
            override fun handleClick(clickType: ClickType, player: Player, event: InventoryClickEvent) {
                return
            }
        }
        
        private val heatedInfoItem = HeatedInfoItem()
        private val progressionInfoItem = ProgressArrowItem()
        
        override val gui = Gui.normal()
            .setStructure(
                ". i i i . t . p .",
                ". i i i . . . . .",
                ". . f . . b . o ."
            )
            .addIngredient('i', ingredientsInventory)
            .addIngredient('t', progressionInfoItem)
            .addIngredient('p', mealStorageInventory)
            .addIngredient('f', heatedInfoItem)
            .addIngredient('b', containerInventory)
            .addIngredient('o', outputInventory)
            .build()
        
        init {
            updateProgress()
        }

        fun updateProgress() {
            progressionInfoItem.percentage = if (currentRecipe != null) cookTime.toDouble() / cookTimeTotal.toDouble() else 0.0
            heatedInfoItem.heated = isHeated()
        }
        
    }
    
}