package fr.ateastudio.farmersdelight.recipe.cuttingboard

import xyz.xenondevs.nova.world.item.tool.VanillaToolCategories

enum class ToolTag {
    SHEARS,
    KNIVES,
    OTHER;
    
    companion object {
        /** Recipe tool tags: the standard common tags, plus the legacy Forge ids older recipe files used. */
        fun fromString(value: String): ToolTag = when (value) {
            "c:tools/knife", "forge:tools/knives" -> KNIVES
            "c:tools/shear", "forge:shears", "forge:tools/shears" -> SHEARS
            else -> OTHER
        }
    }
}


enum class ToolType {
    FARMERS_DELIGHT_TOOL_ACTION,
    OTHER;
    
    companion object {
        fun fromString(value: String): ToolType = when (value) {
            "farmersdelight:tool_action" -> FARMERS_DELIGHT_TOOL_ACTION
            else -> OTHER
        }
    }
}

enum class ToolAction(val actionName: String, val toolCategory: net.kyori.adventure.key.Key?) {
    // Axes
    AXE_DIG("axe_dig", VanillaToolCategories.AXE),
    AXE_STRIP("axe_strip", VanillaToolCategories.AXE),
    AXE_SCRAPE("axe_scrape", VanillaToolCategories.AXE),
    AXE_WAX_OFF("axe_wax_off", VanillaToolCategories.AXE),
    // Pickaxes
    PICKAXE_DIG("pickaxe_dig", VanillaToolCategories.PICKAXE),
    // Shovels
    SHOVEL_DIG("shovel_dig", VanillaToolCategories.SHOVEL),
    SHOVEL_FLATTEN("shovel_flatten", VanillaToolCategories.SHOVEL),
    // Hoes
    HOE_DIG("hoe_dig", VanillaToolCategories.HOE),
    HOE_TILL("till", VanillaToolCategories.HOE),
    // Swords
    SWORD_DIG("sword_dig", VanillaToolCategories.SWORD),
    SWORD_SWEEP("sword_sweep", VanillaToolCategories.SWORD),
    // Shears
    SHEARS_DIG("shears_dig", VanillaToolCategories.SHEARS),
    SHEARS_HARVEST("shears_harvest", VanillaToolCategories.SHEARS),
    SHEARS_CARVE("shears_carve", VanillaToolCategories.SHEARS),
    SHEARS_DISARM("shears_disarm", VanillaToolCategories.SHEARS),
    // Shields
    SHIELD_BLOCK("shield_block", null),
    // Fishing Rods
    FISHING_ROD_CAST("fishing_rod_cast", null);
    
    companion object {
        private val actionMap = entries.associateBy { it.actionName }
        
        fun fromString(value: String): ToolAction =
            actionMap[value] ?: throw IllegalArgumentException("Unknown ToolAction: $value")
    }
}

