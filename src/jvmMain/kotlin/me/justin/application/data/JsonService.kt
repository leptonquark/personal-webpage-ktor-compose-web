package me.justin.application.data

import kotlinx.serialization.json.Json
import project.ProjectList
import java.io.File

class JsonService {

    private val projectsFile = File("/resources/projects.json")
    val projectsJson = projectsFile.readText()
    val projects: ProjectList
        get() = Json.decodeFromString<ProjectList>(projectsJson)
}
