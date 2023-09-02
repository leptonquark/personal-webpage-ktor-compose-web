package me.leptonquark.application.usecase.project

import me.leptonquark.application.data.ProjectService
import project.ProjectList

fun getProjects(projectService: ProjectService) = projectService.projects ?: ProjectList(projects = emptyList())
