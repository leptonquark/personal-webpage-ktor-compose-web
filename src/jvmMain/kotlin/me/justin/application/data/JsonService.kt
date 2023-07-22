package me.justin.application.data

class JsonService {

    private val projectsResource = javaClass.getResource("/projects.json")
    val projects: String?
        get() = projectsResource?.readText()
}
