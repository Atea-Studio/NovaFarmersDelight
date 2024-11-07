package fr.ateastudio.farmersdelight.util

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.TranslatableComponent
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer

fun TranslatableComponent.replacePlaceholders(placeholders: Map<String, String>): Component {
    // Convert TranslatableComponent to legacy text with the placeholders
    val legacyText = LegacyComponentSerializer.legacySection().serialize(this)
    
    // Replace each placeholder in the legacy text
    var resultText = legacyText
    placeholders.forEach { (key, value) ->
        resultText = resultText.replace("{$key}", value)
    }
    
    // Deserialize back into a Component to maintain Adventure compatibility
    return LegacyComponentSerializer.legacySection().deserialize(resultText)
}