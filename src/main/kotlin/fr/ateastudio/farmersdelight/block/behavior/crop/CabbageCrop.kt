package fr.ateastudio.farmersdelight.block.behavior.crop

import fr.ateastudio.farmersdelight.block.behavior.CropBlock
import fr.ateastudio.farmersdelight.registry.Items
import org.bukkit.inventory.ItemType
import xyz.xenondevs.nova.registry.RegistryEntry

object CabbageCrop : CropBlock() {
    override fun resultItem(): RegistryEntry.Paper<ItemType>? {
        return try {
            Items.CABBAGE
        } catch (e : Exception) {
            null
        }
    }
    
    override fun seedItem(): RegistryEntry.Paper<ItemType>? {
        return try {
            Items.CABBAGE_SEEDS
        } catch (e : Exception) {
            null
        }
    }
    
}
