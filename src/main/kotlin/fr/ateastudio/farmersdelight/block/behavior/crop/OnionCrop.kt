package fr.ateastudio.farmersdelight.block.behavior.crop

import fr.ateastudio.farmersdelight.block.behavior.CropBlock
import fr.ateastudio.farmersdelight.registry.Items
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.inventory.ItemType
import xyz.xenondevs.nova.registry.RegistryEntry
import xyz.xenondevs.nova.world.block.NovaBlockState

object OnionCrop : CropBlock() {
    override fun resultItem(): RegistryEntry.Paper<ItemType>? {
        return try {
            Items.ONION
        } catch (e : Exception) {
            null
        }
    }
    
    override fun seedItem(): RegistryEntry.Paper<ItemType>? {
        return try {
            Items.ONION
        } catch (e : Exception) {
            null
        }
    }
    
    override fun mayPlaceOn(pos: Block, state: NovaBlockState): Boolean {
        return pos.type == Material.FARMLAND && hasSufficientLight(pos)
    }
}
