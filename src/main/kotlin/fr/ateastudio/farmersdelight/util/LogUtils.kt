package fr.ateastudio.farmersdelight.util

import org.bukkit.Bukkit

private const val DEBUG = true

fun LogError(message: String) {
        Bukkit.getServer().consoleSender.sendMessage("[FD-ERROR] " + message)
}
fun LogWarning(message: String) {
        Bukkit.getServer().consoleSender.sendMessage("[FD-WARN] " + message)
}
fun Log(message: String) {
        Bukkit.getServer().consoleSender.sendMessage("[FD-INFO] " + message)
}
fun LogDebug(message: String) {
        if (DEBUG) {
                Bukkit.getServer().consoleSender.sendMessage("[FD-DEBUG] " + message)
        }
}