package fr.ateastudio.farmersdelight.block.behavior.wildcrop

import fr.ateastudio.farmersdelight.block.behavior.WildcropBlock
import fr.ateastudio.farmersdelight.registry.Items
import org.bukkit.inventory.ItemStack

class WildRice : WildcropBlock() {
    override fun seedItem(): ItemStack? {
        return try {
            Items.RICE.createItemStack()
        } catch (e : Exception) {
            null
        }
    }
}