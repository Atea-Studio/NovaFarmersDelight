@file:Suppress("unused", "UnstableApiUsage")

package fr.ateastudio.farmersdelight.util

import fr.ateastudio.farmersdelight.registry.ToolCategories
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.entity.Item
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.ItemType
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
        return this.itemTypeEntry.key.asString()
    }
    return this.itemType.key.asString()
}

fun ItemStack.getCraftingRemainingItem(): ItemStack {
    if (!this.itemType.isNova) {
        return when (this.itemType) {
            ItemType.MUSHROOM_STEW,
            ItemType.RABBIT_STEW,
            ItemType.BEETROOT_SOUP,
            ItemType.SUSPICIOUS_STEW -> ItemType.BOWL.createItemStack()
            ItemType.MILK_BUCKET,
            ItemType.WATER_BUCKET,
            ItemType.LAVA_BUCKET,
            ItemType.POWDER_SNOW_BUCKET,
            ItemType.AXOLOTL_BUCKET,
            ItemType.COD_BUCKET,
            ItemType.PUFFERFISH_BUCKET,
            ItemType.SALMON_BUCKET,
            ItemType.TROPICAL_FISH_BUCKET -> ItemType.BUCKET.createItemStack()
            ItemType.POTION,
            ItemType.SPLASH_POTION,
            ItemType.LINGERING_POTION,
            ItemType.EXPERIENCE_BOTTLE,
            ItemType.HONEY_BOTTLE -> ItemType.GLASS_BOTTLE.createItemStack()
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

fun ItemStack.isTag(tag: List<ItemType>): Boolean {
        return tag.contains(this.itemType)
}