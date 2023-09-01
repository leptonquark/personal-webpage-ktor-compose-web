package me.justin.application.usecase.project

import me.justin.application.data.ProjectService
import project.ProjectList

fun getProjects(projectService: ProjectService) = projectService.projects ?: ProjectList(projects = emptyList())
