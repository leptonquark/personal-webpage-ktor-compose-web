package project

import kotlinx.serialization.Serializable

@Serializable
data class Project(
    val client: String,
    val projectName: String,
    val description: String,
    val company: String,
    val city: String,
    val country: String,
    val position: String,
    val startDate: String,
    val endDate: String,
    val icon: String?,
)

@Serializable
data class ProjectList(val projects: List<Project>)
