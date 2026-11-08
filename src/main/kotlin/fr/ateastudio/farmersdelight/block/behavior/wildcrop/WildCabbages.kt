package fr.ateastudio.farmersdelight.block.behavior.wildcrop

import xyz.xenondevs.nova.world.item.createItemStack

import fr.ateastudio.farmersdelight.block.behavior.WildcropBlock
import fr.ateastudio.farmersdelight.registry.Items
import org.bukkit.inventory.ItemStack

class WildCabbages : WildcropBlock() {
    override fun seedItem(): ItemStack? {
        return try {
            Items.CABBAGE_SEEDS.createItemStack()
        } catch (e : Exception) {
            null
        }
    }
}