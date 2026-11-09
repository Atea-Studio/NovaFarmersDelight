package fr.ateastudio.farmersdelight.nms.worldgen

import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.core.BlockPos
import net.minecraft.core.BlockPos.MutableBlockPos
import net.minecraft.core.Holder
import net.minecraft.util.ExtraCodecs
import net.minecraft.util.RandomSource
import net.minecraft.world.level.WorldGenLevel
import net.minecraft.world.level.chunk.ChunkGenerator
import net.minecraft.world.level.levelgen.placement.PlacedFeature
import xyz.xenondevs.nova.world.generation.FeatureType
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

//SOURCE: https://github.com/vectorwing/FarmersDelight/blob/1.21/src/main/java/vectorwing/farmersdelight/common/world/feature/WildCropFeature.java
/**
 * Nova 0.25 collapses the vanilla Feature/FeatureConfiguration split: [FeatureType] instances carry
 * their own configuration as constructor properties, decoded directly via [CODEC].
 */
class WildCropFeature(
    val tries: Int,
    val xzSpread: Int,
    val ySpread: Int,
    val primaryFeature: Holder<PlacedFeature>,
    val secondaryFeature: Holder<PlacedFeature>,
    val floorFeature: Optional<Holder<PlacedFeature>>
) : FeatureType(CODEC) {
    
    override fun place(level: WorldGenLevel, generator: ChunkGenerator, random: RandomSource, pos: BlockPos): Boolean {
        var i = 0
        val xzSpreadRange = xzSpread + 1
        val ySpreadRange = ySpread + 1
        
        val mutablePos = MutableBlockPos()
        
        val floor = floorFeature.getOrNull()
        if (floor != null) {
            (0 until tries).forEach { _ ->
                mutablePos.setWithOffset(pos, random.nextInt(xzSpreadRange) - random.nextInt(xzSpreadRange), random.nextInt(ySpreadRange) - random.nextInt(ySpreadRange), random.nextInt(xzSpreadRange) - random.nextInt(xzSpreadRange))
                if (floor.value().place(level, generator, random, mutablePos)) {
                    ++i
                }
            }
        }
        
        (0 until tries).forEach { _ ->
            val shorterXZ = xzSpreadRange - 2
            mutablePos.setWithOffset(pos, random.nextInt(shorterXZ) - random.nextInt(shorterXZ), random.nextInt(ySpreadRange) - random.nextInt(ySpreadRange), random.nextInt(shorterXZ) - random.nextInt(shorterXZ))
            if (primaryFeature.value().place(level, generator, random, mutablePos)) {
                ++i
            }
        }
        
        (0 until tries).forEach { _ ->
            mutablePos.setWithOffset(pos, random.nextInt(xzSpreadRange) - random.nextInt(xzSpreadRange), random.nextInt(ySpreadRange) - random.nextInt(ySpreadRange), random.nextInt(xzSpreadRange) - random.nextInt(xzSpreadRange))
            if (secondaryFeature.value().place(level, generator, random, mutablePos)) {
                ++i
            }
        }
        
        return i > 0
    }
    
    companion object {
        @JvmField
        val CODEC: MapCodec<WildCropFeature> = RecordCodecBuilder.mapCodec { instance ->
            instance.group(
                ExtraCodecs.POSITIVE_INT.fieldOf("tries").orElse(64).forGetter(WildCropFeature::tries),
                ExtraCodecs.NON_NEGATIVE_INT.fieldOf("xz_spread").orElse(4).forGetter(WildCropFeature::xzSpread),
                ExtraCodecs.NON_NEGATIVE_INT.fieldOf("y_spread").orElse(3).forGetter(WildCropFeature::ySpread),
                PlacedFeature.CODEC.fieldOf("primary_feature").forGetter(WildCropFeature::primaryFeature),
                PlacedFeature.CODEC.fieldOf("secondary_feature").forGetter(WildCropFeature::secondaryFeature),
                PlacedFeature.CODEC.optionalFieldOf("floor_feature").forGetter(WildCropFeature::floorFeature)
            ).apply(instance, ::WildCropFeature)
        }
    }
}
