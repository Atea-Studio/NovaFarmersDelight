package fr.ateastudio.farmersdelight.block.behavior

import fr.ateastudio.farmersdelight.block.BlockStateProperties
import fr.ateastudio.farmersdelight.registry.Blocks
import io.papermc.paper.registry.RegistryAccess
import io.papermc.paper.registry.RegistryKey
import io.papermc.paper.registry.keys.tags.BlockTypeTagKeys
import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.block.BlockType
import org.bukkit.inventory.ItemStack
import xyz.xenondevs.nova.context.Context
import xyz.xenondevs.nova.context.intention.BlockBreak
import xyz.xenondevs.nova.context.intention.BlockPlace
import xyz.xenondevs.nova.util.above
import xyz.xenondevs.nova.util.runTask
import xyz.xenondevs.nova.world.block.NovaBlockState
import xyz.xenondevs.nova.world.block.behavior.BlockBehavior
import xyz.xenondevs.nova.world.block.blockType
import xyz.xenondevs.nova.world.block.novaBlockState
import xyz.xenondevs.nova.world.block.state.property.DefaultBlockStateProperties

private val VANILLA_PLANTS = setOf(
    BlockType.SHORT_GRASS, BlockType.TALL_GRASS, BlockType.FERN, BlockType.LARGE_FERN,
    BlockType.SHORT_DRY_GRASS, BlockType.TALL_DRY_GRASS, BlockType.BUSH, BlockType.FIREFLY_BUSH, BlockType.DEAD_BUSH,
    BlockType.SUGAR_CANE, BlockType.BAMBOO, BlockType.BAMBOO_SAPLING, BlockType.SWEET_BERRY_BUSH,
    BlockType.BROWN_MUSHROOM, BlockType.RED_MUSHROOM, BlockType.PITCHER_PLANT
)

private val VANILLA_PLANT_TAGS: Set<BlockType> by lazy {
    val registry = RegistryAccess.registryAccess().getRegistry(RegistryKey.BLOCK)
    listOf(BlockTypeTagKeys.CROPS, BlockTypeTagKeys.FLOWERS, BlockTypeTagKeys.SAPLINGS)
        .flatMapTo(HashSet()) { registry.getTagValues(it) }
}

/** True for vanilla crops, flowers, saplings and small plants (by block type). */
internal fun isVanillaPlant(type: BlockType): Boolean =
    type in VANILLA_PLANTS || type in VANILLA_PLANT_TAGS

/** Rice only grows naturally on waterlogged muddy farmland. */
internal fun Block.isWaterloggedMuddyFarmland(): Boolean =
    blockType == Blocks.MUDDY_FARMLAND.get() && novaBlockState?.get(DefaultBlockStateProperties.WATERLOGGED) == true

@Suppress("UnstableApiUsage")
object MuddyFarmland : BlockBehavior {

    private val addonPlants: Set<BlockType> by lazy {
        listOf(
            Blocks.RICE_CROP, Blocks.CABBAGES_CROP, Blocks.TOMATOES_CROP, Blocks.ONION_CROP,
            Blocks.WILD_RICE, Blocks.WILD_CABBAGES, Blocks.WILD_ONIONS, Blocks.WILD_TOMATOES,
            Blocks.WILD_CARROTS, Blocks.WILD_POTATOES, Blocks.WILD_BEETROOTS, Blocks.SANDY_SHRUB
        ).mapTo(HashSet()) { it.get() }
    }

    /** Whether a crop or plant grows on top, selecting the `muddy_farmland_crop` model. */
    private fun hasCropAbove(pos: Block): Boolean {
        val above = pos.above
        return above.blockType in addonPlants || isVanillaPlant(above.blockType)
    }

    override fun handlePlace(block: Block, state: NovaBlockState, ctx: Context<BlockPlace>) {
        state[BlockStateProperties.CROP] = hasCropAbove(block)
        block.blockData = state
        if (isCoveredBySolidBlock(block)) scheduleTurnToMud(block)
    }

    override fun handleNeighborChanged(block: Block, state: NovaBlockState) {
        if (isCoveredBySolidBlock(block)) {
            scheduleTurnToMud(block)
            return
        }
        val crop = hasCropAbove(block)
        if (state[BlockStateProperties.CROP] != crop) {
            state[BlockStateProperties.CROP] = crop
            block.blockData = state
        }
    }

    /**
     * Like vanilla farmland: a solid block above (except fence gates) makes it unable to survive.
     * Uses the collision shape rather than [Material.isSolid]: Nova blocks (e.g. crops) report a placeholder
     * material server-side that counts as solid, which would turn the farmland to mud when planting.
     */
    private fun isCoveredBySolidBlock(pos: Block): Boolean {
        val above = pos.above
        if (above.isEmpty || above.isPassable) return false
        if (above.blockType in addonPlants || isVanillaPlant(above.blockType)) return false
        return above.blockType !in VANILLA_FENCE_GATES
    }

    /** Vanilla farmland turns to dirt on the next tick; muddy farmland turns back to plain mud. */
    private fun scheduleTurnToMud(pos: Block) {
        runTask {
            if (pos.blockType == Blocks.MUDDY_FARMLAND.get() && isCoveredBySolidBlock(pos))
                pos.blockType = BlockType.MUD
        }
    }
    
    override fun getDrops(block: Block, state: NovaBlockState, ctx: Context<BlockBreak>): List<ItemStack> {
        if (!ctx[BlockBreak.BLOCK_DROPS] || ctx[BlockBreak.SOURCE_PLAYER]?.gameMode == GameMode.CREATIVE)
            return emptyList()
        return listOf(org.bukkit.inventory.ItemType.MUD.createItemStack())
    }

}

private val VANILLA_FENCE_GATES: Set<BlockType> by lazy {
    RegistryAccess.registryAccess().getRegistry(RegistryKey.BLOCK).getTagValues(BlockTypeTagKeys.FENCE_GATES).toSet()
}
