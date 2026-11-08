package fr.ateastudio.farmersdelight.block.behavior

import xyz.xenondevs.nova.world.item.createItemStack

import fr.ateastudio.farmersdelight.block.BlockStateProperties
import org.bukkit.GameMode
import org.bukkit.GameRules
import org.bukkit.block.BlockType
import org.bukkit.Particle
import org.bukkit.Sound
import org.bukkit.block.BlockFace
import org.bukkit.block.data.Levelled
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Entity
import org.bukkit.entity.Ravager
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.ItemType
import org.bukkit.block.Block
import xyz.xenondevs.nova.context.Context
import xyz.xenondevs.nova.context.intention.BlockBreak
import xyz.xenondevs.nova.context.intention.BlockInteract
import xyz.xenondevs.nova.context.intention.BlockPlace
import xyz.xenondevs.nova.registry.RegistryEntry
import xyz.xenondevs.nova.util.BlockUtils
import xyz.xenondevs.nova.util.below
import xyz.xenondevs.nova.world.InteractionResult
import xyz.xenondevs.nova.world.block.behavior.BlockBehavior
import xyz.xenondevs.nova.world.block.NovaBlockState
import xyz.xenondevs.nova.world.block.blockType
import xyz.xenondevs.nova.world.item.itemType
import kotlin.random.Random


@Suppress("UnstableApiUsage")
abstract class CropBlock : BlockBehavior {
    open val dropProbability = 0.57
    open val badCropResultProbability = 0.05f
    
    abstract fun resultItem(): RegistryEntry.Paper<ItemType>?
    protected open fun badResultItem(): RegistryEntry.Paper<ItemType>? = null
    abstract fun seedItem(): RegistryEntry.Paper<ItemType>?
    
    override fun getDrops(block: Block, state: NovaBlockState, ctx: Context<BlockBreak>): List<ItemStack> {
        val player = ctx[BlockBreak.SOURCE_PLAYER]
        val tool = ctx[BlockBreak.TOOL_ITEM_STACK]
        val seedItem = seedItem()
        val resultItem = resultItem()
        val badResultItem = badResultItem()
        if (!ctx[BlockBreak.BLOCK_DROPS] || player?.gameMode == GameMode.CREATIVE) {
            return emptyList()
        }
        if (isMaxAge(state)) {
            val result = mutableListOf<ItemStack>()
            if (seedItem != null) {
                // Determine the amount of drops, with possible modification from the Fortune enchantment
                val fortuneLevel = tool.getEnchantmentLevel(Enchantment.FORTUNE)
                val quantity = simulateBinomialDrops(fortuneLevel)
                result.add(seedItem.createItemStack(quantity))
            }
            if (resultItem != null) {
                if (badResultItem != null && Random.nextFloat() < badCropResultProbability) {
                    result.add(badResultItem.createItemStack())
                }
                else {
                    result.add(resultItem.createItemStack())
                }
            }
            return result
        }
        else {
            if (seedItem != null) {
                return listOf(seedItem.createItemStack())
            }
            return listOf()
        }
    }
    
    override fun useItemOn(block: Block, state: NovaBlockState, ctx: Context<BlockInteract>): InteractionResult {
        val player = ctx[BlockInteract.SOURCE_PLAYER] ?: return InteractionResult.Pass
        val hand = ctx[BlockInteract.HELD_HAND]
        val itemStack = if (hand == EquipmentSlot.HAND) player.inventory.itemInMainHand else player.inventory.itemInOffHand
        
        if (itemStack.itemType == ItemType.BONE_MEAL && isValidBoneMealTarget(state)) {
            if (player.gameMode != GameMode.CREATIVE) {
                itemStack.subtract()
            }
            performBoneMeal(block, state)
            return InteractionResult.Success()
        }
        return InteractionResult.Pass
    }
    
    override fun ticksRandomly(state: NovaBlockState): Boolean {
        val shouldTick = !isMaxAge(state)
        return shouldTick
    }
    
    override fun handleRandomTick(block: Block, state: NovaBlockState) {
        val lightLevel = getRawBrightness(block)
        
        if (lightLevel >= 9) {
            val age = getAge(state)
            if (age < getMaxAge(state)) {
                val growSpeed = getGrowthSpeed(block)
                val tickSpeedMultiplier = block.world.getGameRuleValue(GameRules.RANDOM_TICK_SPEED) / 3
                repeat(tickSpeedMultiplier) {
                if (Random.nextInt(((25.0F / growSpeed) + 1).toInt()) == 0) {
                        growCrop(block, state)
                    }
                }
            }
        }
        if (!canSurvive(state, block)) {
            breakBlock(block)
        }
    }
    
    override fun handleNeighborChanged(block: Block, state: NovaBlockState) {
        if (!canSurvive(state, block)) {
            breakBlock(block)
        }
    }
    
    override fun handleEntityInside(block: Block, state: NovaBlockState, entity: Entity) {
        if (entity is Ravager && block.world.getGameRuleValue(GameRules.MOB_GRIEFING) == true) {
            block.breakNaturally()
        }
    }
    
    override suspend fun canPlace(block: Block, data: NovaBlockState, ctx: Context<BlockPlace>): Boolean {
        return mayPlaceOn(block.below, data) && block.isEmpty
    }
    
    protected fun getMaxAge(state: NovaBlockState): Int {
        return state.getOrThrow(BlockStateProperties.MAX_AGE)
    }
    
    protected fun getAge(state: NovaBlockState): Int {
        return state.getOrThrow(BlockStateProperties.AGE)
    }
    
    protected fun getStateForAge(state: NovaBlockState, age: Int) : NovaBlockState {
        val newState = state.clone()
        newState[BlockStateProperties.AGE] = age
        return newState
    }
    
    protected fun isMaxAge(state: NovaBlockState) : Boolean {
        return getAge(state) == getMaxAge(state)
    }
    
    protected open fun growCrop(pos: Block, state: NovaBlockState, amount: Int = 1) {
        if (!isMaxAge(state)) {
            val age = getAge(state) + amount
            val maxAge = getMaxAge(state)
            if (age > maxAge)
                pos.blockData = getStateForAge(state, maxAge)
            else
                pos.blockData = getStateForAge(state, age)
        }
    }
    
    private fun getBonemealAgeIncrease(state: NovaBlockState) : Int {
        val maxAge = getMaxAge(state)
        val min = (maxAge * (2.0F/7)).toInt()
        val max = (maxAge * (5.0F/7)).toInt()
        return Random.nextInt(min, max + 1)
    }
    
    private fun getGrowthSpeed(blockPos: Block): Float {
        val cropBlock = blockPos
        var growthSpeed = 1.0f
        val belowBlockPos = blockPos.below
        
        // Loop through adjacent blocks (in a 3x3 area around the crop)
        for (xOffset in -1..1) {
            for (zOffset in -1..1) {
                var individualSpeed = 0.0f
                val adjacentBlock = belowBlockPos.getRelative(xOffset, 0, zOffset)
                
                // Check if block is farmland
                if (adjacentBlock.blockType == BlockType.FARMLAND) {
                    individualSpeed = 1.0f
                    val blockData = adjacentBlock.blockData
                    if (blockData is Levelled && blockData.level > 0) {
                        individualSpeed = 3.0f  // Moist farmland boosts growth
                    }
                }
                
                // Reduce growth speed from non-central adjacent blocks
                if (xOffset != 0 || zOffset != 0) {
                    individualSpeed /= 4.0f
                }
                
                growthSpeed += individualSpeed
            }
        }
        
        // Check for blocks in the cardinal directions (north, south, east, west)
        val northBlock = cropBlock.getRelative(BlockFace.NORTH)
        val southBlock = cropBlock.getRelative(BlockFace.SOUTH)
        val westBlock = cropBlock.getRelative(BlockFace.WEST)
        val eastBlock = cropBlock.getRelative(BlockFace.EAST)
        
        val hasHorizontalAdjacent = (westBlock.blockType == cropBlock.blockType || eastBlock.blockType == cropBlock.blockType)
        val hasVerticalAdjacent = (northBlock.blockType == cropBlock.blockType || southBlock.blockType == cropBlock.blockType)
        
        // If both horizontal and vertical adjacent crops exist, reduce growth speed
        if (hasHorizontalAdjacent && hasVerticalAdjacent) {
            growthSpeed /= 2.0f
        } else {
            // Check for diagonal adjacent crops
            val hasDiagonalAdjacent = (northBlock.getRelative(BlockFace.WEST).blockType == cropBlock.blockType ||
                northBlock.getRelative(BlockFace.EAST).blockType == cropBlock.blockType ||
                southBlock.getRelative(BlockFace.WEST).blockType == cropBlock.blockType ||
                southBlock.getRelative(BlockFace.EAST).blockType == cropBlock.blockType)
            
            if (hasDiagonalAdjacent) {
                growthSpeed /= 2.0f
            }
        }
        
        return growthSpeed
    }
    
    protected open fun canSurvive(state: NovaBlockState, pos: Block): Boolean {
        val blockBelow = pos.below
        return mayPlaceOn(blockBelow, state)
    }
    
    protected open fun hasSufficientLight(pos: Block): Boolean {
        return (getRawBrightness(pos) >= 8)
    }
    
    protected fun isValidBoneMealTarget(state: NovaBlockState): Boolean {
        return !isMaxAge(state)
    }
    
    protected open fun performBoneMeal(pos: Block, state: NovaBlockState) {
        val amount = getBonemealAgeIncrease(state)
        growCrop(pos, state, amount)
        showBoneMealParticle(pos)
    }
    
    protected fun showBoneMealParticle(pos: Block) {
        
        // Spawn the "happy villager" particles (green particles like bone meal effect)
        pos.world.spawnParticle(
            Particle.HAPPY_VILLAGER, // Green particle
            pos.location.toCenterLocation(),                // Where to spawn the particle
            8,                      // Number of particles
            0.25, 0.0, 0.5,           // X, Y, Z offsets (to spread particles around)
            0.1                      // Speed of the particles (can be 0 for slow-moving particles)
        )
        pos.world.playSound(pos.location, Sound.ITEM_BONE_MEAL_USE, 1.0f, 1.0f)
    }
    
    protected fun getRawBrightness(pos: Block): Byte {
        return if (pos.lightLevel < pos.lightFromSky) {
            pos.lightFromSky
        } else {
            pos.lightLevel
        }
    }
    
    protected open fun mayPlaceOn(pos: Block, state: NovaBlockState): Boolean {
        return pos.blockType == BlockType.FARMLAND && hasSufficientLight(pos)
    }
    
    protected fun simulateBinomialDrops(fortuneLevel: Int): Int {
        val trials = 3 + fortuneLevel // n trials: 3 base trials + 1 for each level of Fortune
        var seedDrops = 0
        
        // Perform binomial trials
        repeat(trials) {
            if (Random.nextDouble(1.0) < dropProbability) {
                seedDrops++
            }
        }
        
        return seedDrops.coerceAtLeast(1) // Ensure at least 1 seed drops
    }
    
    protected fun breakBlock(position: Block){
        val context = Context.intention(BlockBreak)
            .param(BlockBreak.BLOCK, position)
            .param(BlockBreak.BLOCK_BREAK_EFFECTS, true)
            .build()
        
        BlockUtils.breakBlockNaturally(context)
    }
}
