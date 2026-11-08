package fr.ateastudio.farmersdelight.block.behavior

import fr.ateastudio.farmersdelight.block.BlockStateProperties
import fr.ateastudio.farmersdelight.util.getCraftingRemainingItem
import fr.ateastudio.farmersdelight.util.hasCraftingRemainingItem
import net.kyori.adventure.text.Component
import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.SoundCategory
import org.bukkit.entity.Player
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.ItemStack
import xyz.xenondevs.nova.context.Context
import xyz.xenondevs.nova.context.intention.BlockBreak
import xyz.xenondevs.nova.context.intention.BlockInteract
import xyz.xenondevs.nova.context.intention.BlockPlace
import org.bukkit.block.Block
import xyz.xenondevs.nova.integration.customitems.CustomItemServiceManager.removeBlock
import xyz.xenondevs.nova.util.BlockUtils.breakBlockNaturally
import xyz.xenondevs.nova.util.addToInventoryOrDrop
import xyz.xenondevs.nova.world.InteractionResult
import xyz.xenondevs.nova.world.block.behavior.BlockBehavior
import xyz.xenondevs.nova.world.block.NovaBlockState
import xyz.xenondevs.nova.world.block.novaBlockState

abstract class FeastBlock(private val hasLeftovers: Boolean) : BlockBehavior {
    protected open val maxServings : Int
        get() = 4
    
    
    abstract fun getServingItem(state: NovaBlockState): ItemStack
    
    /**
     * The item stack representing this feast block itself (a full/undamaged serving),
     * e.g. the item used to place this block. Overridden per feast block since Nova 0.25
     * no longer exposes a public way to resolve a block's associated item from a [NovaBlockState].
     */
    abstract fun getFullBlockItem(): ItemStack
    
    override fun handlePlace(pos: Block, state: NovaBlockState, ctx: Context<BlockPlace>) {
        state[BlockStateProperties.SERVINGS] = maxServings
        pos.blockData = state
    }
    
    override fun use(pos: Block, state: NovaBlockState, ctx: Context<BlockInteract>): InteractionResult {
        val player = ctx[BlockInteract.SOURCE_PLAYER] ?: return InteractionResult.Fail
        val hand = ctx[BlockInteract.HELD_HAND] ?: return InteractionResult.Fail
        return takeServing(pos, state, player, hand)
    }
    
    override fun useItemOn(pos: Block, state: NovaBlockState, ctx: Context<BlockInteract>): InteractionResult {
        val player = ctx[BlockInteract.SOURCE_PLAYER] ?: return InteractionResult.Fail
        val hand = ctx[BlockInteract.HELD_HAND] ?: return InteractionResult.Fail
        return takeServing(pos, state, player, hand)
    }
    
    override fun getDrops(pos: Block, state: NovaBlockState, ctx: Context<BlockBreak>): List<ItemStack> {
        if (!ctx[BlockBreak.BLOCK_DROPS] || ctx[BlockBreak.SOURCE_PLAYER]?.gameMode == GameMode.CREATIVE) {
            return emptyList()
        }
        
        val servings = state[BlockStateProperties.SERVINGS] ?: 0
        if (servings != maxServings) {
            if (hasLeftovers) {
                return listOf(ItemStack(Material.BOWL))
            }
            return emptyList()
        } else {
            val fullItem = getFullBlockItem()
            return if (fullItem.isEmpty) emptyList() else listOf(fullItem)
        }
    }
    
    private fun takeServing(pos: Block, state: NovaBlockState, player: Player, hand: EquipmentSlot): InteractionResult {
        val servings = state[BlockStateProperties.SERVINGS] ?: 0
        if (servings == 0) {
            pos.world.playSound(pos.location, Sound.BLOCK_WOOL_BREAK, SoundCategory.PLAYERS, 0.8f, 0.8f)
            val ctx = Context.intention(BlockBreak)
                .param(BlockBreak.BLOCK, pos)
                .param(BlockBreak.BLOCK_BREAK_EFFECTS, true)
                .param(BlockBreak.SOURCE_PLAYER, player)
                .param(BlockBreak.BLOCK_DROPS, true)
                .build()
            breakBlockNaturally(ctx)
            return InteractionResult.Success()
        }
        
        val serving = getServingItem(state)
        val heldStack = player.inventory.getItem(hand)
        if (servings > 0) {
            if (!serving.hasCraftingRemainingItem() || heldStack.isSimilar(serving.getCraftingRemainingItem())) {
                state[BlockStateProperties.SERVINGS] = servings - 1
                pos.blockData = state
                    if (player.gameMode != GameMode.CREATIVE && serving.hasCraftingRemainingItem()) {
                        player.inventory.setItem(hand, heldStack.asQuantity(heldStack.amount - 1))
                }
                player.addToInventoryOrDrop(serving)
                val posStateServings = pos.novaBlockState?.get(BlockStateProperties.SERVINGS) ?: 0
                if (posStateServings == 0 && !hasLeftovers) {
                    removeBlock(pos, false)
                }
                pos.world.playSound(pos.location, Sound.ITEM_ARMOR_EQUIP_GENERIC, SoundCategory.BLOCKS, 1.0f, 1.0f)
                return InteractionResult.Success()
            } else {
                player.sendMessage(Component.translatable("farmersdelight.block.feast.use_container", serving.getCraftingRemainingItem().effectiveName()))
            }
        }
        return InteractionResult.Pass
    }
    
}