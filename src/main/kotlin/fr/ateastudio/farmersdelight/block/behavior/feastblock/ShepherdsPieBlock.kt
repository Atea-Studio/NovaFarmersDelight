package fr.ateastudio.farmersdelight.block.behavior.feastblock

import xyz.xenondevs.nova.world.item.createItemStack

import fr.ateastudio.farmersdelight.block.behavior.FeastBlock
import fr.ateastudio.farmersdelight.registry.Items
import org.bukkit.inventory.ItemStack
import xyz.xenondevs.nova.world.block.NovaBlockState

object ShepherdsPieBlock : FeastBlock(true) {
    
    override fun getServingItem(state: NovaBlockState): ItemStack {
        return try {
            Items.SHEPHERDS_PIE.createItemStack()
        } catch (e : Exception) {
            ItemStack.empty()
        }
    }
    
    override fun getFullBlockItem(): ItemStack {
        return try {
            Items.SHEPHERDS_PIE_BLOCK.createItemStack()
        } catch (e : Exception) {
            ItemStack.empty()
        }
    }
}