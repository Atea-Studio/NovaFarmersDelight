package fr.ateastudio.farmersdelight.util

import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.inventory.ItemStack


fun Block.isTag(tag: List<Material>): Boolean {
    return tag.contains(this.type)
}