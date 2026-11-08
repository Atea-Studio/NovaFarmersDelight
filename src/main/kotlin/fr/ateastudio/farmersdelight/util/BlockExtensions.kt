package fr.ateastudio.farmersdelight.util

import fr.ateastudio.farmersdelight.block.BlockStateProperties
import fr.ateastudio.farmersdelight.registry.Blocks
import fr.ateastudio.farmersdelight.registry.Tags
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.block.BlockFace
import org.bukkit.block.data.Lightable
import xyz.xenondevs.nova.util.below
import xyz.xenondevs.nova.world.block.blockType
import xyz.xenondevs.nova.world.block.novaBlockState


fun Block.isTag(tag: List<Material>): Boolean {
    return tag.contains(this.type)
}

/** Membership of `farmersdelight:tray_heat_sources`, irrespective of any lit state. */
fun Block.isTrayHeatSource(): Boolean = isTag(Tags.TRAY_HEAT_SOURCE)

fun Block.isHeatConductor(): Boolean = isTag(Tags.HEAT_CONDUCTOR)

/** Receives redstone power, directly or indirectly (vanilla `hasNeighborSignal`). */
fun Block.isRedstonePowered(): Boolean = isBlockPowered || isBlockIndirectlyPowered

/**
 * Membership of `farmersdelight:heat_sources` (which includes the stove and every tray heat source),
 * requiring the block to be lit when it has a lit state.
 */
fun Block.isHeatSource(): Boolean {
    val novaState = novaBlockState
    if (novaState != null) {
        if (blockType != Blocks.STOVE.get()) return false
        return isLitHeatSource(isMember = true, hasLitState = true, lit = novaState[BlockStateProperties.LIT] == true)
    }
    val lightable = blockData as? Lightable
    return isLitHeatSource(
        isMember = isTag(Tags.HEAT_SOURCE) || isTrayHeatSource(),
        hasLitState = lightable != null,
        lit = lightable?.isLit == true
    )
}

/** Heat for a block placed on top of [this] block's position, i.e. checks the blocks underneath. */
fun Block.isHeatedFromBelow(): Boolean {
    val under = this.below
    if (under.isHeatSource()) return true
    return isHeatedThroughConductor(under.isHeatConductor(), under.below.isHeatSource())
}

/** Membership of `farmersdelight:heat_sources` only, without checking any lit state. */
fun Block.isHeatSourceMember(): Boolean {
    if (novaBlockState != null) return blockType == Blocks.STOVE.get()
    return isTag(Tags.HEAT_SOURCE) || isTrayHeatSource()
}

internal fun isLitHeatSource(isMember: Boolean, hasLitState: Boolean, lit: Boolean): Boolean =
    isMember && (!hasLitState || lit)

internal fun isHeatedThroughConductor(belowIsConductor: Boolean, twoBelowIsSource: Boolean): Boolean =
    belowIsConductor && twoBelowIsSource

private val HORIZONTAL_CLOCKWISE = listOf(BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST)

internal fun BlockFace.clockWise(): BlockFace =
    HORIZONTAL_CLOCKWISE[(HORIZONTAL_CLOCKWISE.indexOf(this).also { require(it >= 0) } + 1) % 4]

internal fun BlockFace.counterClockWise(): BlockFace =
    HORIZONTAL_CLOCKWISE[(HORIZONTAL_CLOCKWISE.indexOf(this).also { require(it >= 0) } + 3) % 4]