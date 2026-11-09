package fr.ateastudio.farmersdelight.gui

import fr.ateastudio.farmersdelight.registry.GuiItems
import xyz.xenondevs.commons.provider.mutableProvider
import xyz.xenondevs.commons.provider.provider
import xyz.xenondevs.invui.dsl.ExperimentalDslApi
import xyz.xenondevs.invui.item.ItemBuilder
import xyz.xenondevs.invui.item.Item
import xyz.xenondevs.nova.ui.menu.item.progressItem

@OptIn(ExperimentalDslApi::class)
class ProgressArrowItem {
    private val progress = mutableProvider(0.0)

    val item: Item = progressItem(
        provider(ItemBuilder(GuiItems.ARROW_PROGRESS.get().createItemStack())),
        progress
    )

    var percentage: Double
        get() = progress.get()
        set(value) = progress.set(value)
}