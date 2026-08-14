package fr.ateastudio.farmersdelight.tileentity

import fr.ateastudio.farmersdelight.block.BlockStateProperties
import fr.ateastudio.farmersdelight.block.CookingPotSupport
import fr.ateastudio.farmersdelight.gui.ProgressArrowItem
import fr.ateastudio.farmersdelight.recipe.cookingpot.CookingPotRecipe
import fr.ateastudio.farmersdelight.registry.GuiItems
import fr.ateastudio.farmersdelight.registry.GuiTextures
import fr.ateastudio.farmersdelight.registry.RecipeTypes
import fr.ateastudio.farmersdelight.registry.Sounds
import fr.ateastudio.farmersdelight.registry.Tags
import fr.ateastudio.farmersdelight.util.getCraftingRemainingItem
import fr.ateastudio.farmersdelight.util.hasCraftingRemainingItem
import fr.ateastudio.farmersdelight.util.isTag
import fr.ateastudio.farmersdelight.util.safeGive
import net.kyori.adventure.key.Key
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import net.minecraft.core.component.DataComponents
import net.minecraft.util.Mth
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.Particle
import org.bukkit.Sound
import org.bukkit.SoundCategory
import org.bukkit.block.BlockFace
import org.bukkit.entity.ExperienceOrb
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.ItemStack
import org.bukkit.util.Vector
import xyz.xenondevs.cbf.Compound
import xyz.xenondevs.commons.provider.mapNonNull
import xyz.xenondevs.invui.Click
import xyz.xenondevs.invui.gui.Gui
import xyz.xenondevs.invui.inventory.event.ItemPostUpdateEvent
import xyz.xenondevs.invui.inventory.event.ItemPreUpdateEvent
import xyz.xenondevs.invui.inventory.event.PlayerUpdateReason
import xyz.xenondevs.invui.inventory.get
import xyz.xenondevs.invui.item.AbstractItem
import xyz.xenondevs.invui.item.ItemBuilder
import xyz.xenondevs.invui.item.ItemProvider
import xyz.xenondevs.nova.context.Context
import xyz.xenondevs.nova.context.intention.BlockBreak
import xyz.xenondevs.nova.context.intention.BlockInteract
import xyz.xenondevs.nova.util.BlockUtils
import xyz.xenondevs.nova.util.center
import xyz.xenondevs.nova.util.item.novaItem
import xyz.xenondevs.nova.util.runTask
import xyz.xenondevs.nova.util.unwrap
import xyz.xenondevs.nova.world.BlockPos
import xyz.xenondevs.nova.world.InteractionResult
import xyz.xenondevs.nova.world.block.state.NovaBlockState
import xyz.xenondevs.nova.world.block.tileentity.TileEntity
import xyz.xenondevs.nova.world.block.tileentity.menu.TileEntityMenuClass
import xyz.xenondevs.nova.world.item.recipe.NovaRecipe
import xyz.xenondevs.nova.world.item.recipe.RecipeManager
import kotlin.math.min
import kotlin.random.Random

private const val CLICK_COOLDOWN_TIME: Long = 100

class CookingPot(
    pos: BlockPos,
    state: NovaBlockState,
    data: Compound
) : TileEntity(pos, state, data) {
    private val validContainer = listOf(Material.BOWL, Material.BUCKET, Material.GLASS_BOTTLE)
    private val ingredientsInventory = storedInventory("ingredients_inventory", 6, postUpdateHandler = ::validateRecipe)
    private val mealStorageInventory = storedInventory("meal_display_slot", 1, true, IntArray(1) {99}, preUpdateHandler =  ::preventSteal, ::handleNewStoredItem)
    private val containerInventory = storedInventory("container_slot", 1, ::validateContainer, ::useContainer)
    private val outputInventory = storedInventory("output_slot", 1, ::preventOutputInput, ::takeOutput)
    
    private var storedXp: Float by storedValue<Float>("storedXp", true) {0.0f}
    private var currentRecipe: CookingPotRecipe? by storedValue<Key>("currentRecipe", true).mapNonNull(
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
            return currentRecipe?.result?.getCraftingRemainingItem() ?: ItemStack.empty()
        }
    
    override fun useItemOn(ctx: Context<BlockInteract>): InteractionResult {
        val player = ctx[BlockInteract.SOURCE_PLAYER]  ?: return InteractionResult.Pass
        val hand = ctx[BlockInteract.HELD_HAND]
        val clickItem = if (hand == EquipmentSlot.HAND) player.inventory.itemInMainHand else player.inventory.itemInOffHand
        val container = mealContainerStack
        if (!container.isEmpty &&
            container.isSimilar(clickItem) &&
            !mealStorageInventory.isEmpty) {
            mealStorageInventory.addItemAmount(SELF_UPDATE_REASON, 0, -1)
            clickItem.subtract()
            player.safeGive(currentRecipe!!.result)
            pos.world.playSound(pos.location, Sound.ITEM_ARMOR_EQUIP_GENERIC, 1.0f, 1.0f)
            return InteractionResult.Success()
        }
        return InteractionResult.Pass
    }
    
    override fun use(ctx: Context<BlockInteract>): InteractionResult {
        val player = ctx[BlockInteract.SOURCE_PLAYER]  ?: return InteractionResult.Pass
        val pos = ctx[BlockInteract.BLOCK_POS]
        
        if (!player.isSneaking) {
            return super.use(ctx)
        }
        
        if (!canClick((pos.location))) return InteractionResult.Fail
        val state = pos.novaBlockState
        if (state != null) {
            val isHandle = state[BlockStateProperties.SUPPORT] == CookingPotSupport.HANDLE
            val default = updateSupport(pos)
            if (isHandle) {
                if (default != CookingPotSupport.HANDLE) {
                    BlockUtils.updateBlockState(pos, state.with(BlockStateProperties.SUPPORT, default))
                }
                else if (pos.below.block.isTag(Tags.TRAY_HEAT_SOURCE)) {
                    BlockUtils.updateBlockState(pos, state.with(BlockStateProperties.SUPPORT, CookingPotSupport.TRAY))
                }
                else {
                    BlockUtils.updateBlockState(pos, state.with(BlockStateProperties.SUPPORT, CookingPotSupport.NONE))
                }
            }
            else {
                BlockUtils.updateBlockState(pos, state.with(BlockStateProperties.SUPPORT, CookingPotSupport.HANDLE))
            }
            pos.world.playSound(pos.location, Sound.BLOCK_LANTERN_PLACE, SoundCategory.BLOCKS, 0.8f, 1f)
            return InteractionResult.Success()
        }
        return super.use(ctx)
    }
    
    private fun updateSupport(pos: BlockPos, blockFace: BlockFace? = null) : CookingPotSupport{
        val isSuspended = !pos.add(0,1,0).block.isEmpty
        val isTray = pos.below.block.isTag(Tags.TRAY_HEAT_SOURCE)
        
        if (isSuspended && isTray && blockFace != null) {
            return if (blockFace == BlockFace.DOWN) {
                CookingPotSupport.HANDLE
            } else {
                CookingPotSupport.TRAY
            }
        }
        else if (isSuspended) {
            return CookingPotSupport.HANDLE
        }
        else if (isTray) {
            return CookingPotSupport.TRAY
        }
        else {
            return CookingPotSupport.NONE
        }
    }
    
    private fun canClick(location: Location) : Boolean {
        val currentTime = System.currentTimeMillis()
        
        // Remove expired entries (older than 1 second)
        removeExpiredEntries(currentTime)
        
        // Check if the location is in the map and if it is still within the cooldown period
        return if (clickMap.containsKey(location)) {
            val lastClickTime = clickMap[location]!!
            if (currentTime - lastClickTime < CLICK_COOLDOWN_TIME) {
                false
            } else {
                clickMap.remove(location)
                true
            }
        } else {
            clickMap[location] = currentTime
            true
        }
    }
    
    override fun handleBreak(ctx: Context<BlockBreak>) {
        val pos = ctx[BlockBreak.BLOCK_POS]
        rewardRecipeExperience(pos.block.center)
        super.handleBreak(ctx)
    }
    
    override fun getDrops(includeSelf: Boolean): List<ItemStack> {
        val drop = createDropsWithoutStoredXp(includeSelf)
        if (!includeSelf) return drop
        
        val self = drop[0]
        if (mealStorageInventory.isEmpty) {
            val lore = Component.translatable("farmersdelight.tooltip.cooking_pot.empty")
                .color(NamedTextColor.GRAY)
                .decoration(TextDecoration.ITALIC, false)
            self.lore(listOf(lore))
        }
        else {
            val storedStack = mealStorageInventory[0]!!
            val holdComponent = if (storedStack.amount != 1) {
                Component.translatable("farmersdelight.tooltip.cooking_pot.many_servings",
                    Component.text("${storedStack.amount}")
                )
            }
            else {
                Component.translatable("farmersdelight.tooltip.cooking_pot.single_serving")
            }
            .color(NamedTextColor.GRAY)
            .decoration(TextDecoration.ITALIC, false)
            .decoration(TextDecoration.ITALIC, false)
            
            val displayNameComponent = (storedStack.novaItem?.name ?: storedStack.displayName())
                .color(NamedTextColor.GRAY)
                .decoration(TextDecoration.ITALIC, false)
            self.lore(listOf(holdComponent, displayNameComponent))
            val damage = min(mealStorageInventory.getMaxSlotStackSize(0) - storedStack.amount, mealStorageInventory.getMaxSlotStackSize(0))
            self.unwrap().set(DataComponents.MAX_DAMAGE, mealStorageInventory.getMaxSlotStackSize(0))
            self.unwrap().set(DataComponents.DAMAGE, damage)
        }
        return drop
    }

    private fun createDropsWithoutStoredXp(includeSelf: Boolean): List<ItemStack> {
        val previousStoredXp = storedXp
        storedXp = 0.0f
        return try {
            super.getDrops(includeSelf)
        } finally {
            storedXp = previousStoredXp
        }
    }
    
    override fun handleTick() {
        cookingTick()
        runTask { animateTick() }
    }
    
    private fun cookingTick() {
        val isHeated = isHeated()
        val recipe = currentRecipe
        if (isHeated && hasInput() && recipe != null && checkRecipeIngredients()) {
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
        
        menuContainer.forEachMenu<CookingPotMenu> { it.updateProgress() }
    }
    
    private fun animateTick() {
        if (isHeated()) {
            if (Random.nextFloat() < 0.2f) {
                val x: Double = pos.x.toDouble() + 0.5 + (Random.nextDouble() * 0.6 - 0.3)
                val y = pos.y.toDouble() + 0.7
                val z: Double = pos.z.toDouble() + 0.5 + (Random.nextDouble() * 0.6 - 0.3)
                pos.world.spawnParticle(Particle.BUBBLE_POP, x , y, z, 1, 0.0, 0.0, 0.0, 0.1)
            }
            if (Random.nextFloat() < 0.05f) {
                val x: Double = pos.x.toDouble() + 0.5 + (Random.nextDouble() * 0.4 - 0.2)
                val y = pos.y.toDouble() + 0.5
                val z = pos.z.toDouble() + 0.5 + (Random.nextDouble() * 0.4 - 0.2)
                val motionY = if (Random.nextBoolean()) 0.20 else 0.10
                pos.world.spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, x , y, z, 1, 0.0, motionY, 0.0, 0.1)
            }
            
            val soundEffect = if (!getMeal().isEmpty) Sounds.BLOCK_COOKING_POT_BOIL_SOUP else Sounds.BLOCK_COOKING_POT_BOIL
            val location = pos.location.add(0.5, 0.0, 0.5)
            if (Random.nextInt(5) == 0) {
                location.world.playSound(location, soundEffect, SoundCategory.BLOCKS, 0.5f,Random.nextFloat() * 0.2F + 0.9F)
            }
        }
    }
    
    @Suppress("UNUSED_PARAMETER")
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
    
    private fun handleNewStoredItem(event: ItemPostUpdateEvent) {
        val mealStack = getMeal()
        if (event.isRemove || event.updateReason != SELF_UPDATE_REASON) return
        if (!mealStack.isEmpty) {
            if (!doesMealHaveContainer(mealStack)) {
                moveMealToOutput()
            }
            else if (!containerInventory.isEmpty) {
                useStoredContainersOnMeal()
            }
        }
    }
    
    private fun validateContainer(event: ItemPreUpdateEvent) {
        if (event.updateReason != SELF_UPDATE_REASON && event.newItem != null && !validContainer.contains(event.newItem!!.type)) {
            event.isCancelled = true
        }
    }
    
    private fun useContainer(event: ItemPostUpdateEvent) {
        if (event.updateReason != SELF_UPDATE_REASON) {
            useStoredContainersOnMeal()
        }
    }
    
    private fun preventOutputInput(event: ItemPreUpdateEvent) {
        event.isCancelled = !event.isRemove && event.updateReason != SELF_UPDATE_REASON
    }
    
    private fun takeOutput(event: ItemPostUpdateEvent) {
        if (event.isRemove && event.updateReason is PlayerUpdateReason) {
            val reason = event.updateReason as PlayerUpdateReason
            awardUsedRecipes(reason.player())
            val mealStack = getMeal()
            if (!mealStack.isEmpty) {
                if (!doesMealHaveContainer(mealStack)) {
                    moveMealToOutput()
                }
                else if (!containerInventory.isEmpty) {
                    useStoredContainersOnMeal()
                }
            }
        }
    }
    
    private fun updateResult(result: ItemStack?): ItemStack {
        if (result == null || result.isEmpty) return  ItemStack.empty()
        val meta = result.clone().itemMeta
        meta.setMaxStackSize(mealStorageInventory.getMaxSlotStackSize(0))
        val newStack = result.clone()
        newStack.setItemMeta(meta)
        val container = mealContainerStack
        if (!container.isEmpty) {
            val servedOnComponent = Component.translatable("farmersdelight.container.cooking_pot.served_on", Component.translatable(container.translationKey()))
            val lore = servedOnComponent
                .append(Component.text(" "))
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
                    storedMealStack.amount + resultStack.amount <= resultStack.maxStackSize
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
        setRecipeUsed(recipe)
        
        for (i in 0..<ingredientsInventory.size) {
            val slotStack = ingredientsInventory[i] ?: ItemStack.empty()
            if (slotStack.hasCraftingRemainingItem()) {
                ejectIngredientRemainder(slotStack.getCraftingRemainingItem())
            }
            if (!slotStack.isEmpty) ingredientsInventory.addItemAmount(SELF_UPDATE_REASON, i, -1)
        }
        return true
    }
    
    private fun setRecipeUsed(recipe: CookingPotRecipe?) {
        if (recipe != null) {
            storedXp += recipe.experience
        }
    }
    
    private fun awardUsedRecipes(player: Player) {
        rewardRecipeExperience(player.location)
    }
    
    private fun rewardRecipeExperience(location: Location) {
        if (storedXp == 0.0f) return
        var expTotal = Mth.floor(storedXp)
        val expFraction = Mth.frac(storedXp)
        if (expFraction != 0.0f && Math.random() < expFraction.toDouble()) {
            ++expTotal
        }
        val experienceOrb = location.world.spawn(location, ExperienceOrb::class.java)
        experienceOrb.experience = expTotal
        storedXp = 0.0f
    }
    
    private fun checkRecipeIngredients(): Boolean {
        val recipe = getMatchingRecipe(ingredientsInventory.items.toList())
        return (recipe != null && recipe == currentRecipe && canCook(recipe))
    }
    
    private fun getMatchingRecipe(inputs: List<ItemStack?>): CookingPotRecipe? {
        return RecipeManager.novaRecipes[RecipeTypes.COOKING_POT]?.values?.asSequence()
            ?.map { it as CookingPotRecipe }
            ?.firstOrNull { recipe ->
                recipe.matches(inputs.filterNotNull())
            }
    }
    
    @TileEntityMenuClass
    private inner class CookingPotMenu : GlobalTileEntityMenu(GuiTextures.COOKING_POT) {
        override fun getTitle(): Component {
            return texture?.getTitle(Component.text("    ").append(Component.translatable("farmersdelight.container.cooking_pot"))) ?: Component.text("    ").append(block.name)
        }
        
        private inner class HeatedInfoItem : AbstractItem() {
            
            var heated: Boolean = false
                set(value) {
                    if (field != value) {
                        field = value
                        notifyWindows()
                    }
                }
            
            
            override fun getItemProvider(p0: Player): ItemProvider {
                val itemBuilder = ItemBuilder(if (heated) GuiItems.COOKING_POT_HEATED.createItemStack() else GuiItems.COOKING_POT_NOT_HEATED.createItemStack())
                return itemBuilder
            }
            
            override fun handleClick(clickType: ClickType, player: Player, p2: Click) {
                return
            }
        }
        
        private val heatedInfoItem = HeatedInfoItem()
        private val progressionInfoItem = ProgressArrowItem()
        
        override val gui: Gui = Gui.builder()
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
    
    private val clickMap: MutableMap<Location, Long> = mutableMapOf()
    private fun removeExpiredEntries(currentTime: Long) {
        val iterator = clickMap.iterator()
        while (iterator.hasNext()) {
            val entry = iterator.next()
            if (currentTime - entry.value > CLICK_COOLDOWN_TIME) {
                iterator.remove() // Remove expired entry
            }
        }
    }
    
}