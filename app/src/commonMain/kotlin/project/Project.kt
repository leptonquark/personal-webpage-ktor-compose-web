package project

import kotlinx.serialization.Serializable

@Serializable
data class Project(
    val client: String,
    val projectName: String,
    val description: String,
    val icon: String? = null,
)

@Serializable
data class ProjectList(val projects: List<Project>)
