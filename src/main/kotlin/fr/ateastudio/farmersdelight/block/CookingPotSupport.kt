package fr.ateastudio.farmersdelight.block

enum class CookingPotSupport {
    NONE,
    TRAY,
    HANDLE
}


internal object CookingPotSupportRules {
    fun onPlacement(placedAgainstUnderside: Boolean, trayEligibleBelow: Boolean): CookingPotSupport = when {
        placedAgainstUnderside -> CookingPotSupport.HANDLE
        trayEligibleBelow -> CookingPotSupport.TRAY
        else -> CookingPotSupport.NONE
    }

    fun afterNeighborUpdate(current: CookingPotSupport, trayEligibleBelow: Boolean): CookingPotSupport = when {
        current == CookingPotSupport.HANDLE -> CookingPotSupport.HANDLE
        trayEligibleBelow -> CookingPotSupport.TRAY
        else -> CookingPotSupport.NONE
    }
}
