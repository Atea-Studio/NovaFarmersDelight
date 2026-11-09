package fr.ateastudio.farmersdelight.block.behavior.pie

import xyz.xenondevs.nova.world.item.createItemStack

import fr.ateastudio.farmersdelight.block.behavior.PieBlock
import fr.ateastudio.farmersdelight.registry.Items
import org.bukkit.inventory.ItemStack

object ApplePie : PieBlock() {
    override fun getPieSliceItem(): ItemStack {
        return try {
            Items.APPLE_PIE_SLICE.createItemStack()
        } catch (e : Exception) {
            ItemStack.empty()
        }
    }
}