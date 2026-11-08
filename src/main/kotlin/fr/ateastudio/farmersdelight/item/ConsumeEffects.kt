package fr.ateastudio.farmersdelight.item

import org.bukkit.attribute.Attribute
import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerItemConsumeEvent
import org.bukkit.inventory.ItemStack
import xyz.xenondevs.nova.world.item.behavior.ItemBehavior

/** Melon juice also heals 2 health (1 heart) when drunk, capped at max health. */
object MelonJuiceHealing : ItemBehavior {
    override fun handleConsume(player: Player, itemStack: ItemStack, event: PlayerItemConsumeEvent) {
        if (event.isCancelled) return
        val maxHealth = player.getAttribute(Attribute.MAX_HEALTH)?.value ?: player.health
        player.health = (player.health + 2.0).coerceAtMost(maxHealth)
    }
}

/** The melon popsicle extinguishes the eater. */
object ExtinguishOnConsume : ItemBehavior {
    override fun handleConsume(player: Player, itemStack: ItemStack, event: PlayerItemConsumeEvent) {
        if (event.isCancelled) return
        player.fireTicks = 0
    }
}
