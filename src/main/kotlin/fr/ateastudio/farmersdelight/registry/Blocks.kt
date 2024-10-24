package fr.ateastudio.farmersdelight.registry

import fr.ateastudio.farmersdelight.NovaFarmersDelight
import fr.ateastudio.farmersdelight.block.behavior.TomatoCrop
import org.bukkit.Material
import xyz.xenondevs.nova.addon.registry.BlockRegistry
import xyz.xenondevs.nova.initialize.Init
import xyz.xenondevs.nova.initialize.InitStage
import xyz.xenondevs.nova.resources.layout.block.BackingStateCategory
import xyz.xenondevs.nova.world.block.NovaBlock
import xyz.xenondevs.nova.world.block.NovaBlockBuilder
import xyz.xenondevs.nova.world.block.behavior.BlockSounds
import xyz.xenondevs.nova.world.block.behavior.Breakable
import xyz.xenondevs.nova.world.block.sound.SoundGroup
import xyz.xenondevs.nova.world.item.tool.VanillaToolCategories
import xyz.xenondevs.nova.world.item.tool.VanillaToolTiers

@Init(stage = InitStage.PRE_PACK)
object Blocks : BlockRegistry by NovaFarmersDelight.registry {
    private val CROP = Breakable(
        hardness = 0.0,
        toolCategory = VanillaToolCategories.AXE,
        toolTier = VanillaToolTiers.WOOD,
        requiresToolForDrops = false,
        breakParticles = Material.TALL_GRASS
    )
    
    val TOMATOES_STAGE0 = nonCollisionBlock("tomatoes_stage0"){behaviors(TomatoCrop, CROP, BlockSounds(SoundGroup.CROP))}
    val TOMATOES_STAGE1 = nonCollisionBlock("tomatoes_stage1"){behaviors(TomatoCrop, CROP, BlockSounds(SoundGroup.CROP))}
    val TOMATOES_STAGE2 = nonCollisionBlock("tomatoes_stage2"){behaviors(TomatoCrop, CROP, BlockSounds(SoundGroup.CROP))}
    val TOMATOES_STAGE3 = nonCollisionBlock("tomatoes_stage3"){behaviors(TomatoCrop, CROP, BlockSounds(SoundGroup.CROP))}
    
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