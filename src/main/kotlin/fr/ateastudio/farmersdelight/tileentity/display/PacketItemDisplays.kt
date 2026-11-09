package fr.ateastudio.farmersdelight.tileentity.display

import org.bukkit.Location
import org.bukkit.entity.ItemDisplay
import org.bukkit.inventory.ItemStack
import org.joml.Matrix4fc
import org.joml.Quaternionf
import org.joml.Vector3f
import xyz.xenondevs.nova.packetentity.ItemDisplayMetadata
import xyz.xenondevs.nova.packetentity.PacketEntity
import xyz.xenondevs.nova.packetentity.packetItemDisplay

/** One displayed item: where it is, what it shows and its (rotation + uniform scale) transformation. */
internal data class ItemDisplaySpec(val location: Location, val item: ItemStack, val transformation: Matrix4fc)

/**
 * Packet-only item displays owned by a tile entity, the way Nova's own addons render block contents:
 * they exist only client-side, are never saved to the world and cannot be orphaned by crashes.
 * Show them from `handleEnable`/content changes and [clear] them in `handleDisable`. Main thread only.
 */
internal class PacketItemDisplays {

    private val displays = ArrayList<PacketEntity<ItemDisplayMetadata>>()
    private var shownSpecs: List<ItemDisplaySpec> = emptyList()

    /**
     * Replaces the currently shown displays with [specs]. Nothing is resent when they are identical to what is
     * already shown (e.g. the stored amount changed without changing the number of displayed models), which
     * avoids flickering.
     */
    fun show(specs: List<ItemDisplaySpec>) {
        val normalized = specs.map { it.copy(item = it.item.asOne()) }
        if (normalized == shownSpecs && displays.size == normalized.size) return
        despawnAll()
        shownSpecs = normalized
        for (spec in normalized) {
            // Same affine decomposition Bukkit applies in ItemDisplay#setTransformationMatrix. The matrices carry a
            // scale, so the rotation must be extracted with getUnnormalizedRotation (getNormalizedRotation assumes
            // unit-length axes and would return a non-unit quaternion that tilts and shrinks the item).
            val translation = spec.transformation.getTranslation(Vector3f())
            val rotation = spec.transformation.getUnnormalizedRotation(Quaternionf()).normalize()
            val scale = spec.transformation.getScale(Vector3f())
            val display = packetItemDisplay {
                location by spec.location.clone()
                metadata {
                    itemStack by spec.item.asOne()
                    itemDisplay by ItemDisplay.ItemDisplayTransform.FIXED
                    this.translation by translation
                    leftRotation by rotation
                    this.scale by scale
                }
            }
            display.spawn()
            displays += display
        }
    }

    fun clear() {
        despawnAll()
        shownSpecs = emptyList()
    }

    private fun despawnAll() {
        displays.forEach { it.despawn() }
        displays.clear()
    }
}
