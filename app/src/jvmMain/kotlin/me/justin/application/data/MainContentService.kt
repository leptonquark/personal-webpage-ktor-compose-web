package me.justin.application.data

import kotlinx.serialization.Serializable
import me.justin.application.util.fromJson

@Serializable
data class MainContent(
    val title: String,
    val about: String,
    val contactMe: List<String>,
)

class MainContentService : JsonService("/main-content.json", "/main-content-sample.json") {
    val mainContent: MainContent? get() = resource?.readText()?.fromJson<MainContent>()
}
