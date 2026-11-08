package fr.ateastudio.farmersdelight.tileentity.network

import org.bukkit.Bukkit
import org.bukkit.inventory.ItemStack
import xyz.xenondevs.nova.world.block.tileentity.network.type.item.ItemNetwork
import xyz.xenondevs.nova.world.block.tileentity.network.type.item.inventory.NetworkedInventory
import xyz.xenondevs.invui.inventory.VirtualInventory
import xyz.xenondevs.nova.world.block.tileentity.network.type.item.inventory.NetworkedVirtualInventory
import java.util.UUID
import kotlin.math.ceil
import kotlin.math.min

/** Limiter sized so one Nova item-network tick can move what a vanilla hopper would in the same time. */
internal fun hopperRateLimiter() = TransferRateLimiter {
    ceil(ItemNetwork.TICK_DELAY_PROVIDER.get().toDouble() / TransferRateLimiter.VANILLA_HOPPER_TICKS_PER_ITEM).toInt()
}

/**
 * Inserts at most [limiter]'s allowance through [insert] and records what was actually accepted.
 * @return the leftover amount, as required by [NetworkedInventory.add].
 */
internal inline fun rateLimitedAdd(limiter: TransferRateLimiter, amount: Int, insert: (Int) -> Int): Int {
    val allowed = min(amount, limiter.allowance(Bukkit.getCurrentTick().toLong()))
    if (allowed <= 0) return amount
    val added = allowed - insert(allowed)
    limiter.consume(added)
    return amount - added
}

/** A [NetworkedVirtualInventory] whose automated insertion follows vanilla hopper speed. */
internal class RateLimitedNetworkedInventory(
    val delegate: NetworkedVirtualInventory,
    private val limiter: TransferRateLimiter = hopperRateLimiter(),
    override val uuid: UUID = delegate.uuid
) : NetworkedInventory by delegate {

    override fun add(itemStack: ItemStack, amount: Int): Int =
        rateLimitedAdd(limiter, amount) { allowed -> delegate.add(itemStack, allowed) }

    override fun canExchangeItemsWith(other: NetworkedInventory): Boolean {
        // `===`: this exact wrapper. `==`: Nova's equals compares the underlying InvUI inventory,
        // so it also rejects the unwrapped inventory of this same block.
        if (other === this || other == delegate) return false
        if (other is RateLimitedNetworkedInventory) return delegate.canExchangeItemsWith(other.delegate)
        return delegate.canExchangeItemsWith(other)
    }
}

/** Same value as Nova's internal `DefaultItemHolder.ALL_INVENTORY_UUID` (the "all inventories" side option). */
private val ALL_INVENTORY_UUID = UUID(0, 0xA11)

/**
 * Hopper access for a block with a single [VirtualInventory], laid out like Nova's
 * `storedItemHolder(VirtualInventory to ...)`: the inventory itself plus a merged "all" view that every
 * side uses by default. Both views share one rate limiter. Registering the merged view under Nova's
 * merged-inventory UUID keeps side configurations saved by that overload working.
 */
internal class RateLimitedHopperAccess(inventory: VirtualInventory) {
    private val limiter = hopperRateLimiter()
    val single = RateLimitedNetworkedInventory(NetworkedVirtualInventory(inventory), limiter)
    val merged = RateLimitedNetworkedInventory(NetworkedVirtualInventory(inventory), limiter, ALL_INVENTORY_UUID)
}
