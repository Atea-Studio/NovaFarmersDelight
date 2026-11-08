package fr.ateastudio.farmersdelight.block.behavior.wildcrop

import fr.ateastudio.farmersdelight.block.behavior.WildcropBlock
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.ItemType

@Suppress("UnstableApiUsage")
class WildPotatoes : WildcropBlock() {
    override fun seedItem(): ItemStack? {
        return try {
            ItemType.POTATO.createItemStack()
        } catch (_ : Exception) {
            null
        }
    }
}