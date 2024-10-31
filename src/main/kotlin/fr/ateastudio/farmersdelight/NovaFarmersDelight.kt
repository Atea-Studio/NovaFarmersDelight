package fr.ateastudio.farmersdelight

import fr.ateastudio.farmersdelight.block.MuddyFarmlandListener
import xyz.xenondevs.nova.addon.Addon
import xyz.xenondevs.nova.util.registerEvents

object NovaFarmersDelight : Addon() {
    
    override fun init() {
        MuddyFarmlandListener.registerEvents()
    }
    
    override fun onEnable() {
        // Called when the addon is enabled.
    }
    
    override fun onDisable() {
        // Called when the addon is disabled.
    }
    
}