package fr.ateastudio.farmersdelight.block.behavior

import fr.ateastudio.farmersdelight.registry.Items
import xyz.xenondevs.nova.world.item.NovaItem

object CabbageCrop : CropBlock() {
    override fun resultItem(): NovaItem? {
        return try {
            Items.CABBAGE
        } catch (e : Exception) {
            null
        }
    }
    
    override fun seedItem(): NovaItem? {
        return try {
            Items.CABBAGE_SEEDS
        } catch (e : Exception) {
            null
        }
    }
    
}
