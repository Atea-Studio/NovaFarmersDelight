package fr.ateastudio.farmersdelight.registry

import fr.ateastudio.farmersdelight.NovaFarmersDelight.wailaToolIconProvider
import net.kyori.adventure.key.Key
import xyz.xenondevs.nova.initialize.Init
import xyz.xenondevs.nova.initialize.InitStage
import xyz.xenondevs.nova.resources.builder.ResourcePackBuilder
import xyz.xenondevs.nova.resources.builder.task.PackTask
import xyz.xenondevs.nova.resources.builder.task.TextureIconContent

/** Texture shown in WAILA for blocks a knife mines efficiently (like vanilla's wooden sword / shears icons). */
private const val KNIFE_ICON = "farmersdelight:item/flint_knife"

/**
 * WAILA tool icon for the knife tool category: blocks in [ToolCategories.MINEABLE_KNIFE] show a knife icon.
 * Registered at [InitStage.PRE_PACK] so the icon texture is added to Nova's icon font before the pack is built.
 */
@Suppress("unused")
@Init(stage = InitStage.PRE_PACK)
object WailaToolIconProviders {

    val KNIFE = wailaToolIconProvider("knife") {
        iconProvider { tags ->
            if (ToolCategories.MINEABLE_KNIFE in tags) setOf(Key.key(KNIFE_ICON)) else emptySet()
        }
    }

    init {
        ResourcePackBuilder.configure(ResourcePackBuilder.CORE_PACK_ID) {
            registerTask(::KnifeIconTask)
        }
    }

    /** Adds the knife texture to Nova's texture icon font, as required for WAILA tool icons. */
    private class KnifeIconTask(private val builder: ResourcePackBuilder) : PackTask {

        override val runsBefore = setOf(TextureIconContent.Write::class)

        override suspend fun run() {
            builder.getBuildData<TextureIconContent>().addIcons(KNIFE_ICON)
        }
    }
}
