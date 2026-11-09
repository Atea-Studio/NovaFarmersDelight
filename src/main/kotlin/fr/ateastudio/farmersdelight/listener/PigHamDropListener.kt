package fr.ateastudio.farmersdelight.listener

import fr.ateastudio.farmersdelight.NovaFarmersDelight
import xyz.xenondevs.nova.world.item.createItemStack

import fr.ateastudio.farmersdelight.registry.Items
import fr.ateastudio.farmersdelight.util.isKnife
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.EntityType
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDeathEvent
import xyz.xenondevs.nova.initialize.Init
import xyz.xenondevs.nova.initialize.InitStage
import kotlin.random.Random

@Init(stage = InitStage.POST_WORLD)
object PigHamDropListener : Listener {
    
    init {
        NovaFarmersDelight.plugin!!.server.pluginManager.registerEvents(this, NovaFarmersDelight.plugin!!)
    }
    
    @EventHandler
    fun onPigKilled(event: EntityDeathEvent) {
        val pig = event.entity
        if (pig.type != EntityType.PIG && pig.type != EntityType.PIGLIN) return
        
        if (pig.fireTicks > 0) return // Pig was on fire
        
        val killer = pig.killer ?: return
        
        val itemInHand = killer.inventory.itemInMainHand
        
        // Check if item is tagged as "knife"
        if (!itemInHand.isKnife()) return
        
        // Calculate chance: base 0.5 + looting_bonus (0.1 * level)
        val lootingLevel = itemInHand.getEnchantmentLevel(Enchantment.LOOTING)
        val chance = 0.5 + (lootingLevel * 0.1)
        
        if (Random.nextDouble() <= chance) {
            val ham = Items.HAM.createItemStack() // Or your custom item here
            event.drops.add(ham)
        }
    }
    
}