package fr.ateastudio.farmersdelight.block.behavior

import fr.ateastudio.farmersdelight.registry.Items
import xyz.xenondevs.nova.world.item.NovaItem

object TomatoCrop : BerryBlock() {
    override fun resultItem(): NovaItem? {
        return try {
            Items.TOMATO
        } catch (e : Exception) {
            null
        }
    }
    
    override fun seedItem(): NovaItem? {
        return try {
            Items.TOMATO_SEEDS
        } catch (e : Exception) {
            null
        }
    }
    
}
