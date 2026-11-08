package fr.ateastudio.farmersdelight.tileentity

import fr.ateastudio.farmersdelight.block.BlockStateProperties
import fr.ateastudio.farmersdelight.block.ComparatorSignalSource
import fr.ateastudio.farmersdelight.block.ComparatorSignals
import fr.ateastudio.farmersdelight.block.comparatorFullness
import fr.ateastudio.farmersdelight.block.fullnessSlots
import fr.ateastudio.farmersdelight.block.CookingPotSupport
import fr.ateastudio.farmersdelight.block.CookingPotSupportRules
import fr.ateastudio.farmersdelight.gui.ProgressArrowItem
import fr.ateastudio.farmersdelight.recipe.cookingpot.CookingPotRecipe
import fr.ateastudio.farmersdelight.registry.GuiItems
import fr.ateastudio.farmersdelight.registry.GuiTextures
import fr.ateastudio.farmersdelight.registry.RecipeTypes
import fr.ateastudio.farmersdelight.registry.Sounds
import fr.ateastudio.farmersdelight.util.counterClockWise
import fr.ateastudio.farmersdelight.util.getCraftingRemainingItem
import fr.ateastudio.farmersdelight.util.hasCraftingRemainingItem
import fr.ateastudio.farmersdelight.util.isHeatedFromBelow
import fr.ateastudio.farmersdelight.util.isTrayHeatSource
import fr.ateastudio.farmersdelight.util.safeGive
import net.kyori.adventure.key.Key
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import io.papermc.paper.datacomponent.DataComponentTypes
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
import org.bukkit.block.Block
import xyz.xenondevs.nova.context.Context
import xyz.xenondevs.nova.context.intention.BlockBreak
import xyz.xenondevs.nova.context.intention.BlockInteract
import xyz.xenondevs.nova.ui.overlay.guitexture.getTitle
import xyz.xenondevs.nova.util.add
import xyz.xenondevs.nova.util.below
import xyz.xenondevs.nova.util.center
import xyz.xenondevs.nova.world.InteractionResult
import xyz.xenondevs.nova.world.block.NovaBlockState
import xyz.xenondevs.nova.world.block.state.property.DefaultBlockStateProperties
import xyz.xenondevs.nova.util.CubeFaceMap
import xyz.xenondevs.nova.world.block.tileentity.NetworkedTileEntity
import xyz.xenondevs.nova.world.block.tileentity.network.type.NetworkConnectionType
import xyz.xenondevs.nova.world.block.tileentity.network.type.item.inventory.NetworkedInventory
import xyz.xenondevs.nova.world.block.tileentity.network.type.item.inventory.NetworkedVirtualInventory
import fr.ateastudio.farmersdelight.tileentity.network.CookingPotServingInventory
import fr.ateastudio.farmersdelight.tileentity.network.RateLimitedNetworkedInventory
import xyz.xenondevs.nova.world.block.tileentity.TileEntityMenu
import xyz.xenondevs.nova.world.item.createItemStack
import xyz.xenondevs.nova.world.item.isNova
import xyz.xenondevs.nova.world.item.itemType
import xyz.xenondevs.nova.world.item.name
import xyz.xenondevs.nova.world.item.recipe.NovaRecipe
import xyz.xenondevs.nova.world.item.recipe.RecipeManager
import kotlin.math.floor
import kotlin.math.min
import kotlin.random.Random

private const val CLICK_COOLDOWN_TIME: Long = 100

internal fun retreatCookingProgress(progress: Int, total: Int): Int =
    (progress - 2).coerceIn(0, maxOf(total, 0))

class CookingPot(
    block: Block,
    state: NovaBlockState,
    data: Compound
) : NetworkedTileEntity(block, state, data), ComparatorSignalSource {
    private val validContainer = listOf(Material.BOWL, Material.BUCKET, Material.GLASS_BOTTLE, Material.PUMPKIN)
    private val ingredientsInventory = storedInventory("ingredients_inventory", 6, postUpdateHandler = ::validateRecipe)
    private val mealStorageInventory = storedInventory("meal_display_slot", 1, true, IntArray(1) {99}, preUpdateHandler =  ::preventSteal, ::handleNewStoredItem)
    private val containerInventory = storedInventory("container_slot", 1, ::validateContainer, ::useContainer)
    private val outputInventory = storedInventory("output_slot", 1, ::preventOutputInput, ::takeOutput)
    
    // Top: the six ingredient slots only. Every other face: serving containers in, output out. Never the reservoir.
    private val networkedIngredients = RateLimitedNetworkedInventory(NetworkedVirtualInventory(ingredientsInventory))
    private val networkedServing = CookingPotServingInventory(containerInventory, outputInventory)
    private val itemHolder = storedItemHolder(
        networkedIngredients to NetworkConnectionType.BUFFER,
        networkedServing to NetworkConnectionType.BUFFER,
        defaultInventoryConfig = CubeFaceMap<NetworkedInventory?>(networkedServing).with(BlockFace.UP, networkedIngredients)
    )
    
    private var storedXp: Float by storedValue<Float>("storedXp", true) {0.0f}
    private var currentRecipe: CookingPotRecipe? by storedValue<Key>("currentRecipe", true).mapNonNull(
        { RecipeManager.getRecipe(RecipeTypes.COOKING_POT.get(), it) },
        NovaRecipe::id
    )
    
    private var cookTime: Int by storedValue("cookTime") { 0 }
    private val cookTimeTotal: Int
        get() {
            return currentRecipe?.time ?: 0
        }
    
    private val mealContainerStack: ItemStack
        get() {
            val recipe = currentRecipe ?: return ItemStack.empty()
            return recipe.container?.clone() ?: recipe.result.getCraftingRemainingItem()
        }
    
    override fun useItemOn(ctx: Context<BlockInteract>): InteractionResult {
        val player = ctx[BlockInteract.SOURCE_PLAYER]  ?: return InteractionResult.Pass
        val hand = ctx[BlockInteract.HELD_HAND]
        val clickItem = if (hand == EquipmentSlot.HAND) player.inventory.itemInMainHand else player.inventory.itemInOffHand
        val container = mealContainerStack
        val recipe = currentRecipe
        if (recipe != null &&
            !container.isEmpty &&
            container.isSimilar(clickItem) &&
            !mealStorageInventory.isEmpty) {
            mealStorageInventory.addItemAmount(SELF_UPDATE_REASON, 0, -1)
            clickItem.subtract()
            player.safeGive(recipe.result.asOne())
            block.world.playSound(block.location, Sound.ITEM_ARMOR_EQUIP_GENERIC, 1.0f, 1.0f)
            return InteractionResult.Success()
        }
        return InteractionResult.Pass
    }
    
    override fun use(ctx: Context<BlockInteract>): InteractionResult {
        val player = ctx[BlockInteract.SOURCE_PLAYER]  ?: return InteractionResult.Pass
        val block = ctx[BlockInteract.BLOCK]
        
        if (!player.isSneaking) {
            return super.use(ctx)
        }
        
        if (!canToggleSupport()) return InteractionResult.Fail
        val trayBelow = block.below.isTrayHeatSource()
        updateLiveBlockState {
            val isHandle = this[BlockStateProperties.SUPPORT] == CookingPotSupport.HANDLE
            this[BlockStateProperties.SUPPORT] =
                if (isHandle) CookingPotSupportRules.onPlacement(false, trayBelow) else CookingPotSupport.HANDLE
        }
        block.world.playSound(block.location, Sound.BLOCK_LANTERN_PLACE, SoundCategory.BLOCKS, 0.8f, 1f)
        return InteractionResult.Success()
    }
    
    private fun canToggleSupport(): Boolean {
        val now = System.currentTimeMillis()
        if (now - lastSupportToggleMillis < CLICK_COOLDOWN_TIME) return false
        lastSupportToggleMillis = now
        return true
    }
    
    override fun handleBreak(ctx: Context<BlockBreak>) {
        val block = ctx[BlockBreak.BLOCK]
        rewardRecipeExperience(block.center)
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
            
            val displayNameComponent = (if (storedStack.itemType.isNova) storedStack.itemType.name else storedStack.displayName())
                .color(NamedTextColor.GRAY)
                .decoration(TextDecoration.ITALIC, false)
            self.lore(listOf(holdComponent, displayNameComponent))
            val damage = min(mealStorageInventory.getMaxSlotStackSize(0) - storedStack.amount, mealStorageInventory.getMaxSlotStackSize(0))
            self.setData(DataComponentTypes.MAX_DAMAGE, mealStorageInventory.getMaxSlotStackSize(0))
            self.setData(DataComponentTypes.DAMAGE, damage)
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
        // Heat reads several blocks; compute it once per tick and share it.
        val heated = isHeated()
        syncBlockState(heated)
        cookingTick(heated)
        refreshComparatorSignal()
        animateTick(heated)
    }
    
    /** Neighbour-driven state, owned by the tile entity: tray support below and the heated flag. */
    private fun syncBlockState(heated: Boolean) {
        val trayBelow = block.below.isTrayHeatSource()
        updateLiveBlockState {
            val current = this[BlockStateProperties.SUPPORT] ?: CookingPotSupport.NONE
            this[BlockStateProperties.SUPPORT] = CookingPotSupportRules.afterNeighborUpdate(current, trayBelow)
            this[BlockStateProperties.HEATED] = heated
        }
    }
    
    private var lastComparatorSignal = -1
    
    /** Fullness over all nine slots: six ingredients, the meal reservoir, the container input and the output. */
    override fun comparatorSignal(): Int = comparatorFullness(
        ingredientsInventory.fullnessSlots() + mealStorageInventory.fullnessSlots() +
            containerInventory.fullnessSlots() + outputInventory.fullnessSlots()
    )
    
    private fun refreshComparatorSignal() {
        val signal = comparatorSignal()
        if (signal == lastComparatorSignal) return
        lastComparatorSignal = signal
        ComparatorSignals.notifyChanged(block)
    }
    
    private fun cookingTick(heated: Boolean) {
        val recipe = currentRecipe
        if (heated && hasInput() && recipe != null && checkRecipeIngredients()) {
            if (canCook(recipe)) {
                processCooking(recipe)
            }
            else {
                cookTime = retreatCookingProgress(cookTime, cookTimeTotal)
            }
        }
        else if (cookTime > 0) {
            cookTime = retreatCookingProgress(cookTime, cookTimeTotal)
        }
        
        updateProgress(heated)
    }
    
    private fun animateTick(heated: Boolean) {
        if (heated) {
            if (Random.nextFloat() < 0.2f) {
                val x: Double = block.x.toDouble() + 0.5 + (Random.nextDouble() * 0.6 - 0.3)
                val y = block.y.toDouble() + 0.7
                val z: Double = block.z.toDouble() + 0.5 + (Random.nextDouble() * 0.6 - 0.3)
                block.world.spawnParticle(Particle.BUBBLE_POP, x , y, z, 1, 0.0, 0.0, 0.0, 0.1)
            }
            if (Random.nextFloat() < 0.05f) {
                val x: Double = block.x.toDouble() + 0.5 + (Random.nextDouble() * 0.4 - 0.2)
                val y = block.y.toDouble() + 0.5
                val z = block.z.toDouble() + 0.5 + (Random.nextDouble() * 0.4 - 0.2)
                val motionY = if (Random.nextBoolean()) 0.20 else 0.10
                block.world.spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, x , y, z, 1, 0.0, motionY, 0.0, 0.1)
            }
            
            val soundEffect = if (!getMeal().isEmpty) Sounds.BLOCK_COOKING_POT_BOIL_SOUP else Sounds.BLOCK_COOKING_POT_BOIL
            val location = block.location.add(0.5, 0.0, 0.5)
            if (Random.nextInt(5) == 0) {
                location.world.playSound(location, soundEffect, SoundCategory.BLOCKS, 0.5f,Random.nextFloat() * 0.2F + 0.9F)
            }
        }
    }
    
    @Suppress("UNUSED_PARAMETER")
    private fun validateRecipe(event: ItemPostUpdateEvent) {
        val recipe = getMatchingRecipe(ingredientsInventory.items.toList())
        matchedRecipe = recipe
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
        if (!event.isRemove) return
        val reason = event.updateReason
        if (reason is PlayerUpdateReason) {
            awardUsedRecipes(reason.player())
        } else if (reason != NetworkedVirtualInventory.UPDATE_REASON) {
            return
        }
        // Refill the output after manual or automated extraction; experience is only awarded to players.
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
    
    private fun isHeated(): Boolean = block.isHeatedFromBelow()
    
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
        val direction = (blockState[DefaultBlockStateProperties.FACING_HORIZONTAL] ?: BlockFace.NORTH).counterClockWise()
        val location = block.location.add(0.5 + direction.modX * 0.25, 0.7, 0.5 + direction.modZ * 0.25)
        val itemEntity = block.world.dropItem(location, remainderStack)
        itemEntity.velocity = Vector(direction.modX * 0.08, 0.25, direction.modZ * 0.08)
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
            storedMealStack.amount += resultStack.amount
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
        var expTotal = floor(storedXp).toInt()
        val expFraction = storedXp - floor(storedXp)
        if (expFraction != 0.0f && Math.random() < expFraction.toDouble()) {
            ++expTotal
        }
        val experienceOrb = location.world.spawn(location, ExperienceOrb::class.java)
        experienceOrb.experience = expTotal
        storedXp = 0.0f
    }
    
    private fun checkRecipeIngredients(): Boolean {
        val recipe = matchedRecipe
        return (recipe != null && recipe == currentRecipe && canCook(recipe))
    }
    
    private fun getMatchingRecipe(inputs: List<ItemStack?>): CookingPotRecipe? {
        return RecipeManager.novaRecipes[RecipeTypes.COOKING_POT.get()]?.values?.asSequence()
            ?.map { it as CookingPotRecipe }
            ?.firstOrNull { recipe ->
                recipe.matches(inputs.filterNotNull())
            }
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
    
    private fun cookingPotGui(): Gui = Gui.builder()
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
    
    @OptIn(xyz.xenondevs.invui.dsl.ExperimentalDslApi::class)
    override val menu: TileEntityMenu = TileEntityMenu.cachedWindow(GuiTextures.COOKING_POT) {
        title by GuiTextures.COOKING_POT.getTitle(
            Component.text("    ").append(Component.translatable("farmersdelight.container.cooking_pot"))
        )
        upperGui by cookingPotGui()
    }
    
    /**
     * Recipe matching the current ingredients. Every ingredient change goes through [validateRecipe], so the
     * (order-independent) matching runs on changes only, not on every cooking tick.
     */
    private var matchedRecipe: CookingPotRecipe? = null
    
    init {
        // The constructor runs while the chunk is still loading: reading any block here (e.g. heat below)
        // makes the server wait synchronously for that same chunk and hang. Heat is refreshed on the first tick.
        progressionInfoItem.percentage = progressPercentage()
        matchedRecipe = getMatchingRecipe(ingredientsInventory.items.toList())
    }
    
    private fun progressPercentage(): Double =
        if (currentRecipe != null && cookTimeTotal > 0) cookTime.toDouble() / cookTimeTotal.toDouble() else 0.0
    
    private fun updateProgress(heated: Boolean) {
        progressionInfoItem.percentage = progressPercentage()
        heatedInfoItem.heated = heated
    }
    
    /** Last sneak-toggle of the handle, so main- and off-hand use packets of one click toggle only once. */
    private var lastSupportToggleMillis = 0L
    
}