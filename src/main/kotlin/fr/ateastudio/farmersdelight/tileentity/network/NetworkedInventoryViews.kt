package fr.ateastudio.farmersdelight.tileentity.network

import xyz.xenondevs.invui.inventory.VirtualInventory
import xyz.xenondevs.nova.world.block.tileentity.network.type.item.inventory.NetworkedInvUIInventory
import xyz.xenondevs.nova.world.block.tileentity.network.type.item.inventory.NetworkedInventory
import xyz.xenondevs.nova.world.block.tileentity.network.type.item.inventory.NetworkedVirtualInventory
import java.util.UUID

/**
 * Direct views of one inventory, preserving both UUIDs used by Nova's storedItemHolder(VirtualInventory) overload.
 */
internal class NetworkedInventoryViews(inventory: VirtualInventory) {
    val single: NetworkedInventory = NetworkedVirtualInventory(inventory)
    val all: NetworkedInventory = NetworkedInvUIInventory(ALL_INVENTORY_UUID, inventory, NetworkedVirtualInventory.UPDATE_REASON)

    private companion object {
        val ALL_INVENTORY_UUID = UUID(0, 0xA11)
    }
}
