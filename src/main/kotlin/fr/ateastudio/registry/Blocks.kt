package fr.ateastudio.registry

import fr.ateastudio.farmersdelight.NovaFarmersDelight
import xyz.xenondevs.nova.addon.registry.BlockRegistry
import xyz.xenondevs.nova.initialize.Init
import xyz.xenondevs.nova.initialize.InitStage
import xyz.xenondevs.nova.resources.layout.block.BackingStateCategory
import xyz.xenondevs.nova.world.block.NovaBlock
import xyz.xenondevs.nova.world.block.NovaBlockBuilder

@Suppress("unused")
@Init(stage = InitStage.PRE_PACK)
object Blocks : BlockRegistry by NovaFarmersDelight.registry {
    
    private fun nonInteractiveBlock(
        name: String,
        block: NovaBlockBuilder.() -> Unit
    ): NovaBlock = block(name) {
        block()
        models {
            stateBacked(BackingStateCategory.MUSHROOM_BLOCK, BackingStateCategory.NOTE_BLOCK)
        }
    }
    
    private fun nonCollisionBlock(
        name: String,
        block: NovaBlockBuilder.() -> Unit
    ): NovaBlock = block(name) {
        block()
        models {
            stateBacked(BackingStateCategory.TRIPWIRE_UNATTACHED, BackingStateCategory.TRIPWIRE_ATTACHED)
        }
    }
}