package fr.ateastudio.farmersdelight.block.behavior

import org.bukkit.GameMode
import org.bukkit.GameRule
import org.bukkit.Material
import org.bukkit.Particle
import org.bukkit.Sound
import org.bukkit.block.BlockFace
import org.bukkit.block.data.Levelled
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Entity
import org.bukkit.entity.Ravager
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.ItemStack
import xyz.xenondevs.nova.context.Context
import xyz.xenondevs.nova.context.intention.DefaultContextIntentions
import xyz.xenondevs.nova.context.intention.DefaultContextIntentions.BlockInteract
import xyz.xenondevs.nova.context.param.DefaultContextParamTypes
import xyz.xenondevs.nova.util.BlockUtils
import xyz.xenondevs.nova.world.BlockPos
import xyz.xenondevs.nova.world.block.behavior.BlockBehavior
import xyz.xenondevs.nova.world.block.state.NovaBlockState
import xyz.xenondevs.nova.world.item.NovaItem
import kotlin.random.Random


abstract class CropBlock() : BlockBehavior {
    open val dropProbability = 0.57
    abstract fun stages() : List<NovaBlockState>
    abstract fun resultItem() : NovaItem?
    abstract fun seedItem() : NovaItem?
    
    override fun getDrops(pos: BlockPos, state: NovaBlockState, ctx: Context<DefaultContextIntentions.BlockBreak>): List<ItemStack> {
        val player = ctx[DefaultContextParamTypes.SOURCE_PLAYER]
        val tool = ctx[DefaultContextParamTypes.TOOL_ITEM_STACK]
        val seedItem = seedItem()
        val resultItem = resultItem()
        if (player?.gameMode == GameMode.CREATIVE) {
            return emptyList()
        }
        if (isMaxAge(state)) {
            val result = mutableListOf<ItemStack>()
            if (seedItem is NovaItem) {
                // Determine the amount of drops, with possible modification from the Fortune enchantment
                val fortuneLevel = tool?.getEnchantmentLevel(Enchantment.FORTUNE) ?: 0
                val quantity = simulateBinomialDrops(fortuneLevel)
                result.add(seedItem.createItemStack(quantity))
            }
            if (resultItem is NovaItem) {
                result.add(resultItem.createItemStack())
            }
            return result
        }
        else {
            if (seedItem is NovaItem) {
                return listOf(seedItem.createItemStack())
            }
            return listOf()
        }
    }
    
    override fun handleInteract(pos: BlockPos, state: NovaBlockState, ctx: Context<BlockInteract>): Boolean {
        val player = ctx[DefaultContextParamTypes.SOURCE_PLAYER]
        var itemStack = ctx[DefaultContextParamTypes.INTERACTION_ITEM_STACK]
        
        if (player != null && itemStack != null && itemStack.type == Material.BONE_MEAL && isValidBoneMealTarget(pos, state)) {
            if (player.gameMode != GameMode.CREATIVE) {
                if (itemStack.amount > 0) {
                    if (itemStack.amount > 1) {
                        itemStack.amount -= 1 // Reduce the amount by 1
                    } else {
                        // If there's only 1 item, set the slot to null (removes the item)
                        itemStack = null
                    }
                }
                val inventory = player.inventory
                val slot = ctx[DefaultContextParamTypes.INTERACTION_HAND]
                when (slot) {
                    EquipmentSlot.HAND -> inventory.setItemInMainHand(itemStack)
                    EquipmentSlot.OFF_HAND -> inventory.setItemInOffHand(itemStack)
                    else -> {}
                }
            }
            performBoneMeal(pos, state)
            return true
        }
        return false
    }
    
    override fun ticksRandomly(state: NovaBlockState): Boolean {
        return !isMaxAge(state)
    }
    
    override fun handleRandomTick(pos: BlockPos, state: NovaBlockState) {
        val lightLevel = getRawBrightness(pos)
        
        if (lightLevel >= 9) {
            val age = getAge(state)
            if (age < getMaxAge()) {
                val growSpeed = getGrowthSpeed(pos)
                val tickSpeedMultiplier = (pos.world.getGameRuleValue(GameRule.RANDOM_TICK_SPEED) ?: 3) / 3
                for (i in 1..tickSpeedMultiplier) {
                    if (Random.nextInt(((25.0F / growSpeed) + 1).toInt()) == 0) {
                        val block = getStateForAge(age + 1)
                        setBlock(pos, block)
                    }
                }
            }
        }
        if (!canSurvive(state, pos)) {
            breakBlock(pos)
        }
    }
    
    override fun handleNeighborChanged(pos: BlockPos, state: NovaBlockState, neighborPos: BlockPos) {
        if (!canSurvive(state, pos)) {
            breakBlock(pos)
        }
    }
    
    override fun handleEntityInside(pos: BlockPos, state: NovaBlockState, entity: Entity) {
        if (entity is Ravager && pos.world.getGameRuleValue(GameRule.MOB_GRIEFING) == true) {
            pos.block.breakNaturally()
        }
    }
    
    override suspend fun canPlace(pos: BlockPos, state: NovaBlockState, ctx: Context<DefaultContextIntentions.BlockPlace>): Boolean {
        return mayPlaceOn(pos.below, state) && pos.block.isEmpty
    }
    
    abstract fun getMaxAge() : Int
    
    protected fun getAge(state: NovaBlockState) : Int {
        return stages().indexOf(state)
    }
    
    protected fun getStateForAge(age: Int) : NovaBlockState {
        return stages()[age]
    }
    
    protected fun isMaxAge(state: NovaBlockState) : Boolean {
        return getAge(state) == getMaxAge()
    }
    
    protected fun growCrop(pos: BlockPos, state: NovaBlockState) {
        var newAge = getAge(state) + getBonemealAgeIncrease()
        val maxAge = getMaxAge()
        if (newAge > maxAge) {
            newAge = maxAge
        }
        setBlock(pos, getStateForAge(newAge))
    }
    
    protected fun getBonemealAgeIncrease() : Int {
        val maxAge = getMaxAge()
        val min = (maxAge * (2.0F/7)).toInt()
        val max = (maxAge * (5.0F/7)).toInt()
        return Random.nextInt(min, max + 1)
    }
    
    protected fun getGrowthSpeed(blockPos: BlockPos): Float {
        val cropBlock= blockPos.block
        var growthSpeed = 1.0f
        val belowBlockPos = blockPos.below
        
        // Loop through adjacent blocks (in a 3x3 area around the crop)
        for (xOffset in -1..1) {
            for (zOffset in -1..1) {
                var individualSpeed = 0.0f
                val adjacentBlockPos = belowBlockPos.add(xOffset, 0, zOffset)
                val adjacentBlock = adjacentBlockPos.block
                
                // Check if block is farmland
                if (adjacentBlock.type == Material.FARMLAND) {
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
        
        val hasHorizontalAdjacent = (westBlock.type == cropBlock.type || eastBlock.type == cropBlock.type)
        val hasVerticalAdjacent = (northBlock.type == cropBlock.type || southBlock.type == cropBlock.type)
        
        // If both horizontal and vertical adjacent crops exist, reduce growth speed
        if (hasHorizontalAdjacent && hasVerticalAdjacent) {
            growthSpeed /= 2.0f
        } else {
            // Check for diagonal adjacent crops
            val hasDiagonalAdjacent = (northBlock.getRelative(BlockFace.WEST).type == cropBlock.type ||
                northBlock.getRelative(BlockFace.EAST).type == cropBlock.type ||
                southBlock.getRelative(BlockFace.WEST).type == cropBlock.type ||
                southBlock.getRelative(BlockFace.EAST).type == cropBlock.type)
            
            if (hasDiagonalAdjacent) {
                growthSpeed /= 2.0f
            }
        }
        
        return growthSpeed
    }
    
    protected fun canSurvive(state: NovaBlockState, pos: BlockPos): Boolean {
        val blockBelow = pos.below
        return hasSufficientLight(pos) && mayPlaceOn(blockBelow, state)
    }
    
    protected fun hasSufficientLight(pos: BlockPos): Boolean {
        return (getRawBrightness(pos) >= 8)
    }
    
    protected fun isValidBoneMealTarget(pos: BlockPos, state: NovaBlockState): Boolean {
        return !isMaxAge(state)
    }
    
    protected fun isBoneMealSuccess(pos: BlockPos, state: NovaBlockState): Boolean {
        return true
    }
    
    protected fun performBoneMeal(pos: BlockPos, state: NovaBlockState) {
        growCrop(pos, state)
        // Spawn the "happy villager" particles (green particles like bone meal effect)
        pos.world.spawnParticle(
            Particle.HAPPY_VILLAGER, // Green particle
            pos.location,                // Where to spawn the particle
            30,                      // Number of particles
            0.5, 1.0, 0.5,           // X, Y, Z offsets (to spread particles around)
            0.1                      // Speed of the particles (can be 0 for slow-moving particles)
        )
        pos.world.playSound(pos.location, Sound.ITEM_BONE_MEAL_USE, 1.0f, 1.0f)
    }
    
    private fun getRawBrightness(pos: BlockPos): Byte {
        return if (pos.block.lightLevel < pos.block.lightFromSky) {
            pos.block.lightFromSky
        } else {
            pos.block.lightLevel
        }
    }
    
    protected open fun mayPlaceOn(pos: BlockPos, state: NovaBlockState): Boolean {
        return pos.block.type == Material.FARMLAND
    }
    
    /**
     * Simulates the binomial distribution for seed drops.
     * @param fortuneLevel The level of the Fortune enchantment.
     * @return The number of seeds to drop.
     */
    private fun simulateBinomialDrops(fortuneLevel: Int): Int {
        val trials = 3 + fortuneLevel // n trials: 3 base trials + 1 for each level of Fortune
        var seedDrops = 0
        
        // Perform binomial trials
        for (i in 0 until trials) {
            if (Random.nextDouble(1.0) < dropProbability) {
                seedDrops++
            }
        }
        
        return seedDrops.coerceAtLeast(1) // Ensure at least 1 seed drops
    }
    
    private fun setBlock(pos: BlockPos, state: NovaBlockState) {
        val context = Context.intention(DefaultContextIntentions.BlockPlace)
            .param(DefaultContextParamTypes.BLOCK_POS, pos)
            .param(DefaultContextParamTypes.BLOCK_STATE_NOVA, state)
            .param(DefaultContextParamTypes.BLOCK_PLACE_EFFECTS, false)
            .build()
        
        BlockUtils.placeBlock(context)
    }
    
    private fun breakBlock(position: BlockPos){
        val context = Context.intention(DefaultContextIntentions.BlockBreak)
            .param(DefaultContextParamTypes.BLOCK_POS, position)
            .param(DefaultContextParamTypes.BLOCK_BREAK_EFFECTS, true)
            .build()
        
        BlockUtils.breakBlockNaturally(context)
    }
}
