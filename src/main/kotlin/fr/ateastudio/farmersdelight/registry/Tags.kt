package fr.ateastudio.farmersdelight.registry

import org.bukkit.block.BlockType

object Tags {
    val TRAY_HEAT_SOURCE = listOf(
        BlockType.LAVA,
        BlockType.CAMPFIRE,
        BlockType.SOUL_CAMPFIRE,
        BlockType.FIRE,
        BlockType.SOUL_FIRE,
    )
    val HEAT_SOURCE = listOf(
        BlockType.MAGMA_BLOCK,
        BlockType.LAVA_CAULDRON,
    )
    // create:chute is an optional (required=false) member and is not available here.
    val HEAT_CONDUCTOR = listOf(
        BlockType.HOPPER,
    )
}