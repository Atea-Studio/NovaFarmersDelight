package fr.ateastudio.farmersdelight.block.behavior

import fr.ateastudio.farmersdelight.block.BlockStateProperties
import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.SoundCategory
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.ItemStack
import xyz.xenondevs.nova.context.Context
import xyz.xenondevs.nova.context.intention.BlockInteract
import xyz.xenondevs.nova.util.BlockUtils.updateBlockState
import xyz.xenondevs.nova.util.dropItems
import xyz.xenondevs.nova.world.BlockPos
import xyz.xenondevs.nova.world.InteractionResult
import xyz.xenondevs.nova.world.block.state.NovaBlockState
import xyz.xenondevs.nova.world.item.NovaItem
import kotlin.random.Random


abstract class BerryBlock : CropBlock() {
    
    protected open val pickUpSound = "minecraft:block.sweet_berry_bush.pick_berries"
    
    override fun use(pos: BlockPos, state: NovaBlockState, ctx: Context<BlockInteract>): InteractionResult {
        val player = ctx[BlockInteract.SOURCE_PLAYER] ?: return InteractionResult.Pass
        
        val slot = ctx[BlockInteract.HELD_HAND]
        
        val itemStack = when (slot) {
            EquipmentSlot.OFF_HAND -> player.inventory.itemInOffHand
            else -> player.inventory.itemInMainHand
        }
        
        if (isMaxAge(state)){
            doDrop(pos)
            updateBlockState(pos, getStateForAge(state, getBuddingAge(state) + 1))
        }
        else if (itemStack.type == Material.BONE_MEAL && isValidBoneMealTarget(state)) {
            if (player.gameMode != GameMode.CREATIVE) {
                itemStack.subtract()
            }
            performBoneMeal(pos, state)
            return InteractionResult.Success()
        }
        return InteractionResult.Fail
    }
    
    override fun ticksRandomly(state: NovaBlockState): Boolean {
        val shouldTick = !isMaxAge(state)
        return shouldTick
    }
    
    override fun handleRandomTick(pos: BlockPos, state: NovaBlockState) {
        val age = getAge(state)
        val maxAge = getMaxAge(state)
        val lightLevel = getRawBrightness(pos.add(0,1,0))
        if (age < maxAge && Random.nextInt(5) == 0 && lightLevel >= 9) {
            growCrop(pos, state)
        }
        if (!canSurvive(state, pos)) {
            breakBlock(pos)
        }
    }
    
    override fun performBoneMeal(pos: BlockPos, state: NovaBlockState) {
        val amount = 1
        if (!isMaxAge(state)) {
            val age = getAge(state) + amount
            val maxAge = getMaxAge(state)
            if (age > maxAge) {
                val newAge = getBuddingAge(state) + (age - maxAge) + 1
                updateBlockState(pos, getStateForAge(state, newAge))
                doDrop(pos)
            }
            else
                updateBlockState(pos, getStateForAge(state, age))
        }
        showBoneMealParticle(pos)
    }
    
    override fun mayPlaceOn(pos: BlockPos, state: NovaBlockState): Boolean {
        return pos.block.type == Material.GRASS_BLOCK ||
            pos.block.type == Material.DIRT ||
            pos.block.type == Material.PODZOL ||
            pos.block.type == Material.COARSE_DIRT ||
            pos.block.type == Material.FARMLAND ||
            pos.block.type == Material.MOSS_BLOCK
            
    }
    
    private fun doDrop(pos: BlockPos) {
        val result = mutableListOf<ItemStack>()
        val resultItem = resultItem()
        val badResultItem = badResultItem()
        if (resultItem is NovaItem) {
            if (badResultItem is NovaItem && Random.nextFloat() < badCropResultProbability) {
                result.add(badResultItem.createItemStack())
            }
            else {
                // Determine the amount of drops, with possible modification from the Fortune enchantment
                val quantity = 1 + Random.nextInt(2)
                result.add(resultItem.createItemStack(quantity))
            }
        }
        pos.world.playSound(pos.location, pickUpSound, SoundCategory.BLOCKS, 1.0f, 1.0f)
        pos.location.dropItems(result)
    }
    
    private fun getBuddingAge(state: NovaBlockState): Int {
        return state.getOrThrow(BlockStateProperties.BUDDING_AGE)
    }
}
