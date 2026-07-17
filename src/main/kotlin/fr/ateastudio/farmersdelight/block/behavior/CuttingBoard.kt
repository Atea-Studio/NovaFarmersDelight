package fr.ateastudio.farmersdelight.block.behavior

import fr.ateastudio.farmersdelight.recipe.cuttingboard.CuttingBoardRecipe
import fr.ateastudio.farmersdelight.registry.RecipeTypes
import fr.ateastudio.farmersdelight.registry.Sounds
import fr.ateastudio.farmersdelight.util.Logger
import fr.ateastudio.farmersdelight.util.random
import fr.ateastudio.farmersdelight.util.safeGive
import net.kyori.adventure.text.Component
import org.bukkit.GameMode
import org.bukkit.Location
import org.bukkit.Sound
import org.bukkit.SoundCategory
import org.bukkit.block.BlockFace
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.ItemDisplay
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.jline.utils.Log
import org.joml.Matrix4f
import xyz.xenondevs.nova.context.Context
import xyz.xenondevs.nova.context.intention.BlockBreak
import xyz.xenondevs.nova.context.intention.BlockInteract
import xyz.xenondevs.nova.util.center
import xyz.xenondevs.nova.util.playSoundNearby
import xyz.xenondevs.nova.world.BlockPos
import xyz.xenondevs.nova.world.InteractionResult
import xyz.xenondevs.nova.world.block.behavior.BlockBehavior
import xyz.xenondevs.nova.world.block.state.NovaBlockState
import xyz.xenondevs.nova.world.block.state.property.DefaultBlockStateProperties
import xyz.xenondevs.nova.world.item.recipe.RecipeManager
import xyz.xenondevs.nova.world.item.tool.ToolCategory
import xyz.xenondevs.nova.world.item.tool.VanillaToolCategories


object CuttingBoard : BlockBehavior {
    
    override fun use(pos: BlockPos, state: NovaBlockState, ctx: Context<BlockInteract>): InteractionResult {
        val player = ctx[BlockInteract.SOURCE_PLAYER]
        val tool = player?.inventory?.itemInMainHand ?: ItemStack.empty()
        val facing = state[DefaultBlockStateProperties.FACING]
        val existingDisplay = findItemDisplay(pos)
        
        Logger.info("CuttingBoard: use() called by player ${player?.name} with item ${tool?.type} at position $pos")
        Logger.info("CuttingBoard: existingDisplay = $existingDisplay, facing = $facing")
        
        // If an ItemDisplay already exists, return without spawning a new one
        if (existingDisplay != null) {
            if (tool.isEmpty) {
                Logger.info("CuttingBoard: tool is empty")
                retrieveItem(pos, player)
                return InteractionResult.Pass
            }
            else if (player != null) {
                Logger.info("CuttingBoard: tool is not empty, processing recipe with tool ${tool.type}")
                doRecipe(pos, player, tool, facing)
                return InteractionResult.Pass
            }
        }
        else {
            Logger.info("CuttingBoard: no existing item display found")
            if (!tool.isEmpty) {
                Logger.info("CuttingBoard: placing item on cutting board")
                placeItem(tool.asOne(), pos, facing)
                if (player != null && player.gameMode != GameMode.CREATIVE) {
                    tool.subtract()
                    Logger.info("CuttingBoard: tool ${tool.type} used, new amount: ${tool.amount}")
                }
                return InteractionResult.Pass
            }
        }
        return InteractionResult.Fail
        
    }
    
    override fun handleBreak(pos: BlockPos, state: NovaBlockState, ctx: Context<BlockBreak>) {
        retrieveItem(pos)
    }
    
    private fun doRecipe(pos: BlockPos, player: Player, tool: ItemStack, blockFace: BlockFace?) {
        // Check for an ItemDisplay above the CuttingBoard and remove it
        val itemDisplay = findItemDisplay(pos)
        if (itemDisplay != null) {
            val input = itemDisplay.itemStack
            val fortuneLevel = tool.getEnchantmentLevel(Enchantment.FORTUNE)
            val recipes = getMatchingRecipes(input)
            if (recipes.isEmpty()) {
                player.sendMessage(Component.translatable("farmersdelight.block.cutting_board.invalid_item"))
                return
            }
            val matchingRecipe = recipes.firstOrNull { it.tool.test(tool)}
            if (matchingRecipe == null) {
                player.sendMessage(Component.translatable("farmersdelight.block.cutting_board.invalid_tool"))
                return
            }
            
            val results = matchingRecipe.rollResults(pos.world.random(),fortuneLevel)
            for (resultStack in results) {
                val loc = when (blockFace) {
                    BlockFace.NORTH -> getDisplayLocation(pos).add(0.0,-0.3,-0.5)
                    BlockFace.EAST -> getDisplayLocation(pos).add(0.5,-0.3,0.0)
                    BlockFace.SOUTH -> getDisplayLocation(pos).add(0.0,-0.3,0.5)
                    BlockFace.WEST -> getDisplayLocation(pos).add(-0.5,-0.3,0.0)
                    else -> getDisplayLocation(pos)
                }
                pos.world.dropItem(loc, resultStack)
            }
            if (player.gameMode != GameMode.CREATIVE) {
                tool.damage(1, player)
            }
            val sound = matchingRecipe.soundEvent.ifEmpty { null }
            playProcessingSound(pos, sound, tool)
            itemDisplay.remove() // Remove the item display entity
        }
    }
    
    private fun playProcessingSound(pos: BlockPos, sound: String?, tool: ItemStack) {
        if (sound != null) {
            pos.playSound(sound, SoundCategory.BLOCKS, 1f, 1f)
        }
        else if (ToolCategory.ofItem(tool).contains(VanillaToolCategories.SHEARS)) {
            pos.location.playSoundNearby(Sound.ENTITY_SHEEP_SHEAR, SoundCategory.BLOCKS, 1f, 1f)
        }
        else if (ToolCategory.ofItem(tool).contains(VanillaToolCategories.SWORD) || ToolCategory.ofItem(tool).any { it.id.value() == "knives" || it.id.value() == "knife" }) {
            pos.playSound(Sounds.BLOCK_CUTTING_BOARD_KNIFE, SoundCategory.BLOCKS, 0.8f, 1f)
        }
        else if (ToolCategory.ofItem(tool).contains(VanillaToolCategories.AXE)) {
            pos.location.playSoundNearby(Sound.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1f, 0.8f)
        }
        else if (ToolCategory.ofItem(tool).contains(VanillaToolCategories.PICKAXE)) {
            pos.location.playSoundNearby(Sound.BLOCK_STONE_BREAK, SoundCategory.BLOCKS, 1f, 0.8f)
        }
        else if (ToolCategory.ofItem(tool).contains(VanillaToolCategories.HOE)) {
            pos.location.playSoundNearby(Sound.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1f, 0.8f)
        }
        else if (ToolCategory.ofItem(tool).contains(VanillaToolCategories.SHOVEL)) {
            pos.location.playSoundNearby(Sound.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1f, 0.8f)
        }
        else {
            pos.location.playSoundNearby(Sound.BLOCK_WOOD_BREAK, SoundCategory.BLOCKS, 1f, 0.8f)
        }
    }
    
    private fun getMatchingRecipes(input: ItemStack): List<CuttingBoardRecipe> {
        return RecipeManager.novaRecipes[RecipeTypes.CUTTING_BOARD]?.values?.asSequence()
            ?.map { it as CuttingBoardRecipe }
            ?.filter { it.input.test(input)
            }?.toList() ?: listOf()
    }
    
    private fun placeItem(item: ItemStack, pos: BlockPos, facing: BlockFace?) {
        val displayPos = getDisplayLocation(pos)
        val world = pos.world
        val transformation = Matrix4f(0.6f,0f,0f,0.5f,0f,-0f,-0.6f,0.8f,0f,0.6f,-0f,0.5f,0f,0f,0f,1f)
        
        // Additional rotation around Y-axis based on block face direction
        val facingRotation = when (facing) {
            BlockFace.NORTH -> transformation.rotateLocalY(Math.toRadians(180.0).toFloat())
            BlockFace.SOUTH -> transformation
            BlockFace.WEST -> transformation.rotateLocalY(Math.toRadians(-90.0).toFloat())
            BlockFace.EAST -> transformation.rotateLocalY(Math.toRadians(90.0).toFloat())
            else -> transformation
        }
        
        // Spawn a new ItemDisplay entity
        world.spawn(displayPos, ItemDisplay::class.java) { entity: ItemDisplay ->
            // customize the entity!
            entity.addScoreboardTag("farmingsdelight:cuttingboard")
            entity.isPersistent = true
            entity.setItemStack(item)
            entity.itemDisplayTransform = ItemDisplay.ItemDisplayTransform.FIXED
            entity.setTransformationMatrix(facingRotation)
        }
        pos.playSound(Sound.BLOCK_WOOD_PLACE, 0.8f, 1f)
    }
    
    private fun retrieveItem(pos: BlockPos, player: Player? = null) {
        val itemDisplay = findItemDisplay(pos)
        if (itemDisplay != null) {
            val itemStack = itemDisplay.itemStack
            itemDisplay.remove() // Remove the item display entity
            if (player != null) {
                player.safeGive(itemStack)
                pos.playSound(Sound.BLOCK_WOOD_BREAK, 0.8f, 1f)
            }
            else {
                pos.world.dropItem(getDisplayLocation(pos), itemStack) // Drop the item at the block's position
            }
        }
    }
    
    private fun findItemDisplay(pos: BlockPos) : ItemDisplay? {
        val world = pos.world
        val displayPos = getDisplayLocation(pos)
        return world.getNearbyEntities(displayPos, 0.5, 0.5, 0.5).filter { it.scoreboardTags.contains("farmingsdelight:cuttingboard") }
            .filterIsInstance<ItemDisplay>()
            .firstOrNull()
    }
    
    private fun getDisplayLocation(pos: BlockPos) : Location {
        return pos.location.center().add(0.0,0.05,0.0)
        
    }
}