package fr.ateastudio.farmersdelight.block

import fr.ateastudio.farmersdelight.NovaFarmersDelight
import fr.ateastudio.farmersdelight.registry.Blocks
import io.papermc.paper.registry.RegistryAccess
import io.papermc.paper.registry.RegistryKey
import io.papermc.paper.registry.keys.tags.ItemTypeTagKeys
import org.bukkit.GameMode
import org.bukkit.Sound
import org.bukkit.SoundCategory
import org.bukkit.block.BlockType
import org.bukkit.event.Event
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemType
import org.bukkit.inventory.meta.Damageable
import org.bukkit.inventory.meta.PotionMeta
import org.bukkit.potion.PotionType
import xyz.xenondevs.nova.context.Context
import xyz.xenondevs.nova.context.intention.BlockPlace
import xyz.xenondevs.nova.initialize.Init
import xyz.xenondevs.nova.initialize.InitStage
import xyz.xenondevs.nova.util.BlockUtils
import xyz.xenondevs.nova.util.above
import xyz.xenondevs.nova.util.damageToolBreakBlock
import xyz.xenondevs.nova.world.block.NovaBlockState
import xyz.xenondevs.nova.world.block.blockType
import xyz.xenondevs.nova.world.block.state.property.DefaultBlockStateProperties
import xyz.xenondevs.nova.world.item.itemType

@Suppress("UnstableApiUsage")
@Init(stage = InitStage.POST_WORLD)
object MuddyFarmlandListener : Listener {
    
    init {
        NovaFarmersDelight.plugin!!.server.pluginManager.registerEvents(this, NovaFarmersDelight.plugin!!)
    }
    
    @EventHandler(ignoreCancelled = true)
    fun onUseWaterBottle(event: PlayerInteractEvent) {
        val player = event.player
        val block = event.clickedBlock
        val tool = event.item
        
        // Check if the clicked block is farmland and the tool is a water bottle
        if (block?.blockType != BlockType.FARMLAND || tool?.itemType != ItemType.POTION) return
        
        // Check if the potion is a water bottle
        val potionMeta = tool.itemMeta as? PotionMeta ?: return
        if (potionMeta.basePotionType != PotionType.WATER) return
        event.setUseInteractedBlock(Event.Result.DENY)
        event.setUseItemInHand(Event.Result.DENY)
        
        player.swingHand(event.hand!!)
        if (player.gameMode != GameMode.CREATIVE) {
            // Remove one water bottle from the player's hand
            tool.amount -= 1
            player.inventory.addItem(ItemType.GLASS_BOTTLE.createItemStack())
        }
        
        // Set up context and play sounds; watering farmland makes it waterlogged straight away.
        val waterloggedState = (Blocks.MUDDY_FARMLAND.get().createBlockData() as NovaBlockState).clone()
        waterloggedState[DefaultBlockStateProperties.WATERLOGGED] = true
        val context = Context.intention(BlockPlace)
            .param(BlockPlace.BLOCK, block)
            .param(BlockPlace.BLOCK_STATE, waterloggedState)
            .param(BlockPlace.BLOCK_PLACE_EFFECTS, true)
            .build()
        block.world.playSound(block.location, Sound.ENTITY_GENERIC_SPLASH, SoundCategory.BLOCKS, 1f, 1f)
        block.world.playSound(block.location, Sound.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1f, 1f)
        BlockUtils.placeBlock(context)
    }
    
    @EventHandler(ignoreCancelled = true)
    fun onUseHoe(event: PlayerInteractEvent) {
        val player = event.player
        val block = event.clickedBlock
        val tool = event.item
        val above = block?.above
        // Check if the clicked block is mud and the tool is a hoe
        if (
            !event.action.isRightClick ||
            block?.blockType != BlockType.MUD ||
            tool?.isEmpty != false ||
            !RegistryAccess.registryAccess().getRegistry(RegistryKey.ITEM)
                .getTagValues(ItemTypeTagKeys.HOES)
                .contains(tool.itemType)
        ) return
        if (above?.isEmpty != true) return
        event.setUseInteractedBlock(Event.Result.DENY)
        event.setUseItemInHand(Event.Result.DENY)
        
        if (player.gameMode != GameMode.CREATIVE) {
            if (tool.itemMeta is Damageable) {
                player.damageToolBreakBlock()
            }
        }
        player.swingHand(event.hand!!)
        
        // Tilled mud starts dry (not waterlogged); rice needs it to be filled with water before growing.
        val context = Context.intention(BlockPlace)
            .param(BlockPlace.BLOCK, block)
            .param(BlockPlace.BLOCK_STATE, Blocks.MUDDY_FARMLAND.get().createBlockData() as NovaBlockState)
            .param(BlockPlace.BLOCK_PLACE_EFFECTS, true)
            .build()
        block.world.playSound(block.location, Sound.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1f, 1f)

        BlockUtils.placeBlock(context)
    }
    
}