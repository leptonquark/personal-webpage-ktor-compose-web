package data

import kotlinx.serialization.Serializable

@Serializable
data class ContactMeLink(
    val name: String,
    val url: String,
    val icon: String,
)
