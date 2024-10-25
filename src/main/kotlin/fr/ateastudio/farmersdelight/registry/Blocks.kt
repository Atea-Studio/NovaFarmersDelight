package fr.ateastudio.farmersdelight.registry

import fr.ateastudio.farmersdelight.NovaFarmersDelight
import fr.ateastudio.farmersdelight.block.BlockStateProperties
import fr.ateastudio.farmersdelight.block.ScopedBlockStateProperties
import fr.ateastudio.farmersdelight.block.behavior.Ageable
import fr.ateastudio.farmersdelight.block.behavior.TomatoCrop
import org.bukkit.Material
import xyz.xenondevs.nova.addon.registry.BlockRegistry
import xyz.xenondevs.nova.initialize.Init
import xyz.xenondevs.nova.initialize.InitStage
import xyz.xenondevs.nova.resources.layout.block.BackingStateCategory
import xyz.xenondevs.nova.world.block.NovaBlock
import xyz.xenondevs.nova.world.block.NovaBlockBuilder
import xyz.xenondevs.nova.world.block.behavior.BlockBehaviorHolder
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
    
    val TOMATOES_CROP = cropBlock("tomatoes", 3, TomatoCrop)
    
    private fun cropBlock(
        name: String,
        maxAge: Int,
        cropBehavior: BlockBehaviorHolder,
        block: NovaBlockBuilder.() -> Unit ={}
    ): NovaBlock = block("${name}_crop") {
        block()
        behaviors(CROP, Ageable(maxAge), cropBehavior, BlockSounds(SoundGroup.CROP))
        stateProperties(ScopedBlockStateProperties.AGE, ScopedBlockStateProperties.MAX_AGE)
        models {
            stateBacked(BackingStateCategory.TRIPWIRE_UNATTACHED, BackingStateCategory.TRIPWIRE_ATTACHED)
            selectModel {
                val age = getPropertyValueOrThrow(BlockStateProperties.AGE)
                val id = if (age > maxAge) maxAge else age
                getModel("block/${name}_stage$id")
            }
        }
    }
}