package me.justin.application

import com.typesafe.config.Config
import com.typesafe.config.ConfigException
import com.typesafe.config.ConfigFactory

private const val DEFAULT_TITLE = "CV"

private object ConfigurationPath {
    const val TITLE = "me.title"
    const val ABOUT = "me.about"
    const val CONTACT_ME = "me.contactMe"
}

class ConfigurationService {

    private val config = ConfigFactory.load().withFallback(ConfigFactory.load("application-sample"))

    val title get() = config.getStringOrNull(ConfigurationPath.TITLE) ?: DEFAULT_TITLE
    val about get() = config.getStringOrNull(ConfigurationPath.ABOUT)
    val contactMe = config.getStringListOrNull(ConfigurationPath.CONTACT_ME) ?: emptyList()

    private fun Config.getStringOrNull(path: String) = try {
        getString(path)
    } catch (_: ConfigException.Missing) {
        null
    }

    private fun Config.getStringListOrNull(path: String) = try {
        getStringList(path)
    } catch (_: ConfigException.Missing) {
        null
    }
}
