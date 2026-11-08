package fr.ateastudio.farmersdelight.gameplay

import fr.ateastudio.farmersdelight.block.comparatorFullness
import fr.ateastudio.farmersdelight.item.cookingBarWidth
import fr.ateastudio.farmersdelight.item.isHandheldCookingDone
import fr.ateastudio.farmersdelight.tileentity.skilletDisplayYaw
import fr.ateastudio.farmersdelight.tileentity.stackedDisplayCount
import fr.ateastudio.farmersdelight.item.isStrongSkilletAttack
import fr.ateastudio.farmersdelight.recipe.cookingpot.matchesIngredientMultiset
import fr.ateastudio.farmersdelight.recipe.cuttingboard.rollSurvivingCount
import fr.ateastudio.farmersdelight.tileentity.retreatCookingProgress
import fr.ateastudio.farmersdelight.tileentity.skilletCookingTime
import fr.ateastudio.farmersdelight.tileentity.stoveSlotOffset
import fr.ateastudio.farmersdelight.util.clockWise
import fr.ateastudio.farmersdelight.util.counterClockWise
import fr.ateastudio.farmersdelight.util.isHeatedThroughConductor
import fr.ateastudio.farmersdelight.util.isLitHeatSource
import org.bukkit.block.BlockFace
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class HeatRulesTest {

    @Test
    fun heatSourceMustBeLitOnlyWhenItHasALitState() {
        assertTrue(isLitHeatSource(isMember = true, hasLitState = false, lit = false)) // magma, lava, fire
        assertTrue(isLitHeatSource(isMember = true, hasLitState = true, lit = true)) // lit stove/campfire
        assertFalse(isLitHeatSource(isMember = true, hasLitState = true, lit = false)) // unlit stove/campfire
        assertFalse(isLitHeatSource(isMember = false, hasLitState = false, lit = false))
    }

    @Test
    fun exactlyOneConductorLayerTransmitsHeat() {
        assertTrue(isHeatedThroughConductor(belowIsConductor = true, twoBelowIsSource = true))
        assertFalse(isHeatedThroughConductor(belowIsConductor = true, twoBelowIsSource = false))
        assertFalse(isHeatedThroughConductor(belowIsConductor = false, twoBelowIsSource = true))
    }

    @Test
    fun leftAndRightAreCounterClockwiseAndClockwiseFromFacing() {
        assertEquals(BlockFace.WEST, BlockFace.NORTH.counterClockWise())
        assertEquals(BlockFace.EAST, BlockFace.NORTH.clockWise())
        assertEquals(BlockFace.NORTH, BlockFace.WEST.clockWise())
        assertEquals(BlockFace.SOUTH, BlockFace.WEST.counterClockWise())
    }
}

class ComparatorRulesTest {

    @Test
    fun fullnessFormulaMatchesTheSharedComparatorRule() {
        assertEquals(0, comparatorFullness(listOf(0 to 1)))
        assertEquals(1, comparatorFullness(listOf(1 to 64)))
        assertEquals(8, comparatorFullness(listOf(32 to 64)))
        assertEquals(15, comparatorFullness(listOf(64 to 64)))
    }

    @Test
    fun fullnessAveragesOverEverySlotIncludingEmptyOnes() {
        // One full slot out of nine: floor(14 * 1 / 9) + 1 = 2.
        assertEquals(2, comparatorFullness(listOf(16 to 16) + List(8) { 0 to 1 }))
    }
}

class CookingPotRulesTest {

    @Test
    fun cooledHalfCookedPotRetreatsByTwoPerStepAndClampsAtZero() {
        assertEquals(98, retreatCookingProgress(100, 200))
        assertEquals(0, retreatCookingProgress(1, 200))
        assertEquals(0, retreatCookingProgress(0, 200))
    }

    @Test
    fun recipeMatchingIgnoresSlotAndChoiceOrderWithOverlappingChoices() {
        val onionOnly: (String) -> Boolean = { it == "onion" }
        val anyVegetable: (String) -> Boolean = { it in setOf("onion", "carrot", "potato") }
        val test: ((String) -> Boolean, String) -> Boolean = { choice, item -> choice(item) }

        // A greedy matcher fails here if the onion is consumed by the broader vegetable choice first.
        assertTrue(matchesIngredientMultiset(listOf("onion", "carrot"), listOf(anyVegetable, onionOnly), test))
        assertTrue(matchesIngredientMultiset(listOf("carrot", "onion"), listOf(anyVegetable, onionOnly), test))
        assertTrue(matchesIngredientMultiset(listOf("onion", "carrot"), listOf(onionOnly, anyVegetable), test))
    }

    @Test
    fun recipeMatchingRequiresTheExactOccupiedMultiset() {
        val onionOnly: (String) -> Boolean = { it == "onion" }
        val test: ((String) -> Boolean, String) -> Boolean = { choice, item -> choice(item) }

        assertFalse(matchesIngredientMultiset(listOf("onion", "onion"), listOf(onionOnly), test))
        assertFalse(matchesIngredientMultiset(listOf("onion"), listOf(onionOnly, onionOnly), test))
        assertFalse(matchesIngredientMultiset(listOf("carrot"), listOf(onionOnly), test))
    }
}

class SkilletRulesTest {

    @Test
    fun handheldCookingCompletesOnceTheCookingTimeHasElapsed() {
        assertFalse(isHandheldCookingDone(remainingUseTicks = 72000 - 119, cookingTime = 120))
        assertTrue(isHandheldCookingDone(remainingUseTicks = 72000 - 120, cookingTime = 120))
    }

    @Test
    fun strongImpactSoundRequiresAttackStrengthAboveZeroPointEight() {
        assertFalse(isStrongSkilletAttack(0.8f))
        assertTrue(isStrongSkilletAttack(0.81f))
    }

    @Test
    fun displayedModelCountFollowsStackSize() {
        assertEquals(0, stackedDisplayCount(0, 64))
        assertEquals(1, stackedDisplayCount(1, 64))
        assertEquals(2, stackedDisplayCount(2, 64))
        assertEquals(5, stackedDisplayCount(64, 64))
        assertEquals(5, stackedDisplayCount(16, 16))
    }

    @Test
    fun displayedIngredientsFaceTheSkilletFront() {
        assertEquals(0.0, skilletDisplayYaw(BlockFace.SOUTH))
        assertEquals(-180.0, skilletDisplayYaw(BlockFace.NORTH))
        assertEquals(-90.0, skilletDisplayYaw(BlockFace.WEST))
    }

    @Test
    fun cookingBarWidthMatchesTheOriginalFormula() {
        assertEquals(0, cookingBarWidth(0, 120))
        assertEquals(7, cookingBarWidth(60, 120)) // round(6.5) = 7
        assertEquals(13, cookingBarWidth(120, 120))
        assertEquals(13, cookingBarWidth(500, 120))
    }

    @Test
    fun normalRecipeDurationsFollowTheFireAspectFormula() {
        assertEquals(120, skilletCookingTime(600))
        assertEquals(80, skilletCookingTime(600, fireAspectLevel = 1))
        assertEquals(60, skilletCookingTime(600, fireAspectLevel = 2))
    }

    @Test
    fun shortRecipesAreClampedToTheOriginalTimeWithoutCrashing() {
        assertEquals(40, skilletCookingTime(40))
    }
}

class CuttingBoardRulesTest {

    @Test
    fun eachOutputUnitIsRolledIndependently() {
        val draws = ArrayDeque(listOf(0.1, 0.8, 0.2))
        assertEquals(2, rollSurvivingCount(3, chance = 0.5, fortuneLevel = 0) { draws.removeFirst() })
    }

    @Test
    fun fortuneAddsPointOnePerLevel() {
        assertEquals(1, rollSurvivingCount(1, chance = 0.5, fortuneLevel = 1) { 0.55 })
        assertEquals(0, rollSurvivingCount(1, chance = 0.5, fortuneLevel = 1) { 0.65 })
    }
}

class StoveRulesTest {

    @Test
    fun sixSlotsUseTheSpecifiedOffsetsRotatedOppositeFacing() {
        assertOffset(0.3, 0.2, stoveSlotOffset(0, BlockFace.NORTH))
        assertOffset(-0.3, -0.2, stoveSlotOffset(5, BlockFace.NORTH))
        assertOffset(-0.3, -0.2, stoveSlotOffset(0, BlockFace.SOUTH))
    }

    private fun assertOffset(x: Double, z: Double, actual: Pair<Double, Double>) {
        assertEquals(x, actual.first, 1e-9)
        assertEquals(z, actual.second, 1e-9)
    }
}
