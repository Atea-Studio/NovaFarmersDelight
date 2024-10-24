package fr.ateastudio.farmersdelight.block.behavior

import fr.ateastudio.farmersdelight.registry.Blocks
import fr.ateastudio.farmersdelight.registry.Items
import xyz.xenondevs.nova.world.block.state.NovaBlockState
import xyz.xenondevs.nova.world.item.NovaItem

object TomatoCrop : CropBlock() {
    override fun stages(): List<NovaBlockState> {
        try {
            return listOf(
                Blocks.TOMATOES_STAGE0.defaultBlockState,
                Blocks.TOMATOES_STAGE1.defaultBlockState,
                Blocks.TOMATOES_STAGE2.defaultBlockState,
                Blocks.TOMATOES_STAGE3.defaultBlockState)
        }
        catch (e : Exception) {
            return listOf()
        }
    }
    
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
    
    override fun getMaxAge(): Int {
        return 3
    }
    
}
