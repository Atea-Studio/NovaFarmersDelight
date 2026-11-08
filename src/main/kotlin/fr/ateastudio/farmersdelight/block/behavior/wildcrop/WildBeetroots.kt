package fr.ateastudio.farmersdelight.block.behavior.wildcrop

import fr.ateastudio.farmersdelight.block.behavior.WildcropBlock
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class WildBeetroots : WildcropBlock() {
    override fun seedItem(): ItemStack? {
        return try {
            ItemStack(Material.BEETROOT_SEEDS)
        } catch (e : Exception) {
            null
        }
    }
}