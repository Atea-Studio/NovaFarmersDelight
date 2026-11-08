package fr.ateastudio.farmersdelight.gameplay

import fr.ateastudio.farmersdelight.block.CookingPotSupport
import fr.ateastudio.farmersdelight.block.CookingPotSupportRules
import fr.ateastudio.farmersdelight.block.behavior.saturationAfterFood
import kotlin.test.Test
import kotlin.test.assertEquals

class GameplayRulesTest {

    @Test
    fun cookingPotSupportUsesPlacementFaceAndPreservesHandles() {
        assertEquals(
            CookingPotSupport.HANDLE,
            CookingPotSupportRules.onPlacement(placedAgainstUnderside = true, trayEligibleBelow = false)
        )
        assertEquals(
            CookingPotSupport.TRAY,
            CookingPotSupportRules.onPlacement(placedAgainstUnderside = false, trayEligibleBelow = true)
        )
        assertEquals(
            CookingPotSupport.NONE,
            CookingPotSupportRules.onPlacement(placedAgainstUnderside = false, trayEligibleBelow = false)
        )
        assertEquals(
            CookingPotSupport.HANDLE,
            CookingPotSupportRules.afterNeighborUpdate(CookingPotSupport.HANDLE, trayEligibleBelow = false)
        )
        assertEquals(
            CookingPotSupport.TRAY,
            CookingPotSupportRules.afterNeighborUpdate(CookingPotSupport.TRAY, trayEligibleBelow = true)
        )
        assertEquals(
            CookingPotSupport.NONE,
            CookingPotSupportRules.afterNeighborUpdate(CookingPotSupport.TRAY, trayEligibleBelow = false)
        )
    }

    @Test
    fun foodSaturationDoesNotExceedResultingHunger() {
        assertEquals(10.0f, saturationAfterFood(8.0f, 5.0f, resultingHunger = 10))
        assertEquals(2.0f, saturationAfterFood(0.0f, 5.0f, resultingHunger = 2))
    }
}
