package fr.ateastudio.farmersdelight.registry

import fr.ateastudio.farmersdelight.NovaFarmersDelight.registerFeatureType
import fr.ateastudio.farmersdelight.nms.worldgen.WildCropFeature
import xyz.xenondevs.nova.initialize.Init
import xyz.xenondevs.nova.initialize.InitStage

@Init(stage = InitStage.BOOTSTRAP)
object FeatureTypes {

    val WILD_CROP_FEATURE = registerFeatureType("wild_crop", WildCropFeature.CODEC)
}