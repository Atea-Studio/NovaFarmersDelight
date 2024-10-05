package fr.ateastudio

import fr.ateastudio.registry.ToolCategories.KNIFE
import fr.ateastudio.registry.ToolTiers.FLINT
import net.minecraft.resources.ResourceLocation
import xyz.xenondevs.nova.ui.waila.info.WailaToolIconProvider
import xyz.xenondevs.nova.world.item.tool.ToolCategory
import xyz.xenondevs.nova.world.item.tool.ToolTier

object WailaToolIconProvider : WailaToolIconProvider {
    
    override fun getIcon(category: ToolCategory, tier: ToolTier?): ResourceLocation? {
        val name = when (category) {
            KNIFE -> when (tier) {
                FLINT -> "${tier.id.path}_${category.id.path}"
                else -> null
            }
            else -> null
        } ?: return null
        return ResourceLocation.fromNamespaceAndPath("farmersdelight","item/$name")
    }
}