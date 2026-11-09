package fr.ateastudio.farmersdelight.block.behavior.crop

import fr.ateastudio.farmersdelight.block.behavior.CropBlock
import fr.ateastudio.farmersdelight.registry.Items
import org.bukkit.block.Block
import org.bukkit.block.BlockType
import org.bukkit.inventory.ItemType
import xyz.xenondevs.nova.registry.RegistryEntry
import xyz.xenondevs.nova.world.block.NovaBlockState
import xyz.xenondevs.nova.world.block.blockType

object OnionCrop : CropBlock() {
    override fun resultItem(): RegistryEntry.Paper<ItemType>? {
        return try {
            Items.ONION
        } catch (_ : Exception) {
            null
        }
    }
    
    override fun seedItem(): RegistryEntry.Paper<ItemType>? {
        return try {
            Items.ONION
        } catch (_ : Exception) {
            null
        }
    }
    
    override fun mayPlaceOn(pos: Block, state: NovaBlockState): Boolean {
        return pos.blockType == BlockType.FARMLAND && hasSufficientLight(pos)
    }
}
