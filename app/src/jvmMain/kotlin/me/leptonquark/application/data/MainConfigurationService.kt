package me.leptonquark.application.data

import kotlinx.serialization.Serializable
import me.leptonquark.application.util.fromJson

@Serializable
data class MainConfiguration(
    val title: String,
    val about: String,
    val contactMe: List<String> = emptyList(),
)

class MainConfigurationService : JsonService("/main-configuration.json") {
    val configuration: MainConfiguration? get() = resource?.run { readText().fromJson<MainConfiguration>() }
}
