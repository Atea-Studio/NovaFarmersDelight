package fr.ateastudio.farmersdelight.block

import fr.ateastudio.farmersdelight.util.isHeatedFromBelow
import fr.ateastudio.farmersdelight.util.isRedstonePowered
import fr.ateastudio.farmersdelight.util.isTrayHeatSource
import net.kyori.adventure.key.Key
import org.bukkit.block.BlockFace
import xyz.xenondevs.nova.context.Autofiller
import xyz.xenondevs.nova.context.intention.BlockPlace
import xyz.xenondevs.nova.util.below
import xyz.xenondevs.nova.world.block.state.property.BooleanProperty
import xyz.xenondevs.nova.world.block.state.property.EnumProperty
import xyz.xenondevs.nova.world.block.state.property.IntProperty

/**
 * Placement-time values are set by autofillers, so tile entities are created with the right state.
 * Later neighbour-driven changes are applied by the tile entities themselves (`updateBlockState`).
 */
object BlockStateProperties {
    val AGE = IntProperty(Key.key("farmersdelight", "age"), 0..7)
    val MAX_AGE = IntProperty(Key.key("farmersdelight", "max_age"), 0..7)
    val BUDDING_AGE = IntProperty(Key.key("farmersdelight", "budding_age"), 0..7)
    // Autofillers must only reference BlockPlace inside their lambdas: these properties are created during
    // server bootstrap, and initializing BlockPlace that early touches the block registry before it is loaded.
    val HEATED = BooleanProperty(Key.key("farmersdelight", "heated"), false, Autofiller.dynamic<Boolean, BlockPlace> {
        resolve(BlockPlace.BLOCK)?.isHeatedFromBelow()
    })
    /** Cooking pot support: a handle when placed against a block's underside, a tray over a tray heat source. */
    val SUPPORT = EnumProperty(
        Key.key("farmersdelight", "support"),
        CookingPotSupport::class.java,
        CookingPotSupport.entries.toSet(),
        CookingPotSupport.NONE,
        Autofiller.dynamic<CookingPotSupport, BlockPlace> {
            val pos = resolve(BlockPlace.BLOCK) ?: return@dynamic null
            CookingPotSupportRules.onPlacement(resolve(BlockPlace.CLICKED_BLOCK_FACE) == BlockFace.DOWN, pos.below.isTrayHeatSource())
        }
    )
    val PAIRED = BooleanProperty(Key.key("farmersdelight", "paired"))
    val BITES = IntProperty(Key.key("farmersdelight", "bites"), 0..3)
    val SERVINGS = IntProperty(Key.key("farmersdelight", "servings"), 0..8)
    /** Skillet tray (legs) model: shown while resting on a tray heat source. */
    val TRAY = BooleanProperty(Key.key("farmersdelight", "tray"), false, Autofiller.dynamic<Boolean, BlockPlace> {
        resolve(BlockPlace.BLOCK)?.below?.isTrayHeatSource()
    })
    // Default unlit; a normally placed stove is lit. Set through placement so the tile entity starts in sync.
    val LIT = BooleanProperty(Key.key("farmersdelight", "lit"), false, Autofiller.from { true })
    val OPEN = BooleanProperty(Key.key("farmersdelight", "open"))
    /** Basket collection: enabled unless receiving redstone power. */
    val ENABLED = BooleanProperty(Key.key("farmersdelight", "enabled"), true, Autofiller.dynamic<Boolean, BlockPlace> {
        resolve(BlockPlace.BLOCK)?.let { !it.isRedstonePowered() }
    })
    val CROP = BooleanProperty(Key.key("farmersdelight", "crop"))
}