package com.example

import xyz.xenondevs.nova.addon.Addon

object ExampleAddon : Addon() {
    
    override fun init() {
        // Called when the addon is initialized.
    }
    
    override fun onEnable() {
        // Called when the addon is enabled.
    }
    
    override fun onDisable() {
        // Called when the addon is disabled.
    }
    
}