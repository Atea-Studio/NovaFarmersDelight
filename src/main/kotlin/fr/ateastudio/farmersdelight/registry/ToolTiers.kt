package fr.ateastudio.farmersdelight.registry

import net.kyori.adventure.key.Key

// Custom tool tiers are no longer registered types in Nova 0.25; they are identified
// by a Key referencing a corresponding "incorrect_for_<tier>_tool" block tag (see the
// migration guide's "Custom Tool Tiers" section).
@Suppress("unused")
object ToolTiers {
    val FLINT: Key = Key.key("farmersdelight", "flint")
}
