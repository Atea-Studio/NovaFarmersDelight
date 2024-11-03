package fr.ateastudio.farmersdelight.tileentity

import fr.ateastudio.farmersdelight.registry.GuiItems
import fr.ateastudio.farmersdelight.registry.GuiTextures
import xyz.xenondevs.cbf.Compound
import xyz.xenondevs.invui.gui.Gui
import xyz.xenondevs.nova.world.BlockPos
import xyz.xenondevs.nova.world.block.state.NovaBlockState
import xyz.xenondevs.nova.world.block.tileentity.TileEntity
import xyz.xenondevs.nova.world.block.tileentity.menu.TileEntityMenuClass


class CookingPot(
    pos: BlockPos,
    state: NovaBlockState,
    data: Compound
) : TileEntity(pos, state, data) {
    
    @TileEntityMenuClass
    private inner class CookingPotMenu : GlobalTileEntityMenu(GuiTextures.COOKING_POT) {
        override val gui = Gui.normal()
            .setStructure(
                ". . . . . a . . .",
                ". . . . . . . . .",
                ". . f . . b . . ."
            )
            .addIngredient('a', GuiItems.ARROW_PROGRESS_FULL.createItemStack())
            .addIngredient('f', GuiItems.COOKING_POT_HEATED.createItemStack())
            .addIngredient('b', GuiItems.BOWL_UNDERLAY.createItemStack())
            .build()
        
    }
}