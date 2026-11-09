package fr.ateastudio.farmersdelight.block.behavior.feastblock

import xyz.xenondevs.nova.world.item.createItemStack

import fr.ateastudio.farmersdelight.block.behavior.FeastBlock
import fr.ateastudio.farmersdelight.registry.Items
import org.bukkit.inventory.ItemStack
import xyz.xenondevs.nova.world.block.NovaBlockState

object RoastChickenBlock : FeastBlock(true) {
    
    override fun getServingItem(state: NovaBlockState): ItemStack {
        return try {
            Items.ROAST_CHICKEN.createItemStack()
        } catch (e : Exception) {
            ItemStack.empty()
        }
    }
    
    override fun getFullBlockItem(): ItemStack {
        return try {
            Items.ROAST_CHICKEN_BLOCK.createItemStack()
        } catch (e : Exception) {
            ItemStack.empty()
        }
    }
}