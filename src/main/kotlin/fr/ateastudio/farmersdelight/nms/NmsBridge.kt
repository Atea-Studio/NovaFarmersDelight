package fr.ateastudio.farmersdelight.nms

import org.bukkit.block.Block
import org.bukkit.util.BoundingBox

/**
 * Every Minecraft-internal (NMS/CraftBukkit) operation the addon needs, for which neither Paper nor Nova
 * offers a public API. Gameplay code only depends on this interface; when a Minecraft/Paper update breaks
 * internals, only [PaperNmsBridge] (plus the comparator mixin and world-generation features in this
 * package) has to be adapted.
 */
interface NmsBridge {

    /**
     * Whether the selection (outline) shape of [block] intersects [box], given in block-local coordinates
     * (0..1). Paper only exposes collision shapes, which differ for blocks such as signs.
     */
    fun outlineIntersects(block: Block, box: BoundingBox): Boolean

    /** Makes comparators next to [block] (directly or through one solid block) re-read their input signal. */
    fun updateComparatorOutput(block: Block)
}

/** The active [NmsBridge] implementation. */
object Nms : NmsBridge by PaperNmsBridge
