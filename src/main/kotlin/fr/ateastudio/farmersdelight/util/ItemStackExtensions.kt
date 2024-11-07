package fr.ateastudio.farmersdelight.util

import org.bukkit.Material
import org.bukkit.craftbukkit.inventory.CraftItemStack
import org.bukkit.entity.Item
import org.bukkit.inventory.ItemStack
import xyz.xenondevs.nova.util.item.novaItem
import xyz.xenondevs.nova.world.item.NovaItem
import xyz.xenondevs.nova.world.item.behavior.Consumable

fun ItemStack.getItemId(): String {
    if (this.novaItem is NovaItem) {
        return this.novaItem!!.id.toString()
    }
    return this.type.key.toString()
}

fun ItemStack.getCraftingRemainingItem(): ItemStack {
    if (this.novaItem == null) {
        return when (this.type) {
            Material.MUSHROOM_STEW,
            Material.RABBIT_STEW,
            Material.BEETROOT_SOUP,
            Material.SUSPICIOUS_STEW -> ItemStack(Material.BOWL)
            Material.MILK_BUCKET,
            Material.POWDER_SNOW_BUCKET,
            Material.AXOLOTL_BUCKET,
            Material.COD_BUCKET,
            Material.PUFFERFISH_BUCKET,
            Material.SALMON_BUCKET,
            Material.TROPICAL_FISH_BUCKET -> ItemStack(Material.BUCKET)
            Material.POTION,
            Material.SPLASH_POTION,
            Material.LINGERING_POTION,
            Material.EXPERIENCE_BOTTLE,
            Material.HONEY_BOTTLE -> ItemStack(Material.GLASS_BOTTLE)
            else -> ItemStack.empty()
        }
    }
    else {
        val novaItem = this.novaItem!!
        val consumable = novaItem.getBehaviorOrNull<Consumable>()
        if (consumable != null) {
            return consumable.remains ?: ItemStack.empty()
        }
        return ItemStack.empty()
    }
}

fun ItemStack.hasCraftingRemainingItem(): Boolean {
        return !this.getCraftingRemainingItem().isEmpty
}

fun ItemStack.isTag(tag: List<Material>): Boolean {
        return tag.contains(this.type)
}

fun ItemStack?.unwrap(): net.minecraft.world.item.ItemStack =
    this?.let(CraftItemStack::unwrap) ?: net.minecraft.world.item.ItemStack.EMPTY