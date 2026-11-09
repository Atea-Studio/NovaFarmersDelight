package fr.ateastudio.farmersdelight.block

import fr.ateastudio.farmersdelight.nms.Nms
import org.bukkit.Bukkit
import org.bukkit.block.Block
import xyz.xenondevs.invui.inventory.VirtualInventory
import xyz.xenondevs.nova.util.runTask
import xyz.xenondevs.nova.world.block.novaTileEntity

/** A Nova tile entity that provides a comparator signal (0..15). */
interface ComparatorSignalSource {
    fun comparatorSignal(): Int
}

/**
 * Fullness comparator over N slots, each given as (count, effective limit = min(slot limit, item stack limit)):
 * 0 when every slot is empty, otherwise `floor(14 * sum(count / limit) / N) + 1`, capped at 15.
 */
internal fun comparatorFullness(slots: List<Pair<Int, Int>>): Int {
    if (slots.isEmpty() || slots.all { it.first <= 0 }) return 0
    val fullness = slots.sumOf { (count, limit) -> if (count <= 0) 0.0 else count.toDouble() / limit.coerceAtLeast(1) } / slots.size
    return (Math.floor(14 * fullness).toInt() + 1).coerceAtMost(15)
}

internal fun VirtualInventory.fullnessSlots(): List<Pair<Int, Int>> = (0 until size).map { slot ->
    val stack = getItem(slot)
    if (stack == null || stack.isEmpty) 0 to 1
    else stack.amount to minOf(getMaxSlotStackSize(slot), stack.maxStackSize)
}

/** Bridge between the vanilla comparator (via mixin, see [fr.ateastudio.farmersdelight.nms.NmsHooks]) and Nova tile entities. */
object ComparatorSignals {

    /** Signal of the Nova tile entity at [block], or -1 if it is not a [ComparatorSignalSource]. */
    fun read(block: Block): Int {
        val source = block.novaTileEntity as? ComparatorSignalSource ?: return -1
        return source.comparatorSignal().coerceIn(0, 15)
    }

    /** Asks adjacent comparators (directly or through one solid block) to re-read [block]. */
    fun notifyChanged(block: Block) {
        if (!Bukkit.isPrimaryThread()) {
            runTask { notifyChanged(block) }
            return
        }
        Nms.updateComparatorOutput(block)
    }
}
