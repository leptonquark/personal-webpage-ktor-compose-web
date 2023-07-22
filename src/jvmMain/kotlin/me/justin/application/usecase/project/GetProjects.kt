package me.justin.application.usecase.project

import me.justin.application.data.JsonService
import project.ProjectList

fun getProjects(jsonService: JsonService): ProjectList = jsonService.projects
