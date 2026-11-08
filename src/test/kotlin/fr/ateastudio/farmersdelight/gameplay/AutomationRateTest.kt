package fr.ateastudio.farmersdelight.gameplay

import fr.ateastudio.farmersdelight.tileentity.network.TransferRateLimiter
import kotlin.test.Test
import kotlin.test.assertEquals

class AutomationRateTest {

    @Test
    fun insertionMatchesVanillaHopperSpeedAcrossNetworkTicks() {
        // Nova default item network tick delay is 20 game ticks: at most 3 items per burst.
        val limiter = TransferRateLimiter { 3 }
        var inserted = 0
        var tick = 0L
        repeat(10) {
            val allowed = limiter.allowance(tick)
            limiter.consume(allowed)
            inserted += allowed
            tick += 20
        }
        // 1 initial item + 180 elapsed ticks / 8 ticks per item = 23 items, never a whole stack at once.
        assertEquals(23, inserted)
    }

    @Test
    fun unusedCreditIsCappedAndRejectedItemsAreNotCharged() {
        val limiter = TransferRateLimiter { 3 }
        assertEquals(1, limiter.allowance(0))
        limiter.consume(0) // destination full: nothing accepted, credit kept
        assertEquals(3, limiter.allowance(10_000)) // long idle period does not allow a full stack
        limiter.consume(3)
        assertEquals(0, limiter.allowance(10_007))
        assertEquals(1, limiter.allowance(10_008))
    }
}
