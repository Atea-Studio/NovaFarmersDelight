package fr.ateastudio

import xyz.xenondevs.nova.resources.builder.ResourcePackBuilder
import xyz.xenondevs.nova.resources.builder.task.PackTask
import xyz.xenondevs.nova.resources.builder.task.PackTaskHolder
import xyz.xenondevs.nova.resources.builder.task.font.TextureIconContent

@Suppress("unused")
class WailaAssets(private val builder: ResourcePackBuilder) : PackTaskHolder {
    
    @Suppress("unused")
    @PackTask(runBefore = arrayOf("TextureIconContent#write"))
    fun register() {
        val textureIconContent = builder.getHolder<TextureIconContent>()
        textureIconContent.addIcons(
            "farmersdelight:item/flint_knife",
        )
    }
    
}