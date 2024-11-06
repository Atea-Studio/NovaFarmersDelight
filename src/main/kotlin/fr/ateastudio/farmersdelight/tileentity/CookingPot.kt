package fr.ateastudio.farmersdelight.tileentity

import fr.ateastudio.farmersdelight.block.BlockStateProperties
import fr.ateastudio.farmersdelight.gui.ProgressArrowItem
import fr.ateastudio.farmersdelight.recipe.CookingPotRecipe
import fr.ateastudio.farmersdelight.registry.GuiItems
import fr.ateastudio.farmersdelight.registry.GuiTextures
import fr.ateastudio.farmersdelight.registry.RecipeTypes
import fr.ateastudio.farmersdelight.registry.Sounds
import fr.ateastudio.farmersdelight.util.LogDebug
import fr.ateastudio.farmersdelight.util.getCraftingRemainingItem
import fr.ateastudio.farmersdelight.util.hasCraftingRemainingItem
import fr.ateastudio.farmersdelight.util.split
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap
import net.kyori.adventure.text.Component
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
import xyz.xenondevs.nova.util.item.toItemStack
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
    
    private val ingredientsInventory = storedInventory("ingredients_inventory", 6, false, preUpdateHandler = ::handleIngredientsInputInventoryUpdate)
    private val mealDisplayInventory = storedInventory("meal_display_slot", 1, true, preUpdateHandler =  ::handleMealDisplayInventoryUpdate)
    private val containerInventory = storedInventory("container_slot", 1, false, preUpdateHandler = ::handleContainerInventoryPreUpdate, postUpdateHandler = ::handleContainerInventoryPostUpdate)
    private val outputInventory = storedInventory("output_slot", 1, false, preUpdateHandler = ::handleOutputInventoryUpdate)
    
    private var cookTime = 0
    private var cookTimeTotal = 0
    
    private var mealContainerStack = ItemStack.empty()
    
    private fun isHeated(): Boolean { return blockState[BlockStateProperties.HEATED] == true}
    
    private fun hasInput(): Boolean {
        LogDebug("hasInput")
        return !ingredientsInventory.isEmpty
    }
    
    private fun canCook(recipe: CookingPotRecipe): Boolean {
        LogDebug("canCook")
        if (hasInput()) {
            val resultStack = recipe.result
            if (resultStack.isEmpty) {
                return false
            } else {
                val storedMealStack = mealDisplayInventory[0] ?: ItemStack.empty()
                return if (storedMealStack.isEmpty) {
                    true
                } else if (storedMealStack.isSimilar(resultStack)) {
                    false
                } else if (storedMealStack.amount + resultStack.amount <= mealDisplayInventory.getMaxSlotStackSize(0)) {
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
        LogDebug("getMeal")
        return mealDisplayInventory[0] ?: ItemStack.empty()
    }
    
    
    private fun doesMealHaveContainer(meal: ItemStack): Boolean {
        LogDebug("doesMealHaveContainer")
        return !mealContainerStack.isEmpty || meal.hasCraftingRemainingItem()
        
    }
    
    private fun isContainerValid(containerItem: ItemStack): Boolean {
        LogDebug("isContainerValid")
        if (containerItem.isEmpty) return false
        LogDebug("mealContainerStack: $mealContainerStack")
        LogDebug("containerItem: $containerItem")
        LogDebug("isContainerValid.similar: ${mealContainerStack.isSimilar(containerItem)}")
        if (!mealContainerStack.isEmpty) return mealContainerStack.isSimilar(containerItem)
        LogDebug("isContainerValid3")
        return getMeal().isSimilar(containerItem)
    }
    
    private fun ejectIngredientRemainder(remainderStack: ItemStack) {
        LogDebug("ejectIngredientRemainder")
        val location = pos.location.add(0.0, 0.7, 0.0)
        val itemEntity = pos.world.dropItem(location, remainderStack)
        itemEntity.velocity = Vector(0,1,0)
    }
    
    private fun moveMealToOutput() {
        LogDebug("moveMealToOutput")
        val mealStack = mealDisplayInventory[0] ?: ItemStack.empty()
        val outputStack = outputInventory[0] ?: ItemStack.empty()
        val mealCount = min(mealStack.amount.toDouble(), (mealStack.maxStackSize - outputStack.amount).toDouble()).toInt()
        if (outputStack.isEmpty) {
            outputInventory.setItem(SELF_UPDATE_REASON,0,mealStack.split(mealCount))
        } else if (outputStack.isSimilar(mealStack)) {
            mealDisplayInventory.addItemAmount(SELF_UPDATE_REASON, 0, -mealCount)
            outputInventory.addItemAmount(SELF_UPDATE_REASON, 0, mealCount)
        }
    }
    
    private fun useStoredContainersOnMeal() {
        LogDebug("useStoredContainersOnMeal")
        val mealStack = mealDisplayInventory[0] ?: ItemStack.empty()
        val containerInputStack = containerInventory[0] ?: ItemStack.empty()
        val outputStack = outputInventory[0] ?: ItemStack.empty()
        LogDebug("isContainerValid: ${isContainerValid(containerInputStack)}")
        LogDebug("outputStack.amount: ${outputStack.amount}")
        LogDebug("outputInventory.getMaxSlotStackSize(0): ${outputInventory.getMaxSlotStackSize(0)}")
        
        if (isContainerValid(containerInputStack) && outputStack.amount < outputInventory.getMaxSlotStackSize(0)) {
            val smallerStackCount = min(mealStack.amount.toDouble(), containerInputStack.amount.toDouble()).toInt()
            val mealCount = min(smallerStackCount.toDouble(), (mealStack.maxStackSize - outputStack.amount).toDouble()).toInt()
            LogDebug("smallerStackCount: $smallerStackCount")
            LogDebug("mealCount: $mealCount")
            if (outputStack.isEmpty) {
                LogDebug("empty")
                containerInventory.addItemAmount(SELF_UPDATE_REASON, 0, -mealCount)
                outputInventory.setItem(SELF_UPDATE_REASON,0,mealStack.split(mealCount))
            } else if (outputStack.isSimilar(mealStack)) {
                LogDebug("mealStack")
                mealDisplayInventory.addItemAmount(SELF_UPDATE_REASON, 0, -mealCount)
                containerInventory.addItemAmount(SELF_UPDATE_REASON, 0, -mealCount)
                outputInventory.addItemAmount(SELF_UPDATE_REASON, 0, mealCount)
            }
            LogDebug("END")
        }
    }
    
    private fun processCooking(recipe: CookingPotRecipe): Boolean {
        LogDebug("processCooking")
        
        ++cookTime
        cookTimeTotal = recipe.time
        if (cookTime < cookTimeTotal) {
            return false
        }
        
        cookTime = 0
        mealContainerStack = recipe.container
        val resultStack = recipe.result
        val storedMealStack = mealDisplayInventory[0] ?: ItemStack.empty()
        if (storedMealStack.isEmpty) {
            mealDisplayInventory.setItem(SELF_UPDATE_REASON, 0, resultStack.clone())
        } else if (storedMealStack.isSimilar(resultStack)) {
            mealDisplayInventory.addItemAmount(SELF_UPDATE_REASON, 0, 1)
        }
        
        for (i in 0..<mealDisplayInventory.size) {
            val slotStack = mealDisplayInventory[i] ?: ItemStack.empty()
            if (slotStack.hasCraftingRemainingItem()) {
                ejectIngredientRemainder(slotStack.getCraftingRemainingItem())
            }
            if (!slotStack.isEmpty) mealDisplayInventory.addItemAmount(SELF_UPDATE_REASON, 0, -1)
        }
        return true
    }
    
    private fun getMatchingRecipe(inputs: List<ItemStack?>): CookingPotRecipe? {
        LogDebug("getMatchingRecipe")
        return RecipeManager.novaRecipes[RecipeTypes.COOKING_POT]?.values?.asSequence()
            ?.map { it as CookingPotRecipe }
            ?.firstOrNull { recipe ->
                recipe.matches(inputs.filterNotNull())
            }
    }
    
    private fun handleIngredientsInputInventoryUpdate(event: ItemPreUpdateEvent) {
        LogDebug("handleIngredientsInputInventoryUpdate")
    
    }
    
    private fun handleMealDisplayInventoryUpdate(event: ItemPreUpdateEvent) {
        LogDebug("handleMealDisplayInventoryUpdate")
        LogDebug("${event.updateReason}")
        event.isCancelled = event.updateReason != SELF_UPDATE_REASON
    }
    
    private fun handleContainerInventoryPreUpdate(event: ItemPreUpdateEvent) {
        LogDebug("handleContainerInventoryPreUpdate")
        if (event.updateReason != SELF_UPDATE_REASON && event.newItem != null && !validContainer.contains(event.newItem!!.type)) {
            event.isCancelled = true
        }
    }
    
    private fun handleContainerInventoryPostUpdate(event: ItemPostUpdateEvent) {
        LogDebug("handleContainerInventoryPostUpdate")

    }
    
    private fun handleOutputInventoryUpdate(event: ItemPreUpdateEvent) {
        LogDebug("handleOutputInventoryUpdate")
        event.isCancelled = !event.isRemove && event.updateReason != SELF_UPDATE_REASON
    }
    
    private var currentRecipe: CookingPotRecipe? by storedValue<ResourceLocation>("currentRecipe").mapNonNull(
        { RecipeManager.getRecipe(RecipeTypes.COOKING_POT, it) },
        NovaRecipe::id
    )
    
    override fun handleTick() {
        LogDebug("handleTick")
        cookingTick()
        //animateTick()
    }
    
    private fun cookingTick() {
        LogDebug("cookingTick")
        val isHeated = isHeated()
        if (isHeated && hasInput()) {
            val recipe = getMatchingRecipe(ingredientsInventory.items.toList())
            if (recipe != null && canCook(recipe)) {
                processCooking(recipe)
            }
            else {
                cookTime = 0
            }
        }
        else if (cookTime > 0) {
            cookTime = Math.clamp(cookTime - 2L, 0, cookTimeTotal)
        }
        
        val mealStack = getMeal()
        if (!mealStack.isEmpty) {
            if (!doesMealHaveContainer(mealStack)) {
                LogDebug("NO CONTAINER")
                moveMealToOutput()
            }
            else if (!containerInventory.isEmpty) {
                LogDebug("CONTAINER")
                useStoredContainersOnMeal()
            }
        }
        
        menuContainer.forEachMenu(CookingPotMenu::updateProgress)
    }
    
    
    
    private fun animateTick() {
        LogDebug("animateTick")
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
            .addIngredient('p', mealDisplayInventory)
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