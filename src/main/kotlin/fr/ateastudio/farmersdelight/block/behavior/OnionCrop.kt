package fr.ateastudio.farmersdelight.block.behavior

import fr.ateastudio.farmersdelight.registry.Items
import org.bukkit.Material
import xyz.xenondevs.nova.world.BlockPos
import xyz.xenondevs.nova.world.block.state.NovaBlockState
import xyz.xenondevs.nova.world.item.NovaItem

object OnionCrop : CropBlock() {
    override fun resultItem(): NovaItem? {
        return try {
            Items.ONION
        } catch (e : Exception) {
            null
        }
    }
    
    override fun seedItem(): NovaItem? {
        return try {
            Items.ONION
        } catch (e : Exception) {
            null
        }
    }
    
    override fun mayPlaceOn(pos: BlockPos, state: NovaBlockState): Boolean {
        return pos.block.type == Material.FARMLAND && hasSufficientLight(pos)
    }
}
