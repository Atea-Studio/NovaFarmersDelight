package fr.ateastudio.farmersdelight.util

import org.bukkit.Bukkit
import xyz.xenondevs.nova.util.runTask

/**
 * Runs [action] on the server thread: immediately if already there, otherwise on the next tick.
 * Nova ticks item networks in parallel worker threads, so inventory update handlers of networked
 * tile entities must not touch the world (entities, blocks, sounds) directly.
 */
fun runOnMainThread(action: () -> Unit) {
    if (Bukkit.isPrimaryThread()) action() else runTask(action)
}
