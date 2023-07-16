package me.justin.application

import io.ktor.server.config.ApplicationConfig
import io.ktor.server.config.ApplicationConfigurationException

private const val DEFAULT_TITLE = "CV"

class ConfigurationService(private val config: ApplicationConfig) {
    val title get() = config.getPropertyOrNull("me.title") ?: DEFAULT_TITLE
    val about get() = config.getPropertyOrNull("me.about")

    private fun ApplicationConfig.getPropertyOrNull(path: String) = try {
        property(path).getString()
    } catch (_: ApplicationConfigurationException) {
        null
    }
}
