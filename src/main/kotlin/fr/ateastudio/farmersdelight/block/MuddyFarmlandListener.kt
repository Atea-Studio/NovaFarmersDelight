package fr.ateastudio.farmersdelight.block

import fr.ateastudio.farmersdelight.registry.Blocks
import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.SoundCategory
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.meta.Damageable
import org.bukkit.inventory.meta.PotionMeta
import org.bukkit.potion.PotionType
import xyz.xenondevs.nova.context.Context
import xyz.xenondevs.nova.context.intention.DefaultContextIntentions
import xyz.xenondevs.nova.context.param.DefaultContextParamTypes
import xyz.xenondevs.nova.initialize.Init
import xyz.xenondevs.nova.initialize.InitStage
import xyz.xenondevs.nova.util.BlockUtils
import xyz.xenondevs.nova.util.above
import xyz.xenondevs.nova.util.damageToolBreakBlock
import xyz.xenondevs.nova.util.item.toItemStack
import xyz.xenondevs.nova.util.playSoundNearby
import xyz.xenondevs.nova.world.item.tool.ToolCategory
import xyz.xenondevs.nova.world.item.tool.VanillaToolCategories
import xyz.xenondevs.nova.world.pos

@Init(stage = InitStage.POST_WORLD)
object MuddyFarmlandListener : Listener {
    
    @EventHandler
    fun onUseWaterBottle(event: PlayerInteractEvent) {
        val player = event.player
        val block = event.clickedBlock
        val tool = event.item
        
        // Check if the clicked block is farmland and the tool is a water bottle
        if (block?.type != Material.FARMLAND || tool?.type != Material.POTION) return
        
        // Check if the potion is a water bottle
        val potionMeta = tool.itemMeta as? PotionMeta ?: return
        if (potionMeta.basePotionType != PotionType.WATER) return
        
        player.swingHand(event.hand!!)
        if (player.gameMode != GameMode.CREATIVE) {
            // Remove one water bottle from the player's hand
            tool.amount -= 1
            player.inventory.addItem(Material.GLASS_BOTTLE.toItemStack())
        }
        
        // Set up context and play sounds
        val context = Context.intention(DefaultContextIntentions.BlockPlace)
            .param(DefaultContextParamTypes.BLOCK_POS, block.pos)
            .param(DefaultContextParamTypes.BLOCK_TYPE_NOVA, Blocks.MUDDY_FARMLAND)
            .param(DefaultContextParamTypes.BLOCK_PLACE_EFFECTS, true)
            .build()
        block.location.playSoundNearby(Sound.ENTITY_GENERIC_SPLASH, SoundCategory.BLOCKS, 1f, 1f)
        block.location.playSoundNearby(Sound.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1f, 1f)
        BlockUtils.placeBlock(context)
    }
    
    @EventHandler
    fun onUseHoe(event: PlayerInteractEvent) {
        val player = event.player
        val block = event.clickedBlock
        val tool = event.item
        val above = block?.above
        // Check if the clicked block is mud and the tool is a hoe
        if (!event.action.isRightClick || block?.type != Material.MUD || tool?.isEmpty != false || !ToolCategory.ofItem(tool).contains(VanillaToolCategories.HOE)) return
        if (above?.isEmpty != true) return
        
        if (player.gameMode != GameMode.CREATIVE) {
            if (tool.itemMeta is Damageable) {
                player.damageToolBreakBlock()
            }
        }
        player.swingHand(event.hand!!)
        
        // Set up context and play sounds
        val context = Context.intention(DefaultContextIntentions.BlockPlace)
            .param(DefaultContextParamTypes.BLOCK_POS, block.pos)
            .param(DefaultContextParamTypes.BLOCK_TYPE_NOVA, Blocks.MUDDY_FARMLAND)
            .param(DefaultContextParamTypes.BLOCK_PLACE_EFFECTS, true)
            .build()
        block.location.playSoundNearby(Sound.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1f, 1f)
        BlockUtils.placeBlock(context)
    }
    
}