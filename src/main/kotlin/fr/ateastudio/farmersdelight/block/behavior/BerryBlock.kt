package fr.ateastudio.farmersdelight.block.behavior

import xyz.xenondevs.nova.world.item.createItemStack

import fr.ateastudio.farmersdelight.block.BlockStateProperties
import org.bukkit.GameMode
import org.bukkit.SoundCategory
import org.bukkit.block.BlockType
import org.bukkit.block.Block
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.ItemType
import xyz.xenondevs.nova.context.Context
import xyz.xenondevs.nova.context.intention.BlockInteract
import xyz.xenondevs.nova.world.InteractionResult
import xyz.xenondevs.nova.world.block.NovaBlockState
import xyz.xenondevs.nova.world.block.blockType
import xyz.xenondevs.nova.world.item.itemType
import kotlin.random.Random


@Suppress("UnstableApiUsage")
abstract class BerryBlock : CropBlock() {
    
    protected open val pickUpSound = "minecraft:block.sweet_berry_bush.pick_berries"
    
    override fun useItemOn(block: Block, state: NovaBlockState, ctx: Context<BlockInteract>): InteractionResult {
        val player = ctx[BlockInteract.SOURCE_PLAYER] ?: return InteractionResult.Pass
        val hand = ctx[BlockInteract.HELD_HAND]
        val itemStack = if (hand == EquipmentSlot.HAND) player.inventory.itemInMainHand else player.inventory.itemInOffHand
        
        
        if (isMaxAge(state)){
            doDrop(block)
            block.blockData = getStateForAge(state, getBuddingAge(state) + 1)
        }
        else if (itemStack.itemType == ItemType.BONE_MEAL && isValidBoneMealTarget(state)) {
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
        val age = getAge(state)
        val maxAge = getMaxAge(state)
        val lightLevel = getRawBrightness(block.getRelative(0, 1, 0))
        if (age < maxAge && Random.nextInt(5) == 0 && lightLevel >= 9) {
            growCrop(block, state)
        }
        if (!canSurvive(state, block)) {
            breakBlock(block)
        }
    }
    
    override fun performBoneMeal(pos: Block, state: NovaBlockState) {
        val amount = 1
        if (!isMaxAge(state)) {
            val age = getAge(state) + amount
            val maxAge = getMaxAge(state)
            if (age > maxAge) {
                val newAge = getBuddingAge(state) + (age - maxAge) + 1
                pos.blockData = getStateForAge(state, newAge)
                doDrop(pos)
            }
            else
                pos.blockData = getStateForAge(state, age)
        }
        showBoneMealParticle(pos)
    }
    
    override fun mayPlaceOn(pos: Block, state: NovaBlockState): Boolean {
        return pos.blockType == BlockType.GRASS_BLOCK ||
            pos.blockType == BlockType.DIRT ||
            pos.blockType == BlockType.PODZOL ||
            pos.blockType == BlockType.COARSE_DIRT ||
            pos.blockType == BlockType.FARMLAND ||
            pos.blockType == BlockType.MOSS_BLOCK
            
    }
    
    private fun doDrop(pos: Block) {
        val result = mutableListOf<ItemStack>()
        val resultItem = resultItem()
        val badResultItem = badResultItem()
        if (resultItem != null) {
            if (badResultItem != null && Random.nextFloat() < badCropResultProbability) {
                result.add(badResultItem.createItemStack())
            }
            else {
                // Determine the amount of drops, with possible modification from the Fortune enchantment
                val quantity = 1 + Random.nextInt(2)
                result.add(resultItem.createItemStack(quantity))
            }
        }
        pos.world.playSound(pos.location, pickUpSound, SoundCategory.BLOCKS, 1.0f, 1.0f)
        result.forEach { pos.world.dropItemNaturally(pos.location, it) }
    }
    
    private fun getBuddingAge(state: NovaBlockState): Int {
        return state.getOrThrow(BlockStateProperties.BUDDING_AGE)
    }
}
