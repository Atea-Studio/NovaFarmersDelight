package fr.ateastudio.farmersdelight.block.behavior

import fr.ateastudio.farmersdelight.registry.Blocks
import io.papermc.paper.registry.RegistryAccess
import io.papermc.paper.registry.RegistryKey
import io.papermc.paper.registry.keys.tags.BlockTypeTagKeys
import org.bukkit.block.Block
import xyz.xenondevs.nova.context.Context
import xyz.xenondevs.nova.context.intention.BlockBreak
import xyz.xenondevs.nova.context.intention.BlockPlace
import xyz.xenondevs.nova.integration.protection.ProtectionManager
import xyz.xenondevs.nova.util.BlockUtils.breakBlockNaturally
import xyz.xenondevs.nova.util.BlockUtils.placeBlock
import xyz.xenondevs.nova.world.block.NovaBlockState
import xyz.xenondevs.nova.world.block.behavior.BlockBehavior
import xyz.xenondevs.nova.world.block.blockType
import xyz.xenondevs.nova.world.block.novaBlockState
import xyz.xenondevs.nova.world.block.state.property.DefaultBlockStateProperties

private val REPLACEABLE_BLOCK_TYPES by lazy {
    RegistryAccess.registryAccess().getRegistry(RegistryKey.BLOCK).getTagValues(BlockTypeTagKeys.REPLACEABLE).toSet()
}

object TatamiMatHead : BlockBehavior {
    
    override fun handlePlace(block: Block, state: NovaBlockState, ctx: Context<BlockPlace>) {
        val face = state[DefaultBlockStateProperties.FACING_HORIZONTAL]!!
        val footPos = block.getRelative(face.oppositeFace)
        val footMat = Blocks.FULL_TATAMI_MAT_FOOT.get().createBlockData() as NovaBlockState
        footMat[DefaultBlockStateProperties.FACING_HORIZONTAL] = face.oppositeFace
        val contextFoot = Context.intention(BlockPlace)
            .param(BlockPlace.BLOCK, footPos)
            .param(BlockPlace.BLOCK_STATE, footMat)
            .build()
        placeBlock(contextFoot)
    }
    
    override suspend fun canPlace(block: Block, data: NovaBlockState, ctx: Context<BlockPlace>): Boolean {
        if (block.blockType !in REPLACEABLE_BLOCK_TYPES || block.novaBlockState != null) {
            return false
        }
        
        if (!ProtectionManager.canPlace(ctx))
            return false
        
        val face = data[DefaultBlockStateProperties.FACING_HORIZONTAL]!!
        val footPos = block.getRelative(face.oppositeFace)
        return footPos.blockType in REPLACEABLE_BLOCK_TYPES && footPos.novaBlockState == null
    }
    
    override fun handleBreak(block: Block, state: NovaBlockState, ctx: Context<BlockBreak>) {
        val face = state[DefaultBlockStateProperties.FACING_HORIZONTAL]!!
        val footPos = block.getRelative(face.oppositeFace)
        if (footPos.blockType == Blocks.FULL_TATAMI_MAT_FOOT.get()) {
            val contextFoot = Context.intention(BlockBreak)
                .param(BlockBreak.BLOCK, footPos)
                .param(BlockBreak.SOURCE_ENTITY, ctx[BlockBreak.SOURCE_PLAYER])
                .build()
            breakBlockNaturally(contextFoot)
        }
    }
    
    
}