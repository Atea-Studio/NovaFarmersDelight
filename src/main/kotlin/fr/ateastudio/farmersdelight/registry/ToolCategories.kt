package fr.ateastudio.farmersdelight.registry

import fr.ateastudio.farmersdelight.NovaFarmersDelight.blockTag
import fr.ateastudio.farmersdelight.NovaFarmersDelight.itemTag
import io.papermc.paper.registry.RegistryKey
import io.papermc.paper.registry.tag.TagKey
import net.kyori.adventure.key.Key
import xyz.xenondevs.nova.initialize.Init
import xyz.xenondevs.nova.initialize.InitStage
import xyz.xenondevs.nova.registry.entries.BlockTypeEntries
import xyz.xenondevs.nova.registry.registryEntrySetOf
import xyz.xenondevs.nova.registry.tags.BlockTypeTags

/**
 * Knife tool category and knife tags. Membership has a single source of truth: the tool category.
 *
 * - [KNIFE] is the tool category key (`tool_category` in the knife configs). From it Nova derives the block tag
 *   [MINEABLE_KNIFE] (`farmersdelight:mineable/knife`) and the item tag [KNIFE_CATEGORY_ITEMS]
 *   (`farmersdelight:knifes`), which Nova fills automatically with every item of this category.
 * - [KNIVES] (`c:tools/knife`, the cross-mod convention used by the original mod) includes that category tag.
 *   It is the only tag this addon checks, so any item configured with the knife category is a knife everywhere,
 *   and other mods/addons can also add their knives to it directly.
 */
@Suppress("unused")
@Init(stage = InitStage.PRE_PACK, runBefore = [Items::class])
object ToolCategories {
    val KNIFE: Key = Key.key("farmersdelight", "knife")

    /**
     * `farmersdelight:knifes`: the item tag Nova derives from [KNIFE] (Nova pluralizes "knife" as "knifes") and
     * fills with every knife. Nova requires it to be declared, or startup fails with
     * "Registry entries for non-existent values".
     */
    private val KNIFE_CATEGORY_ITEMS = itemTag("knifes") {}

    /** `c:tools/knife`: every knife; contains all items of the [KNIFE] tool category. */
    val KNIVES = itemTag(registryEntrySetOf(TagKey.create(RegistryKey.ITEM, Key.key("c", "tools/knife")))) {
        add(KNIFE_CATEGORY_ITEMS)
    }

    /** `c:mineable/knife`: blocks other mods declare knife-mineable (empty here). */
    private val COMMON_MINEABLE_KNIFE = blockTag(registryEntrySetOf(TagKey.create(RegistryKey.BLOCK, Key.key("c", "mineable/knife")))) {}

    /** `farmersdelight:straw_blocks`. The original's rope and safety net are not implemented in this port. */
    val STRAW_BLOCKS = blockTag("straw_blocks") {
        add(Blocks.RICE_BAG)
    }

    /**
     * `farmersdelight:mineable/knife`: blocks knives mine efficiently. The original also lists its pumpkin pie
     * block, which is not implemented in this port.
     */
    val MINEABLE_KNIFE = blockTag("mineable/knife") {
        add(
            BlockTypeEntries.CACTUS, BlockTypeEntries.MELON, BlockTypeEntries.PUMPKIN, BlockTypeEntries.CARVED_PUMPKIN,
            BlockTypeEntries.JACK_O_LANTERN, BlockTypeEntries.COBWEB, BlockTypeEntries.CAKE,
            Blocks.APPLE_PIE, Blocks.SWEET_BERRY_CHEESECAKE, Blocks.CHOCOLATE_PIE
        )
        add(BlockTypeTags.WOOL_CARPETS)
        add(BlockTypeTags.WOOL)
        add(BlockTypeTags.CANDLE_CAKES)
        add(STRAW_BLOCKS)
        add(COMMON_MINEABLE_KNIFE)
    }

    val INCORRECT_FOR_FLINT_TOOL = blockTag("incorrect_for_flint_tool") {
        add(BlockTypeTags.INCORRECT_FOR_IRON_TOOL)
    }
}
