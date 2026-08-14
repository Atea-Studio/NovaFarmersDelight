package fr.ateastudio.farmersdelight.block.behavior.feastblock

import fr.ateastudio.farmersdelight.block.BlockStateProperties
import fr.ateastudio.farmersdelight.block.behavior.FeastBlock
import fr.ateastudio.farmersdelight.registry.Items
import org.bukkit.inventory.ItemStack
import xyz.xenondevs.nova.world.block.state.NovaBlockState

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
}