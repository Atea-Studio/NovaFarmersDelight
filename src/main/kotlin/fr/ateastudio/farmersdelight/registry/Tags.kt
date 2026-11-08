package fr.ateastudio.farmersdelight.registry

import org.bukkit.Material

object Tags {
    val TRAY_HEAT_SOURCE = listOf(
        Material.LAVA,
        Material.CAMPFIRE,
        Material.SOUL_CAMPFIRE,
        Material.FIRE,
        Material.SOUL_FIRE,
    )
    val HEAT_SOURCE = listOf(
        Material.MAGMA_BLOCK,
        Material.LAVA_CAULDRON,
    )
    // create:chute is an optional (required=false) member and is not available here.
    val HEAT_CONDUCTOR = listOf(
        Material.HOPPER,
    )
}