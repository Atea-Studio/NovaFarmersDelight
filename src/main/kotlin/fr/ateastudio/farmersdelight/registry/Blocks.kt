package fr.ateastudio.farmersdelight.registry

import fr.ateastudio.farmersdelight.NovaFarmersDelight.block
import fr.ateastudio.farmersdelight.NovaFarmersDelight.tileEntity
import fr.ateastudio.farmersdelight.block.BlockStateProperties
import fr.ateastudio.farmersdelight.block.CookingPotSupport
import fr.ateastudio.farmersdelight.block.behavior.Ageable
import fr.ateastudio.farmersdelight.block.behavior.CookingPotBehavior
import fr.ateastudio.farmersdelight.block.behavior.CuttingBoardBehavior
import fr.ateastudio.farmersdelight.block.behavior.MuddyFarmland
import fr.ateastudio.farmersdelight.block.behavior.PairedBlock
import fr.ateastudio.farmersdelight.block.behavior.TatamiMatFoot
import fr.ateastudio.farmersdelight.block.behavior.TatamiMatHead
import fr.ateastudio.farmersdelight.block.behavior.crop.CabbageCrop
import fr.ateastudio.farmersdelight.block.behavior.crop.OnionCrop
import fr.ateastudio.farmersdelight.block.behavior.crop.RiceCrop
import fr.ateastudio.farmersdelight.block.behavior.crop.TomatoCrop
import fr.ateastudio.farmersdelight.block.behavior.feastblock.HoneyGlazedHamBlock
import fr.ateastudio.farmersdelight.block.behavior.feastblock.RiceRollMedleyBlock
import fr.ateastudio.farmersdelight.block.behavior.feastblock.RoastChickenBlock
import fr.ateastudio.farmersdelight.block.behavior.feastblock.ShepherdsPieBlock
import fr.ateastudio.farmersdelight.block.behavior.feastblock.StuffedPumpkinBlock
import fr.ateastudio.farmersdelight.block.behavior.pie.ApplePie
import fr.ateastudio.farmersdelight.block.behavior.pie.ChocolatePie
import fr.ateastudio.farmersdelight.block.behavior.pie.SweetBerryCheesecake
import fr.ateastudio.farmersdelight.block.behavior.SkilletBehavior
import fr.ateastudio.farmersdelight.block.behavior.wildcrop.SandyShrub
import fr.ateastudio.farmersdelight.block.behavior.wildcrop.WildBeetroots
import fr.ateastudio.farmersdelight.block.behavior.wildcrop.WildCabbages
import fr.ateastudio.farmersdelight.block.behavior.wildcrop.WildCarrots
import fr.ateastudio.farmersdelight.block.behavior.wildcrop.WildOnions
import fr.ateastudio.farmersdelight.block.behavior.wildcrop.WildPotatoes
import fr.ateastudio.farmersdelight.block.behavior.wildcrop.WildRice
import fr.ateastudio.farmersdelight.block.behavior.wildcrop.WildTomatoes
import fr.ateastudio.farmersdelight.tileentity.Basket
import fr.ateastudio.farmersdelight.tileentity.Cabinet
import fr.ateastudio.farmersdelight.tileentity.CookingPot
import fr.ateastudio.farmersdelight.tileentity.CuttingBoard
import fr.ateastudio.farmersdelight.tileentity.Skillet
import fr.ateastudio.farmersdelight.tileentity.Stove
import org.bukkit.Bukkit
import org.bukkit.block.BlockFace
import org.bukkit.block.BlockType
import org.bukkit.block.data.BlockData
import xyz.xenondevs.nova.world.block.HitboxCuboid
import xyz.xenondevs.nova.initialize.Init
import xyz.xenondevs.nova.initialize.InitStage
import xyz.xenondevs.nova.registry.NovaBlockBuilder
import xyz.xenondevs.nova.registry.RegistryEntry
import xyz.xenondevs.nova.registry.entries.BlockTypeEntries
import xyz.xenondevs.nova.registry.entries.ItemTypeEntries
import xyz.xenondevs.nova.resources.builder.layout.block.BackingStateCategory
import xyz.xenondevs.nova.resources.builder.layout.block.BlockSelectorScope
import xyz.xenondevs.nova.world.block.behavior.BlockBehaviorHolder
import xyz.xenondevs.nova.world.block.behavior.BlockDrops
import xyz.xenondevs.nova.world.block.behavior.TileEntityDrops
import xyz.xenondevs.nova.world.block.behavior.TileEntityInteractive
import xyz.xenondevs.nova.world.block.sound.SoundGroup
import xyz.xenondevs.nova.world.block.state.property.DefaultBlockStateProperties
import xyz.xenondevs.nova.world.item.tool.VanillaToolCategories
import xyz.xenondevs.nova.world.item.tool.VanillaToolTiers

/** Client-side placeholder for the entity-backed skillet (see [Blocks.SKILLET]). */
private val SKILLET_CLIENT_BLOCK: BlockData by lazy { Bukkit.createBlockData("minecraft:light[level=0]") }

/** Skillet selection shape `(1,0,1)-(15,4,15)`. */
private val SKILLET_HITBOX = HitboxCuboid(1.0 / 16, 0.0, 1.0 / 16, 14.0 / 16, 4.0 / 16)

@Init(stage = InitStage.PRE_PACK)
object Blocks {
    
    private fun NovaBlockBuilder.crop() {
        breakable(0.0, setOf(VanillaToolCategories.AXE), VanillaToolTiers.WOOD, false, ItemTypeEntries.TALL_GRASS, BlockTypeEntries.TALL_GRASS, true)
    }
    private fun NovaBlockBuilder.mud() {
        breakable(0.5, setOf(VanillaToolCategories.SHOVEL), VanillaToolTiers.WOOD, false, ItemTypeEntries.MUD, BlockTypeEntries.MUD, true)
    }
    private fun NovaBlockBuilder.bag() {
        breakable(0.8, setOf(VanillaToolCategories.SHEARS), VanillaToolTiers.WOOD, false, ItemTypeEntries.WHITE_WOOL, BlockTypeEntries.WHITE_WOOL, true)
    }
    private fun NovaBlockBuilder.bale() {
        breakable(0.8, setOf(VanillaToolCategories.HOE), VanillaToolTiers.WOOD, false, ItemTypeEntries.HAY_BLOCK, BlockTypeEntries.HAY_BLOCK, true)
    }
    private fun NovaBlockBuilder.crate() {
        breakable(2.0, setOf(VanillaToolCategories.AXE), VanillaToolTiers.WOOD, false, ItemTypeEntries.OAK_PLANKS, BlockTypeEntries.OAK_PLANKS, true)
    }
    private fun NovaBlockBuilder.lightMetal() {
        breakable(0.5, setOf(VanillaToolCategories.PICKAXE), VanillaToolTiers.WOOD, false, ItemTypeEntries.CAULDRON, BlockTypeEntries.CAULDRON, true)
    }
    private fun NovaBlockBuilder.sprucePlank() {
        breakable(0.5, setOf(VanillaToolCategories.AXE), VanillaToolTiers.WOOD, false, ItemTypeEntries.SPRUCE_PLANKS, BlockTypeEntries.SPRUCE_PLANKS, true)
    }
    private fun NovaBlockBuilder.brick() {
        breakable(2.0, setOf(VanillaToolCategories.PICKAXE), VanillaToolTiers.WOOD, true, ItemTypeEntries.BRICKS, BlockTypeEntries.BRICKS, true)
        explosionResistance(6.0f)
    }
    private fun NovaBlockBuilder.barrel() {
        breakable(2.5, setOf(VanillaToolCategories.AXE), VanillaToolTiers.WOOD, false, ItemTypeEntries.OAK_PLANKS, BlockTypeEntries.OAK_PLANKS, true)
    }
    
    val COOKING_POT = tileEntity("cooking_pot", ::CookingPot) {
        behaviors(
            TileEntityDrops,
            TileEntityInteractive,
            CookingPotBehavior
        )
        lightMetal()
        sounds { SoundGroup.LANTERN }
        tickrate(20)
        stateProperties(DefaultBlockStateProperties.FACING_HORIZONTAL, BlockStateProperties.SUPPORT, BlockStateProperties.HEATED, DefaultBlockStateProperties.WATERLOGGED)
        stateBacked(BackingStateCategory.LEAVES) {
            val support = getPropertyValueOrThrow(BlockStateProperties.SUPPORT)
            when (support) {
                CookingPotSupport.HANDLE -> getModel("block/cooking_pot_handle").rotated()
                CookingPotSupport.TRAY -> getModel("block/cooking_pot_tray").rotated()
                else -> getModel("block/cooking_pot").rotated()
            }
        }
    }
    val CUTTING_BOARD = tileEntity("cutting_board", ::CuttingBoard) {
        behaviors(
            TileEntityDrops,
            TileEntityInteractive,
            CuttingBoardBehavior
        )
        sprucePlank()
        sounds { SoundGroup.WOOD }
        stateProperties(DefaultBlockStateProperties.FACING_HORIZONTAL)
        stateBacked(BackingStateCategory.TRIPWIRE_ATTACHED) { defaultModel.rotated() }
    }
    val SKILLET = tileEntity("skillet", ::Skillet) {
        behaviors(
            TileEntityDrops,
            TileEntityInteractive,
            SkilletBehavior
        )
        lightMetal()
        sounds { SoundGroup.LANTERN }
        tickrate(20)
        stateProperties(DefaultBlockStateProperties.FACING_HORIZONTAL, BlockStateProperties.TRAY, DefaultBlockStateProperties.WATERLOGGED)
        // Entity-backed so the outline matches the pan, (1,0,1)-(15,4,15), instead of a full leaves cube.
        // The client-side block is a level-0 light block: no collision, hidden outline, no light, and waterloggable
        // (Nova copies WATERLOGGED onto it). Clicks on the interaction hitbox are routed to the block by Nova.
        entityBacked(
            stateSelector = { SKILLET_CLIENT_BLOCK },
            extraHitboxSelector = { listOf(SKILLET_HITBOX) }
        ) {
            val hasTray = getPropertyValueOrThrow(BlockStateProperties.TRAY)
            if (hasTray) getModel("block/skillet_tray").rotated() else getModel("block/skillet").rotated()
        }
    }
    val STOVE = tileEntity("stove", ::Stove) {
        behaviors(
            TileEntityDrops,
            TileEntityInteractive
        )
        brick()
        sounds { SoundGroup.STONE }
        tickrate(20)
        stateProperties(DefaultBlockStateProperties.FACING_HORIZONTAL, BlockStateProperties.LIT)
        lightEmission { if (getPropertyValueOrThrow(BlockStateProperties.LIT)) 13 else 0 }
        entityBacked {
            val lit = getPropertyValueOrThrow(BlockStateProperties.LIT)
            val modelName = if (lit) "stove_on" else "stove"
            getModel("block/$modelName").rotated()
        }
    }
    
    val OAK_CABINET = cabinetBlock("oak_cabinet")
    val SPRUCE_CABINET = cabinetBlock("spruce_cabinet")
    val BIRCH_CABINET = cabinetBlock("birch_cabinet")
    val JUNGLE_CABINET = cabinetBlock("jungle_cabinet")
    val ACACIA_CABINET = cabinetBlock("acacia_cabinet")
    val DARK_OAK_CABINET = cabinetBlock("dark_oak_cabinet")
    val MANGROVE_CABINET = cabinetBlock("mangrove_cabinet")
    val CHERRY_CABINET = cabinetBlock("cherry_cabinet") { SoundGroup.CHERRY_WOOD }
    val BAMBOO_CABINET = cabinetBlock("bamboo_cabinet") { SoundGroup.BAMBOO_WOOD }
    val CRIMSON_CABINET = cabinetBlock("crimson_cabinet") { SoundGroup.NETHER_WOOD }
    val WARPED_CABINET = cabinetBlock("warped_cabinet") { SoundGroup.NETHER_WOOD }
    
    val WOODEN_BASKET = basketBlock("wooden_basket") { SoundGroup.WOOD }
    val BAMBOO_BASKET = basketBlock("bamboo_basket") { SoundGroup.BAMBOO_WOOD }
    
    val CARROT_CRATE = nonInteractiveBlock("carrot_crate") { behaviors(BlockDrops); crate(); sounds { SoundGroup.WOOD } }
    val POTATO_CRATE = nonInteractiveBlock("potato_crate") { behaviors(BlockDrops); crate(); sounds { SoundGroup.WOOD } }
    val BEETROOT_CRATE = nonInteractiveBlock("beetroot_crate") { behaviors(BlockDrops); crate(); sounds { SoundGroup.WOOD } }
    val CABBAGE_CRATE = nonInteractiveBlock("cabbage_crate") { behaviors(BlockDrops); crate(); sounds { SoundGroup.WOOD } }
    val TOMATO_CRATE = nonInteractiveBlock("tomato_crate") { behaviors(BlockDrops); crate(); sounds { SoundGroup.WOOD } }
    val ONION_CRATE = nonInteractiveBlock("onion_crate") { behaviors(BlockDrops); crate(); sounds { SoundGroup.WOOD } }
    val RICE_BALE = nonInteractiveBlock("rice_bale") {
        behaviors(BlockDrops)
        bale()
        sounds { SoundGroup.GRASS }
        stateProperties(DefaultBlockStateProperties.FACING_CARTESIAN)
        stateBacked(BackingStateCategory.MUSHROOM_BLOCK, BackingStateCategory.NOTE_BLOCK) {
            when (getPropertyValueOrThrow(DefaultBlockStateProperties.FACING_CARTESIAN)) {
                BlockFace.UP -> defaultModel.rotateX(-90.0)
                BlockFace.DOWN -> defaultModel.rotateX(90.0)
                BlockFace.EAST -> defaultModel.rotateY(270.0)
                BlockFace.SOUTH -> defaultModel.rotateY(180.0)
                BlockFace.WEST -> defaultModel.rotateY(90.0)
                else -> defaultModel
            }
        }
    }
    val RICE_BAG = nonInteractiveBlock("rice_bag") { behaviors(BlockDrops); bag(); sounds { SoundGroup.WOOL } }
    val STRAW_BALE = block("straw_bale") {
        behaviors(BlockDrops)
        bale()
        sounds { SoundGroup.GRASS }
        stateProperties(DefaultBlockStateProperties.AXIS)
        stateBacked(BackingStateCategory.MUSHROOM_BLOCK, BackingStateCategory.NOTE_BLOCK) {
            defaultModel.rotated()
        }
    }
    
    val TATAMI = block("tatami") {
        behaviors(PairedBlock, BlockDrops)
        bag()
        sounds { SoundGroup.WOOL }
        stateProperties(DefaultBlockStateProperties.FACING_CARTESIAN, BlockStateProperties.PAIRED)
        stateBacked(BackingStateCategory.MUSHROOM_BLOCK, BackingStateCategory.NOTE_BLOCK) {
            val paired = getPropertyValueOrThrow(BlockStateProperties.PAIRED)
            val face = getPropertyValueOrThrow(DefaultBlockStateProperties.FACING_CARTESIAN)
            if (paired) {
                when (face) {
                    BlockFace.DOWN -> getModel("block/tatami_odd")
                    BlockFace.UP -> getModel("block/tatami_even").rotateX(-180.0)
                    BlockFace.NORTH -> getModel("block/tatami_odd").rotateX(-90.0)
                    BlockFace.SOUTH -> getModel("block/tatami_even").rotateX(90.0)
                    BlockFace.EAST -> getModel("block/tatami_odd").rotateZ(90.0)
                    BlockFace.WEST -> getModel("block/tatami_even").rotateZ(-90.0)
                    else -> getModel("block/tatami_half").rotated()
                }
            }
            else {
                when (face) {
                    BlockFace.UP -> getModel("block/tatami_half").rotateX(-90.0)
                    BlockFace.DOWN -> getModel("block/tatami_half").rotateX(90.0)
                    BlockFace.EAST -> getModel("block/tatami_half").rotateY(270.0)
                    BlockFace.SOUTH -> getModel("block/tatami_half").rotateY(180.0)
                    BlockFace.WEST -> getModel("block/tatami_half").rotateY(90.0)
                    else -> getModel("block/tatami_half")
                }
            }
        }
    }
    
    val FULL_TATAMI_MAT_HEAD = block("full_tatami_mat") {
        behaviors(TatamiMatHead, BlockDrops)
        bag()
        sounds { SoundGroup.WOOL }
        stateProperties(DefaultBlockStateProperties.FACING_HORIZONTAL)
        stateBacked(BackingStateCategory.TRIPWIRE_ATTACHED, BackingStateCategory.TRIPWIRE_UNATTACHED) {
            getModel("block/tatami_mat_head").rotateY(180.0).rotated()
        }
    }
    
    val FULL_TATAMI_MAT_FOOT = block("full_tatami_mat_foot") {
        behaviors(TatamiMatFoot)
        bag()
        sounds { SoundGroup.WOOL }
        stateProperties(DefaultBlockStateProperties.FACING_HORIZONTAL)
        stateBacked(BackingStateCategory.TRIPWIRE_ATTACHED, BackingStateCategory.TRIPWIRE_UNATTACHED) {
            getModel("block/tatami_mat_foot").rotated()
        }
    }
    
    val HALF_TATAMI_MAT = block("half_tatami_mat") {
        behaviors(BlockDrops)
        bag()
        sounds { SoundGroup.WOOL }
        stateProperties(DefaultBlockStateProperties.FACING_HORIZONTAL)
        stateBacked(BackingStateCategory.TRIPWIRE_ATTACHED, BackingStateCategory.TRIPWIRE_UNATTACHED) {
            val face = getPropertyValueOrThrow(DefaultBlockStateProperties.FACING_HORIZONTAL)
                when (face) {
                    BlockFace.SOUTH, BlockFace.NORTH -> getModel("block/tatami_mat_half")
                    else -> getModel("block/tatami_mat_half").rotateY(90.0)
                }
        }
    }
    
    val CANVAS_RUG = block("canvas_rug") {
        behaviors(BlockDrops)
        bag()
        sounds { SoundGroup.WOOL }
        stateBacked(BackingStateCategory.TRIPWIRE_ATTACHED, BackingStateCategory.TRIPWIRE_UNATTACHED)
    }
    
    val MUDDY_FARMLAND = block("muddy_farmland") {
        behaviors(MuddyFarmland)
        mud()
        sounds { SoundGroup.MUD }
        // Only leaves backing states can be waterlogged.
        stateProperties(DefaultBlockStateProperties.WATERLOGGED, BlockStateProperties.CROP)
        stateBacked(BackingStateCategory.LEAVES) {
            if (getPropertyValueOrThrow(BlockStateProperties.CROP)) getModel("block/muddy_farmland_crop") else defaultModel
        }
    }
    
    val SANDY_SHRUB = plantBlock("sandy_shrub") { behaviors(SandyShrub()); crop(); sounds { SoundGroup.GRASS } }
    val WILD_CABBAGES = plantBlock("wild_cabbages") { behaviors(WildCabbages()); crop(); sounds { SoundGroup.GRASS } }
    val WILD_ONIONS = plantBlock("wild_onions") { behaviors(WildOnions()); crop(); sounds { SoundGroup.GRASS } }
    val WILD_TOMATOES = plantBlock("wild_tomatoes") { behaviors(WildTomatoes()); crop(); sounds { SoundGroup.GRASS } }
    val WILD_CARROTS = plantBlock("wild_carrots") { behaviors(WildCarrots()); crop(); sounds { SoundGroup.GRASS } }
    val WILD_POTATOES = plantBlock("wild_potatoes") { behaviors(WildPotatoes()); crop(); sounds { SoundGroup.GRASS } }
    val WILD_BEETROOTS = plantBlock("wild_beetroots") { behaviors(WildBeetroots()); crop(); sounds { SoundGroup.GRASS } }
    val WILD_RICE = plantBlock("wild_rice") { behaviors(WildRice()); crop(); sounds { SoundGroup.GRASS } }
    
    // val BROWN_MUSHROOM_COLONY = cropBlock("brown_mushroom_colony", TomatoCrop, 3)
    // val RED_MUSHROOM_COLONY = cropBlock("red_mushroom_colony", TomatoCrop, 3)
    val CABBAGES_CROP = cropBlock("cabbages", CabbageCrop, 7)
    val TOMATOES_CROP = cropBlock("tomatoes", TomatoCrop, 7, 3)
    val ONION_CROP = cropBlock("onions", OnionCrop, 3)
    val RICE_CROP = cropBlock("rice", RiceCrop, 7, 3)
    
    val APPLE_PIE = pieBlock("apple_pie", ApplePie)
    val SWEET_BERRY_CHEESECAKE = pieBlock("sweet_berry_cheesecake", SweetBerryCheesecake)
    val CHOCOLATE_PIE = pieBlock("chocolate_pie", ChocolatePie)
    
    val ROAST_CHICKEN_BLOCK = feastBlock("roast_chicken_block", RoastChickenBlock, true)
    val STUFFED_PUMPKIN_BLOCK = feastBlock("stuffed_pumpkin_block", StuffedPumpkinBlock, false)
    val HONEY_GLAZED_HAM_BLOCK = feastBlock("honey_glazed_ham_block", HoneyGlazedHamBlock, true)
    val SHEPHERDS_PIE_BLOCK = feastBlock("shepherds_pie_block", ShepherdsPieBlock, true)
    val RICE_ROLL_MEDLEY_BLOCK = feastBlock("rice_roll_medley_block", RiceRollMedleyBlock, true, 8)
    
    
    private fun nonInteractiveBlock(
        name: String,
        block: NovaBlockBuilder.() -> Unit
    ): RegistryEntry.Paper<BlockType> = block(name) {
        block()
        stateBacked(BackingStateCategory.MUSHROOM_BLOCK, BackingStateCategory.NOTE_BLOCK)
    }
    
    private fun cabinetBlock(
        name: String,
        soundGroup: BlockSelectorScope.() -> SoundGroup = { SoundGroup.WOOD }
    ): RegistryEntry.Paper<BlockType> = tileEntity(name, ::Cabinet) {
        behaviors(
            TileEntityDrops,
            TileEntityInteractive
        )
        barrel()
        sounds(soundGroup)
        stateProperties(DefaultBlockStateProperties.FACING_HORIZONTAL, BlockStateProperties.OPEN)
        entityBacked {
            val open = getPropertyValueOrThrow(BlockStateProperties.OPEN)
            val modelName = if (open) "${name}_open" else name
            getModel("block/$modelName").rotated()
        }
    }
    
    private fun basketBlock(
        name: String,
        soundGroup: BlockSelectorScope.() -> SoundGroup = { SoundGroup.WOOD }
    ): RegistryEntry.Paper<BlockType> = tileEntity(name, ::Basket) {
        behaviors(
            TileEntityDrops,
            TileEntityInteractive
        )
        breakable(1.5, emptySet(), null, false, ItemTypeEntries.OAK_PLANKS, BlockTypeEntries.OAK_PLANKS, true)
        sounds(soundGroup)
        tickrate(20)
        stateProperties(BlockStateProperties.ENABLED)
        // Leaves backing states do not occlude neighbours, so faces behind the open basket/handle stay rendered
        // (mushroom/note block backings are opaque full cubes and culled them, letting players see underground).
        stateBacked(BackingStateCategory.LEAVES) { getModel("block/$name") }
    }
    
    private fun plantBlock(
        name: String,
        block: NovaBlockBuilder.() -> Unit = {}
    ): RegistryEntry.Paper<BlockType> = block(name) {
        block()
        stateBacked(BackingStateCategory.TRIPWIRE_UNATTACHED, BackingStateCategory.TRIPWIRE_ATTACHED)
    }
    
    private fun cropBlock(
        name: String,
        cropBehavior: BlockBehaviorHolder,
        maxAge: Int,
        buddingAge: Int = -1,
        block: NovaBlockBuilder.() -> Unit = {}
    ): RegistryEntry.Paper<BlockType> = block("${name}_crop") {
        block()
        behaviors(Ageable(maxAge, buddingAge), cropBehavior)
        crop()
        sounds { SoundGroup.CROP }
        stateProperties(BlockStateProperties.AGE, BlockStateProperties.BUDDING_AGE, BlockStateProperties.MAX_AGE)
        stateBacked(BackingStateCategory.TRIPWIRE_UNATTACHED, BackingStateCategory.TRIPWIRE_ATTACHED) {
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
    
    private fun feastBlock(
        name: String,
        behaviorHolder: BlockBehaviorHolder,
        hasLeftover: Boolean,
        maxServing: Int = 4,
        block: NovaBlockBuilder.() -> Unit = {}
    ): RegistryEntry.Paper<BlockType> = block(name) {
        block()
        behaviors(behaviorHolder)
        sounds { SoundGroup.WOOL }
        breakable(0.5, emptySet(), null, false, ItemTypeEntries.WHEAT, BlockTypeEntries.WHEAT, true)
        stateProperties(BlockStateProperties.SERVINGS, DefaultBlockStateProperties.FACING_HORIZONTAL)
        entityBacked {
            val servings = (maxServing - minOf(getPropertyValueOrThrow(BlockStateProperties.SERVINGS), maxServing))
            val suffix = if (servings <= (maxServing - 1)) "_stage$servings" else if (hasLeftover) "_leftover" else "_stage3"
            getModel("block/${name}$suffix").rotated()
        }
    }
    
    private fun pieBlock(
        name: String,
        behaviorHolder: BlockBehaviorHolder,
        block: NovaBlockBuilder.() -> Unit = {}
    ): RegistryEntry.Paper<BlockType> = block(name) {
        block()
        behaviors(behaviorHolder)
        sounds { SoundGroup.WOOL }
        breakable(0.5, emptySet(), null, false, ItemTypeEntries.CAKE, BlockTypeEntries.CAKE, true)
        stateProperties(BlockStateProperties.BITES, DefaultBlockStateProperties.FACING_HORIZONTAL)
        entityBacked {
            val bites = getPropertyValueOrThrow(BlockStateProperties.BITES)
            val suffix = if (bites > 0) "_slice$bites" else ""
            getModel("block/${name}$suffix").rotated()
        }
    }
    
}