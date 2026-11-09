package fr.ateastudio.farmersdelight.block.behavior.wildcrop

import fr.ateastudio.farmersdelight.block.behavior.WildcropBlock
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.ItemType

@Suppress("UnstableApiUsage")
class WildBeetroots : WildcropBlock() {
    override fun seedItem(): ItemStack? {
        return try {
            ItemType.BEETROOT_SEEDS.createItemStack()
        } catch (_ : Exception) {
            null
        }
    }
}