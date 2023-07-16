package me.justin.application

import com.typesafe.config.Config
import com.typesafe.config.ConfigException
import com.typesafe.config.ConfigFactory
import data.ContactMeLink
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.hocon.Hocon
import kotlinx.serialization.hocon.decodeFromConfig

private const val DEFAULT_TITLE = "CV"

private object ConfigurationPath {
    const val TITLE = "me.title"
    const val ABOUT = "me.about"
    const val CONTACT_ME = "me.contactMe"
}

class ConfigurationService {

    private val config = ConfigFactory.load()

    val title get() = config.getStringOrNull(ConfigurationPath.TITLE) ?: DEFAULT_TITLE
    val about get() = config.getStringOrNull(ConfigurationPath.ABOUT)

    val contactMe = config.getListOrNull<ContactMeLink>(ConfigurationPath.CONTACT_ME)


    private fun Config.getStringOrNull(path: String) = try {
        getString(path)
    } catch (_: ConfigException.Missing) {
        null
    }

    @OptIn(ExperimentalSerializationApi::class)
    private inline fun <reified T> Config.getListOrNull(path: String) = try {
        getConfigList(path)
    } catch (_: ConfigException.Missing) {
        null
    }?.map { Hocon.decodeFromConfig<T>(it) } ?: emptyList()
}
