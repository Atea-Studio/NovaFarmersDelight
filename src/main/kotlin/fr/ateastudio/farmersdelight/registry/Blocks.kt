package fr.ateastudio.farmersdelight.registry

import fr.ateastudio.farmersdelight.NovaFarmersDelight
import fr.ateastudio.farmersdelight.block.BlockStateProperties
import fr.ateastudio.farmersdelight.block.CookingPotSupport
import fr.ateastudio.farmersdelight.block.ScopedBlockStateProperties
import fr.ateastudio.farmersdelight.block.behavior.Ageable
import fr.ateastudio.farmersdelight.block.behavior.CabbageCrop
import fr.ateastudio.farmersdelight.block.behavior.CookingPotBehavior
import fr.ateastudio.farmersdelight.block.behavior.MuddyFarmland
import fr.ateastudio.farmersdelight.block.behavior.OnionCrop
import fr.ateastudio.farmersdelight.block.behavior.RiceCrop
import fr.ateastudio.farmersdelight.block.behavior.TomatoCrop
import fr.ateastudio.farmersdelight.tileentity.CookingPot
import org.bukkit.Material
import xyz.xenondevs.nova.addon.registry.BlockRegistry
import xyz.xenondevs.nova.initialize.Init
import xyz.xenondevs.nova.initialize.InitStage
import xyz.xenondevs.nova.resources.layout.block.BackingStateCategory
import xyz.xenondevs.nova.world.block.NovaBlock
import xyz.xenondevs.nova.world.block.NovaBlockBuilder
import xyz.xenondevs.nova.world.block.behavior.BlockBehaviorHolder
import xyz.xenondevs.nova.world.block.behavior.BlockDrops
import xyz.xenondevs.nova.world.block.behavior.BlockSounds
import xyz.xenondevs.nova.world.block.behavior.Breakable
import xyz.xenondevs.nova.world.block.behavior.TileEntityDrops
import xyz.xenondevs.nova.world.block.behavior.TileEntityInteractive
import xyz.xenondevs.nova.world.block.behavior.TileEntityLimited
import xyz.xenondevs.nova.world.block.sound.SoundGroup
import xyz.xenondevs.nova.world.block.state.property.DefaultScopedBlockStateProperties.FACING_HORIZONTAL
import xyz.xenondevs.nova.world.item.tool.VanillaToolCategories
import xyz.xenondevs.nova.world.item.tool.VanillaToolTiers

@Init(stage = InitStage.PRE_PACK)
object Blocks : BlockRegistry by NovaFarmersDelight.registry {
    private val CROP = Breakable(0.0, VanillaToolCategories.AXE, VanillaToolTiers.WOOD, false, Material.TALL_GRASS)
    private val MUD = Breakable(0.5, VanillaToolCategories.SHOVEL, VanillaToolTiers.WOOD, false, Material.MUD)
    private val BAG = Breakable(0.8, VanillaToolCategories.SHEARS, VanillaToolTiers.WOOD, false, Material.WHITE_WOOL)
    private val BALE = Breakable(0.8, VanillaToolCategories.HOE, VanillaToolTiers.WOOD, false, Material.HAY_BLOCK)
    private val CRATE = Breakable(2.0, VanillaToolCategories.AXE, VanillaToolTiers.WOOD, false, Material.OAK_PLANKS)
    private val LIGHT_METAL = Breakable(0.5, VanillaToolCategories.PICKAXE, VanillaToolTiers.WOOD, false, Material.IRON_BLOCK)
    
    val BEETROOT_CRATE = nonInteractiveBlock("beetroot_crate") { behaviors(CRATE, BlockDrops, BlockSounds(SoundGroup.WOOD)) }
    val CARROT_CRATE = nonInteractiveBlock("carrot_crate") { behaviors(CRATE, BlockDrops, BlockSounds(SoundGroup.WOOD)) }
    val POTATO_CRATE = nonInteractiveBlock("potato_crate") { behaviors(CRATE, BlockDrops, BlockSounds(SoundGroup.WOOD)) }
    val ONION_CRATE = nonInteractiveBlock("onion_crate") { behaviors(CRATE, BlockDrops, BlockSounds(SoundGroup.WOOD)) }
    val TOMATO_CRATE = nonInteractiveBlock("tomato_crate") { behaviors(CRATE, BlockDrops, BlockSounds(SoundGroup.WOOD)) }
    val RICE_BAG = nonInteractiveBlock("rice_bag") { behaviors(BAG, BlockDrops, BlockSounds(SoundGroup.WOOL)) }
    val RICE_BALE = nonInteractiveBlock("rice_bale") { behaviors(BALE, BlockDrops, BlockSounds(SoundGroup.GRASS)) }
    val CABBAGE_CRATE = nonInteractiveBlock("cabbage_crate") { behaviors(CRATE, BlockDrops, BlockSounds(SoundGroup.WOOD)) }
    
    val TOMATOES_CROP = cropBlock("tomatoes", TomatoCrop, 7,3)
    val RICE_CROP = cropBlock("rice", RiceCrop, 7,3)
    val CABBAGES_CROP = cropBlock("cabbages", CabbageCrop, 7)
    val ONION_CROP = cropBlock("onions", OnionCrop, 3)
    
    val MUDDY_FARMLAND = block("muddy_farmland") {
        behaviors(MUD, BlockSounds(SoundGroup.MUD), MuddyFarmland)
        models {
            stateBacked(BackingStateCategory.LEAVES, BackingStateCategory.NOTE_BLOCK)
        }
    }
    
    val COOKING_POT = tileEntity("cooking_pot", ::CookingPot) {
        behaviors(
            TileEntityLimited,
            TileEntityDrops,
            TileEntityInteractive,
            LIGHT_METAL,
            BlockSounds(SoundGroup.LANTERN),
            CookingPotBehavior
        )
        tickrate(1)
        stateProperties(FACING_HORIZONTAL, ScopedBlockStateProperties.SUPPORT, ScopedBlockStateProperties.HEATED)
        models {
            selectModel {
                val support = getPropertyValueOrThrow(BlockStateProperties.SUPPORT)
                when (support) {
                    CookingPotSupport.HANDLE -> getModel("block/cooking_pot_handle").rotated()
                    CookingPotSupport.TRAY -> getModel("block/cooking_pot_tray").rotated()
                    else -> getModel("block/cooking_pot").rotated()
                }
            }
        }
    }
    
    private fun nonInteractiveBlock(
        name: String,
        block: NovaBlockBuilder.() -> Unit
    ): NovaBlock = block(name) {
        block()
        models {
            stateBacked(BackingStateCategory.NOTE_BLOCK, BackingStateCategory.MUSHROOM_BLOCK)
        }
    }
    
    private fun cropBlock(
        name: String,
        cropBehavior: BlockBehaviorHolder,
        maxAge: Int,
        buddingAge: Int = -1,
        block: NovaBlockBuilder.() -> Unit ={}
    ): NovaBlock = block("${name}_crop") {
        block()
        behaviors(CROP, Ageable(maxAge, buddingAge), cropBehavior, BlockSounds(SoundGroup.CROP))
        stateProperties(ScopedBlockStateProperties.AGE, ScopedBlockStateProperties.BUDDING_AGE, ScopedBlockStateProperties.MAX_AGE)
        models {
            stateBacked(BackingStateCategory.TRIPWIRE_UNATTACHED, BackingStateCategory.TRIPWIRE_ATTACHED)
            selectModel {
                val age = getPropertyValueOrThrow(BlockStateProperties.AGE)
                var id = if (age > maxAge) maxAge else age
                if (id > buddingAge) {
                    id -= (buddingAge + 1)
                    getModel("block/${name}_stage$id")
                }
                else {
                    getModel("block/budding_${name}_stage$id")
                }
            }
        }
    }
    
}