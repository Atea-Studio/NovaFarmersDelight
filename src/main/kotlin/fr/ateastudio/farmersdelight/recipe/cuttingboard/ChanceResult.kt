package fr.ateastudio.farmersdelight.recipe.cuttingboard

import org.bukkit.inventory.ItemStack
import kotlin.random.Random

/** Configured per-level Fortune bonus added to each output unit's survival chance (default 0.1). */
const val CUTTING_BOARD_FORTUNE_BONUS = 0.1

/** Each unit survives independently when its draw is `<= chance + fortuneLevel * bonus`. */
internal fun rollSurvivingCount(count: Int, chance: Double, fortuneLevel: Int, nextDraw: () -> Double): Int {
    val threshold = chance + fortuneLevel * CUTTING_BOARD_FORTUNE_BONUS
    return (0 until count).count { nextDraw() <= threshold }
}

class ChanceResult
(private val stack: ItemStack, private val chance: Float) {
    
    fun getStack(): ItemStack {
        return stack
    }
    
    fun rollOutput(rand: Random, fortuneLevel: Int): ItemStack {
        val outputAmount = rollSurvivingCount(stack.amount, chance.toDouble(), fortuneLevel) { rand.nextFloat().toDouble() }
        if (outputAmount == 0) return ItemStack.empty()
        val out: ItemStack = stack.clone()
        out.amount = outputAmount
        return out
    }
}