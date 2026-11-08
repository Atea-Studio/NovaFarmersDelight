package fr.ateastudio.farmersdelight.block.behavior.feastblock

import xyz.xenondevs.nova.world.item.createItemStack

import fr.ateastudio.farmersdelight.block.behavior.FeastBlock
import fr.ateastudio.farmersdelight.registry.Items
import org.bukkit.inventory.ItemStack
import xyz.xenondevs.nova.world.block.NovaBlockState

object StuffedPumpkinBlock : FeastBlock(false) {
    
    override fun getServingItem(state: NovaBlockState): ItemStack {
        return try {
            Items.STUFFED_PUMPKIN.createItemStack()
        } catch (e : Exception) {
            ItemStack.empty()
        }
    }
    
    override fun getFullBlockItem(): ItemStack {
        return try {
            Items.STUFFED_PUMPKIN_BLOCK.createItemStack()
        } catch (e : Exception) {
            ItemStack.empty()
        }
    }
}