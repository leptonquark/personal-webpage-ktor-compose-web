package me.justin.application.data

import me.justin.application.util.fromJson
import project.ProjectList

class ProjectService : JsonService("/projects.json", "/projects-sample.json") {

    val projects: ProjectList?
        get() = resource?.readText()?.fromJson()

}
