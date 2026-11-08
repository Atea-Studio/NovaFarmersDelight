package fr.ateastudio.farmersdelight.block.behavior.wildcrop

import fr.ateastudio.farmersdelight.block.behavior.WildcropBlock
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class WildCarrots : WildcropBlock() {
    override fun seedItem(): ItemStack? {
        return try {
            ItemStack(Material.CARROT)
        } catch (e : Exception) {
            null
        }
    }
}