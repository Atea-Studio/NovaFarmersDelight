package fr.ateastudio.farmersdelight.block.behavior

import fr.ateastudio.farmersdelight.block.BlockStateProperties
import org.bukkit.GameMode
import org.bukkit.Material
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


abstract class BerryBlock : CropBlock() {
    
    override fun handleInteract(pos: BlockPos, state: NovaBlockState, ctx: Context<BlockInteract>): Boolean {
        val player = ctx[DefaultContextParamTypes.SOURCE_PLAYER]
        var itemStack = ctx[DefaultContextParamTypes.INTERACTION_ITEM_STACK]
        
        if (player != null) {
            if (itemStack != null && itemStack.type == Material.BONE_MEAL && isValidBoneMealTarget(state)) {
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
            else if (isMaxAge(state)){
                doDrop(pos)
                updateBlockState(pos, getStateForAge(state, getBuddingAge(state) + 1))
            }
        }
        return false
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
    
    private fun doDrop(pos: BlockPos) {
        val result = mutableListOf<ItemStack>()
        val resultItem = resultItem()
        if (resultItem is NovaItem) {
            // Determine the amount of drops, with possible modification from the Fortune enchantment
            val quantity = simulateBinomialDrops(0)
            result.add(resultItem.createItemStack(quantity))
        }
        pos.location.dropItems(result)
    }
    
    private fun getBuddingAge(state: NovaBlockState): Int {
        return state.getOrThrow(BlockStateProperties.BUDDING_AGE)
    }
}
