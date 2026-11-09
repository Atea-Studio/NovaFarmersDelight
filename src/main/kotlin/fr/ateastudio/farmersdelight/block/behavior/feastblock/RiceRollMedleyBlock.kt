package fr.ateastudio.farmersdelight.block.behavior.feastblock

import xyz.xenondevs.nova.world.item.createItemStack

import fr.ateastudio.farmersdelight.block.BlockStateProperties
import fr.ateastudio.farmersdelight.block.behavior.FeastBlock
import fr.ateastudio.farmersdelight.registry.Items
import org.bukkit.inventory.ItemStack
import xyz.xenondevs.nova.world.block.NovaBlockState

object RiceRollMedleyBlock : FeastBlock(true) {
    
    private val riceRollServings = lazy{
        listOf(
            Items.COD_ROLL,
            Items.COD_ROLL,
            Items.SALMON_ROLL,
            Items.SALMON_ROLL,
            Items.SALMON_ROLL,
            Items.KELP_ROLL_SLICE,
            Items.KELP_ROLL_SLICE,
            Items.KELP_ROLL_SLICE,
        )
    }
    
    override val maxServings: Int
        get() = 8
    
    override fun getServingItem(state: NovaBlockState): ItemStack {
        val servings = state[BlockStateProperties.SERVINGS] ?: 0
        val map = riceRollServings.value
        return try {
            map[servings - 1].createItemStack()
        } catch (_ : Exception) {
            ItemStack.empty()
        }
    }
    
    override fun getFullBlockItem(): ItemStack {
        return try {
            Items.RICE_ROLL_MEDLEY_BLOCK.createItemStack()
        } catch (e : Exception) {
            ItemStack.empty()
        }
    }
}