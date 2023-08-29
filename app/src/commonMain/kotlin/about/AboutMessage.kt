package about

import kotlinx.serialization.Serializable

@Serializable
data class AboutMessage(val message: String?)
