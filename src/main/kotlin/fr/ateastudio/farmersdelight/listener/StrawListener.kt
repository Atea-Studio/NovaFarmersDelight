package fr.ateastudio.farmersdelight.listener

import xyz.xenondevs.nova.world.item.createItemStack

import fr.ateastudio.farmersdelight.block.BlockStateProperties
import fr.ateastudio.farmersdelight.registry.Blocks
import fr.ateastudio.farmersdelight.registry.Items
import fr.ateastudio.farmersdelight.util.isKnife
import org.bukkit.block.data.Ageable
import org.bukkit.block.BlockType
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import xyz.xenondevs.nova.initialize.Init
import xyz.xenondevs.nova.initialize.InitStage
import fr.ateastudio.farmersdelight.NovaFarmersDelight
import xyz.xenondevs.nova.world.block.blockType
import xyz.xenondevs.nova.world.block.novaBlockState
import kotlin.random.Random

@Init(stage = InitStage.POST_WORLD)
object StrawListener : Listener {
    
    init {
        NovaFarmersDelight.plugin!!.server.pluginManager.registerEvents(this, NovaFarmersDelight.plugin!!)
    }
    
    @EventHandler
    fun onPlayerBlockBreak(event: BlockBreakEvent) {
        val player = event.player
        val block = event.block
        if (event.isCancelled || !player.inventory.itemInMainHand.isKnife()) return
        
        var chance = 0.0
        when (block.blockType) {
            BlockType.SHORT_GRASS, BlockType.SHORT_DRY_GRASS, BlockType.TALL_GRASS, BlockType.TALL_DRY_GRASS -> {
                chance = 0.2
            }
            BlockType.WHEAT -> {
                val data = block.blockData
                if (data is Ageable) {
                    if (data.age != data.maximumAge) {
                        return // Wheat is not fully grown
                    }
                    chance = 1.0
                }
            }
            else -> {
                chance = when (block.blockType) {
                    Blocks.SANDY_SHRUB.get() -> 0.3
                    Blocks.RICE_CROP.get() -> {
                        val novaBlockState = block.novaBlockState
                        val cropAge = novaBlockState?.get(BlockStateProperties.AGE) ?: return
                        val cropMaxAge = novaBlockState[BlockStateProperties.MAX_AGE] ?: return
                        if (cropAge < cropMaxAge) return
                        1.0
                    }
                    else -> return // if it's not a recognized novaBlock, stop here
                }
            }
        }
        
        if (Random.nextDouble() <= chance) {
            val straw = Items.STRAW.createItemStack()
            block.world.dropItemNaturally(block.location, straw)
        }
        
    }
    
}