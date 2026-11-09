package fr.ateastudio.farmersdelight.listener

import fr.ateastudio.farmersdelight.NovaFarmersDelight
import fr.ateastudio.farmersdelight.item.isStrongSkilletAttack
import fr.ateastudio.farmersdelight.registry.Items
import fr.ateastudio.farmersdelight.registry.Sounds
import io.papermc.paper.event.player.PrePlayerAttackEntityEvent
import org.bukkit.SoundCategory
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import xyz.xenondevs.nova.initialize.Init
import xyz.xenondevs.nova.initialize.InitStage
import xyz.xenondevs.nova.world.item.itemTypeEntry

/**
 * Plays the skillet's strong/weak impact sound. Uses the pre-attack event because the player's attack
 * strength is already reset by the time the damage event fires.
 */
@Init(stage = InitStage.POST_WORLD)
object SkilletAttackListener : Listener {

    init {
        NovaFarmersDelight.plugin!!.server.pluginManager.registerEvents(this, NovaFarmersDelight.plugin!!)
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    fun onAttack(event: PrePlayerAttackEntityEvent) {
        if (!event.willAttack()) return
        val player = event.player
        if (player.inventory.itemInMainHand.itemTypeEntry != Items.SKILLET) return
        val sound = if (isStrongSkilletAttack(player.attackCooldown)) Sounds.ITEM_SKILLET_ATTACK_STRONG else Sounds.ITEM_SKILLET_ATTACK_WEAK
        player.world.playSound(player.location, sound, SoundCategory.PLAYERS, 1f, 1f)
    }
}
