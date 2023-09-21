package me.leptonquark.application.data

import me.leptonquark.application.util.fromJson
import project.ProjectList

class ProjectService : JsonService("/projects.json") {
    val projects: ProjectList? get() = resource?.run { readText().fromJson() }
}
