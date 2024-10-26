package fr.ateastudio.farmersdelight.block.behavior

import fr.ateastudio.farmersdelight.registry.Items
import org.bukkit.GameRule
import org.bukkit.Material
import org.bukkit.block.BlockFace
import org.bukkit.block.data.Levelled
import xyz.xenondevs.nova.world.BlockPos
import xyz.xenondevs.nova.world.block.state.NovaBlockState
import xyz.xenondevs.nova.world.item.NovaItem
import kotlin.random.Random

object TomatoCrop : BerryBlock() {
    override fun resultItem(): NovaItem? {
        return try {
            Items.TOMATO
        } catch (e : Exception) {
            null
        }
    }
    
    override fun seedItem(): NovaItem? {
        return try {
            Items.TOMATO_SEEDS
        } catch (e : Exception) {
            null
        }
    }
    
    override fun badResultItem(): NovaItem? {
        return try {
            Items.ROTTEN_TOMATO
        } catch (e : Exception) {
            null
        }
    }
    
    
    
    override fun mayPlaceOn(pos: BlockPos, state: NovaBlockState): Boolean {
        return pos.block.type == Material.FARMLAND && hasSufficientLight(pos)
        
    }
    
    override fun handleRandomTick(pos: BlockPos, state: NovaBlockState) {
        val lightLevel = getRawBrightness(pos)
        if (lightLevel >= 9) {
            val age = getAge(state)
            val maxAge = getMaxAge(state)
            if (age < maxAge) {
                val growSpeed = getGrowthSpeed(pos)
                val tickSpeedMultiplier = (pos.world.getGameRuleValue(GameRule.RANDOM_TICK_SPEED) ?: 3) / 3
                for (i in 1..tickSpeedMultiplier) {
                    if (Random.nextInt(((25.0F / growSpeed) + 1).toInt()) == 0) {
                        growCrop(pos, state)
                    }
                }
            }
        }
        if (!canSurvive(state, pos)) {
            breakBlock(pos)
        }
    }
    
    private fun getGrowthSpeed(blockPos: BlockPos): Float {
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
    
}
