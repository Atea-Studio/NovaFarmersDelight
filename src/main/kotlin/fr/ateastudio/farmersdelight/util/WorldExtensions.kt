package fr.ateastudio.farmersdelight.util

import org.bukkit.World
import kotlin.random.Random

/**
 * Shared, thread-safe random source for gameplay rolls and effects. A new generator seeded with the
 * world seed would return the same value on every call, so chance-based sounds/particles never vary.
 */
@Suppress("UnusedReceiverParameter")
fun World.random(): Random = Random.Default