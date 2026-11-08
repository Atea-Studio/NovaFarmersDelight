package fr.ateastudio.farmersdelight.block.behavior

import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemStack
import xyz.xenondevs.nova.context.Context
import xyz.xenondevs.nova.context.intention.BlockBreak
import xyz.xenondevs.nova.context.intention.BlockPlace
import xyz.xenondevs.nova.util.BlockUtils
import xyz.xenondevs.nova.util.below
import xyz.xenondevs.nova.world.block.NovaBlockState
import xyz.xenondevs.nova.world.block.behavior.BlockBehavior
import kotlin.random.Random


abstract class WildcropBlock : BlockBehavior {
    open val canDropSeed = true
    open val dropProbability = 0.57
    abstract fun seedItem() : ItemStack?
    
    override fun getDrops(pos: Block, state: NovaBlockState, ctx: Context<BlockBreak>): List<ItemStack> {
        val player = ctx[BlockBreak.SOURCE_PLAYER]
        val tool = ctx[BlockBreak.TOOL_ITEM_STACK]
        val seedItem = seedItem()
        if (!ctx[BlockBreak.BLOCK_DROPS] || !canDropSeed || player?.gameMode == GameMode.CREATIVE) {
            return emptyList()
        }
        
        val result = mutableListOf<ItemStack>()
        if (seedItem is ItemStack) {
            // Determine the amount of drops, with possible modification from the Fortune enchantment
            val fortuneLevel = tool.getEnchantmentLevel(Enchantment.FORTUNE)
            val quantity = simulateBinomialDrops(fortuneLevel)
            result.add(seedItem.asQuantity(quantity))
        }
        
        return result
    }
    
    override fun handleNeighborChanged(pos: Block, state: NovaBlockState) {
        if (!canSurvive(state, pos)) {
            breakBlock(pos)
        }
    }
    
    override suspend fun canPlace(pos: Block, state: NovaBlockState, ctx: Context<BlockPlace>): Boolean {
        return mayPlaceOn(pos.below, state) && pos.isEmpty
    }
    
    protected open fun canSurvive(state: NovaBlockState, pos: Block): Boolean {
        val blockBelow = pos.below
        return mayPlaceOn(blockBelow, state)
    }
    
    protected open fun mayPlaceOn(pos: Block, state: NovaBlockState): Boolean {
        return  pos.type == Material.ROOTED_DIRT ||
                pos.type == Material.COARSE_DIRT ||
                pos.type == Material.GRASS_BLOCK ||
                pos.type == Material.DIRT
    }
    
    protected fun simulateBinomialDrops(fortuneLevel: Int): Int {
        val trials = 3 + fortuneLevel // n trials: 3 base trials + 1 for each level of Fortune
        var seedDrops = 0
        
        // Perform binomial trials
        repeat(trials) {
            if (Random.nextDouble(1.0) < dropProbability) {
                seedDrops++
            }
        }
        
        return seedDrops.coerceAtLeast(1) // Ensure at least 1 seed drops
    }
    
    protected fun breakBlock(position: Block){
        val context = Context.intention(BlockBreak)
            .param(BlockBreak.BLOCK, position)
            .param(BlockBreak.BLOCK_BREAK_EFFECTS, true)
            .build()
        
        BlockUtils.breakBlockNaturally(context)
    }
}
