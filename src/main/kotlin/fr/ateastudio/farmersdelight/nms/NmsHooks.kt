package fr.ateastudio.farmersdelight.nms

import fr.ateastudio.farmersdelight.block.ComparatorSignals
import net.minecraft.core.BlockPos
import net.minecraft.world.level.Level

/**
 * Static entry points called from mixins (Java, invokedynamic-safe). They only translate Minecraft types
 * into Bukkit ones and delegate to the addon's Bukkit-level logic.
 */
object NmsHooks {

    /** Comparator signal of the Nova tile entity at [pos], or -1 if it does not provide one. */
    @JvmStatic
    fun readComparatorSignal(level: Level, pos: BlockPos): Int =
        ComparatorSignals.read(level.world.getBlockAt(pos.x, pos.y, pos.z))
}
