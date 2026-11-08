package fr.ateastudio.farmersdelight.block.behavior

import fr.ateastudio.farmersdelight.block.BlockStateProperties
import fr.ateastudio.farmersdelight.registry.Blocks
import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.Tag
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
    Material.SHORT_GRASS, Material.TALL_GRASS, Material.FERN, Material.LARGE_FERN,
    Material.SHORT_DRY_GRASS, Material.TALL_DRY_GRASS, Material.BUSH, Material.FIREFLY_BUSH, Material.DEAD_BUSH,
    Material.SUGAR_CANE, Material.BAMBOO, Material.BAMBOO_SAPLING, Material.SWEET_BERRY_BUSH,
    Material.BROWN_MUSHROOM, Material.RED_MUSHROOM, Material.PITCHER_PLANT
)

/** True for vanilla crops, flowers, saplings and small plants (by block type). */
internal fun isVanillaPlant(type: Material): Boolean =
    type in VANILLA_PLANTS || Tag.CROPS.isTagged(type) || Tag.FLOWERS.isTagged(type) || Tag.SAPLINGS.isTagged(type)

/** Rice only grows naturally on waterlogged muddy farmland. */
internal fun Block.isWaterloggedMuddyFarmland(): Boolean =
    blockType == Blocks.MUDDY_FARMLAND.get() && novaBlockState?.get(DefaultBlockStateProperties.WATERLOGGED) == true

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
        return above.blockType in addonPlants || isVanillaPlant(above.type)
    }

    override fun handlePlace(pos: Block, state: NovaBlockState, ctx: Context<BlockPlace>) {
        state[BlockStateProperties.CROP] = hasCropAbove(pos)
        pos.blockData = state
        if (isCoveredBySolidBlock(pos)) scheduleTurnToMud(pos)
    }

    override fun handleNeighborChanged(pos: Block, state: NovaBlockState) {
        if (isCoveredBySolidBlock(pos)) {
            scheduleTurnToMud(pos)
            return
        }
        val crop = hasCropAbove(pos)
        if (state[BlockStateProperties.CROP] != crop) {
            state[BlockStateProperties.CROP] = crop
            pos.blockData = state
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
        if (above.blockType in addonPlants || isVanillaPlant(above.type)) return false
        return !Tag.FENCE_GATES.isTagged(above.type)
    }

    /** Vanilla farmland turns to dirt on the next tick; muddy farmland turns back to plain mud. */
    private fun scheduleTurnToMud(pos: Block) {
        runTask {
            if (pos.blockType == Blocks.MUDDY_FARMLAND.get() && isCoveredBySolidBlock(pos))
                pos.type = Material.MUD
        }
    }

    override fun getDrops(pos: Block, state: NovaBlockState, ctx: Context<BlockBreak>): List<ItemStack> {
        if (!ctx[BlockBreak.BLOCK_DROPS] || ctx[BlockBreak.SOURCE_PLAYER]?.gameMode == GameMode.CREATIVE)
            return emptyList()
        return listOf(ItemStack(Material.MUD))
    }

}
