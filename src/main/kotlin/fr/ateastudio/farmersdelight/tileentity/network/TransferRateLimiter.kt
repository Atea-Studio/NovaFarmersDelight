package fr.ateastudio.farmersdelight.tileentity.network

import kotlin.math.max
import kotlin.math.min

/**
 * Caps automated insertion to vanilla hopper throughput (one item every [ticksPerItem] game ticks).
 * Nova item networks transfer in bursts every few ticks with no default limit, so unused credit is kept
 * up to [maxBurst] items to keep the average rate equal to a vanilla hopper.
 *
 * Several networks (one per connected face) may tick in parallel, hence the synchronization.
 */
internal class TransferRateLimiter(
    private val ticksPerItem: Int = VANILLA_HOPPER_TICKS_PER_ITEM,
    private val maxBurst: () -> Int
) {
    private var lastTick: Long = Long.MIN_VALUE
    private var credit = 0

    /** Items that may be inserted at [currentTick]. */
    @Synchronized
    fun allowance(currentTick: Long): Int {
        val burst = max(1, maxBurst())
        if (lastTick == Long.MIN_VALUE || currentTick < lastTick) {
            lastTick = currentTick
            credit = 1
        } else {
            val periods = (currentTick - lastTick) / ticksPerItem
            if (periods > 0) {
                credit = min(burst.toLong(), credit + periods).toInt()
                lastTick += periods * ticksPerItem
            }
        }
        return min(credit, burst)
    }

    @Synchronized
    fun consume(amount: Int) {
        credit = max(0, credit - amount)
    }

    companion object {
        const val VANILLA_HOPPER_TICKS_PER_ITEM = 8
    }
}
