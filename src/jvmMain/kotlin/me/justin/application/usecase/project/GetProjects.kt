package me.justin.application.usecase.project

import kotlinx.serialization.json.Json
import me.justin.application.data.JsonService
import project.ProjectList

fun getProjects(jsonService: JsonService) = jsonService.projects?.let {
    Json.decodeFromString<ProjectList>(it)
} ?: ProjectList(projects = emptyList())
