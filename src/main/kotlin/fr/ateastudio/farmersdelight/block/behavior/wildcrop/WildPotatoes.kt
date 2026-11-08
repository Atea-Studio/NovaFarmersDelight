package fr.ateastudio.farmersdelight.block.behavior.wildcrop

import fr.ateastudio.farmersdelight.block.behavior.WildcropBlock
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class WildPotatoes : WildcropBlock() {
    override fun seedItem(): ItemStack? {
        return try {
            ItemStack(Material.POTATO)
        } catch (e : Exception) {
            null
        }
    }
}