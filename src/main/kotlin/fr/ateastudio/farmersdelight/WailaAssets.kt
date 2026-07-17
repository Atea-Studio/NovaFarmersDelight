package fr.ateastudio.farmersdelight

import xyz.xenondevs.nova.resources.builder.ResourcePackBuilder
import xyz.xenondevs.nova.resources.builder.task.PackTask
import xyz.xenondevs.nova.resources.builder.task.TextureIconContent

@Suppress("unused")
class WailaAssets(private val builder: ResourcePackBuilder) : PackTask {
    
    override val runsBefore = setOf(TextureIconContent.Write::class)
    
    @Suppress("unused")
    override suspend fun run() {
        val buildData = builder.getBuildData<TextureIconContent>()
        buildData.addIcons("farmersdelight:item/flint_knife")
    }
    
}