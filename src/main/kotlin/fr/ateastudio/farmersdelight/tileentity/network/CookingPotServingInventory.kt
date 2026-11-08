package fr.ateastudio.farmersdelight.tileentity.network

import org.bukkit.inventory.ItemStack
import xyz.xenondevs.invui.inventory.VirtualInventory
import xyz.xenondevs.invui.inventory.event.UpdateReason
import xyz.xenondevs.nova.world.block.tileentity.network.type.item.inventory.NetworkedInventory
import xyz.xenondevs.nova.world.block.tileentity.network.type.item.inventory.NetworkedVirtualInventory
import java.util.*

/**
 * Automation view of a cooking pot from every face except the top: items are only ever inserted into the
 * serving-container slot and only ever extracted from the output slot. The meal reservoir is never exposed.
 *
 * Slot 0 maps to the container input (never extractable), slot 1 to the output.
 */
internal class CookingPotServingInventory(
    private val containerInventory: VirtualInventory,
    private val outputInventory: VirtualInventory
) : NetworkedInventory {

    override val uuid: UUID = SERVING_SIDE_UUID

    override val size: Int = 2

    // Containers enter at vanilla hopper speed instead of a whole stack per network tick.
    private val limiter = hopperRateLimiter()

    override fun add(itemStack: ItemStack, amount: Int): Int =
        rateLimitedAdd(limiter, amount) { allowed ->
            containerInventory.addItem(NetworkedVirtualInventory.UPDATE_REASON, itemStack.clone().also { it.amount = allowed })
        }

    override fun canTake(slot: Int, amount: Int): Boolean {
        if (slot != OUTPUT_SLOT) return false
        val output = outputInventory.getItem(0) ?: return false
        return output.amount >= amount
    }

    override fun take(slot: Int, amount: Int) {
        if (slot != OUTPUT_SLOT) return
        val previous = outputInventory.getItem(0) ?: return
        outputInventory.addItemAmount(UpdateReason.SUPPRESSED, 0, -amount)
        outputInventory.callPostUpdateEvent(NetworkedVirtualInventory.UPDATE_REASON, 0, previous, outputInventory.getItem(0))
    }

    override fun isFull(): Boolean = containerInventory.isFull

    override fun isEmpty(): Boolean = outputInventory.isEmpty

    override fun copyContents(destination: Array<ItemStack>) {
        destination[0] = ItemStack.empty()
        destination[OUTPUT_SLOT] = outputInventory.getItem(0) ?: ItemStack.empty()
    }

    override fun canExchangeItemsWith(other: NetworkedInventory): Boolean {
        if (other == this) return false
        val otherVirtual = (other as? RateLimitedNetworkedInventory)?.delegate ?: other
        if (otherVirtual is NetworkedVirtualInventory)
            return otherVirtual.virtualInventory != containerInventory && otherVirtual.virtualInventory != outputInventory
        return true
    }

    private companion object {
        const val OUTPUT_SLOT = 1
        val SERVING_SIDE_UUID: UUID = UUID.nameUUIDFromBytes("farmersdelight:cooking_pot_serving_side".toByteArray())
    }
}
