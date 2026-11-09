package fr.ateastudio.farmersdelight.nms

import net.minecraft.core.BlockPos
import net.minecraft.world.phys.shapes.BooleanOp
import net.minecraft.world.phys.shapes.Shapes
import org.bukkit.block.Block
import org.bukkit.craftbukkit.CraftWorld
import org.bukkit.util.BoundingBox

/** [NmsBridge] for the Paper/Minecraft version this addon is compiled against (mojang-mapped internals). */
internal object PaperNmsBridge : NmsBridge {

    override fun outlineIntersects(block: Block, box: BoundingBox): Boolean {
        val level = (block.world as CraftWorld).handle
        val pos = BlockPos(block.x, block.y, block.z)
        val shape = level.getBlockState(pos).getShape(level, pos)
        val area = Shapes.box(box.minX, box.minY, box.minZ, box.maxX, box.maxY, box.maxZ)
        return Shapes.joinIsNotEmpty(area, shape, BooleanOp.AND)
    }

    override fun updateComparatorOutput(block: Block) {
        val level = (block.world as CraftWorld).handle
        val pos = BlockPos(block.x, block.y, block.z)
        level.updateNeighbourForOutputSignal(pos, level.getBlockState(pos).block)
    }
}
