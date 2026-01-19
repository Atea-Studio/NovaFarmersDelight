package fr.ateastudio.farmersdelight

import xyz.xenondevs.nova.initialize.Init
import xyz.xenondevs.nova.initialize.InitStage

/**
 * Texture icon registration for Waila tooltips in Nova 0.21+
 * 
 * In Nova 0.21, texture icon registration is handled automatically through
 * item model registration and doesn't require explicit PackTask setup.
 * The TextureIconContent is managed by the resource pack builder internally.
 */
@Suppress("unused")
@Init(stage = InitStage.PRE_PACK)
object WailaAssets {
    // Texture icons are now registered automatically when items with
    // texture models are defined in the item registry.
}