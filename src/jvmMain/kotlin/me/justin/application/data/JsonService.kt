package me.justin.application.data

import java.net.URL

class JsonService {

    private val projectsResource = getProjectsResource()
    val projects: String?
        get() = projectsResource?.readText()

    private fun getProjectsResource(): URL? = javaClass.run {
        getResource("/projects.json") ?: getResource("/projects-sample.json")
    }
}
