package fr.ateastudio.farmersdelight.util

import fr.ateastudio.farmersdelight.registry.ToolCategories
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.World
import org.bukkit.entity.Item
import org.bukkit.inventory.ItemStack
import org.bukkit.util.Vector
import xyz.xenondevs.nova.world.item.behavior.Consumable
import xyz.xenondevs.nova.world.item.getBehaviorOrNull
import xyz.xenondevs.nova.world.item.isNova
import xyz.xenondevs.nova.world.item.itemType
import xyz.xenondevs.nova.world.item.itemTypeEntry

/** A knife is any member of the common `c:tools/knife` tag ([ToolCategories.KNIVES]); no id/name guessing. */
fun ItemStack.isKnife(): Boolean = itemTypeEntry in ToolCategories.KNIVES

fun ItemStack.spawnItemEntity(world: World, x: Double, y: Double, z: Double, motionX: Double, motionY: Double, motionZ: Double) {
    val location = Location(world, x, y, z)
    this.spawnItemEntity(location, motionX, motionY, motionZ)
}

fun ItemStack.spawnItemEntity(location: Location, motionX: Double, motionY: Double, motionZ: Double) {
    val itemEntity: Item = location.world.dropItem(location, this)
    itemEntity.velocity = Vector(motionX, motionY, motionZ)
}

fun ItemStack.getItemId(): String {
    if (this.itemType.isNova) {
        return this.itemTypeEntry.key.toString()
    }
    return this.type.key.toString()
}

fun ItemStack.getCraftingRemainingItem(): ItemStack {
    if (!this.itemType.isNova) {
        return when (this.type) {
            Material.MUSHROOM_STEW,
            Material.RABBIT_STEW,
            Material.BEETROOT_SOUP,
            Material.SUSPICIOUS_STEW -> ItemStack(Material.BOWL)
            Material.MILK_BUCKET,
            Material.WATER_BUCKET,
            Material.LAVA_BUCKET,
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
        val consumable = this.itemType.getBehaviorOrNull<Consumable>()
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