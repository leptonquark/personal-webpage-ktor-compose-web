package data

import kotlinx.serialization.Serializable

@Serializable
data class ContactMeItem(
    val name: String,
    val url: String,
    val icon: String,
)
