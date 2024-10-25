package fr.ateastudio.farmersdelight.block.behavior

import fr.ateastudio.farmersdelight.registry.Items
import xyz.xenondevs.nova.world.item.NovaItem

object TomatoCrop : CropBlock() {
    override fun resultItem(): NovaItem? {
        try {
            return Items.TOMATO
        }
        catch (e : Exception) {
            return null
        }
    }
    
    override fun seedItem(): NovaItem? {
        try {
            return Items.TOMATO_SEEDS
        }
        catch (e : Exception) {
            return null
        }
    }
    
}
