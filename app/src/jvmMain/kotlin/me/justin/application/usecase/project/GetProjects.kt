package me.justin.application.usecase.project

import kotlinx.serialization.json.Json
import me.justin.application.data.ProjectService
import me.justin.application.util.fromJson
import project.ProjectList

fun getProjects(projectService: ProjectService) = projectService.projects ?: ProjectList(projects = emptyList())
