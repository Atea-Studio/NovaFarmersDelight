package fr.ateastudio.farmersdelight.block.behavior

import fr.ateastudio.farmersdelight.block.BlockStateProperties
import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.SoundCategory
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.ItemStack
import xyz.xenondevs.nova.context.Context
import xyz.xenondevs.nova.context.intention.DefaultContextIntentions.BlockInteract
import xyz.xenondevs.nova.context.param.DefaultContextParamTypes
import xyz.xenondevs.nova.util.BlockUtils.updateBlockState
import xyz.xenondevs.nova.util.dropItems
import xyz.xenondevs.nova.world.BlockPos
import xyz.xenondevs.nova.world.block.state.NovaBlockState
import xyz.xenondevs.nova.world.item.NovaItem
import kotlin.random.Random


abstract class BerryBlock : CropBlock() {
    
    protected open val pickUpSound = "minecraft:block.sweet_berry_bush.pick_berries"
    
    override fun handleInteract(pos: BlockPos, state: NovaBlockState, ctx: Context<BlockInteract>): Boolean {
        val player = ctx[DefaultContextParamTypes.SOURCE_PLAYER]
        var itemStack = ctx[DefaultContextParamTypes.INTERACTION_ITEM_STACK]
        
        if (player != null) {
            if (isMaxAge(state)){
                doDrop(pos)
                updateBlockState(pos, getStateForAge(state, getBuddingAge(state) + 1))
            }
            else if (itemStack != null && itemStack.type == Material.BONE_MEAL && isValidBoneMealTarget(state)) {
                if (player.gameMode != GameMode.CREATIVE) {
                    if (itemStack.amount > 0) {
                        if (itemStack.amount > 1) {
                            itemStack.amount -= 1 // Reduce the amount by 1
                        } else {
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
        }
        return false
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
