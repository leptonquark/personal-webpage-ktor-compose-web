package me.justin.application

import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.application.install
import io.ktor.server.html.respondHtml
import io.ktor.server.http.content.resources
import io.ktor.server.http.content.static
import io.ktor.server.netty.EngineMain
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.Routing
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import me.justin.application.data.ConfigurationService
import me.justin.application.data.JsonService
import me.justin.application.usecase.about.getAboutMessage
import me.justin.application.usecase.contactme.getContactMe
import me.justin.application.usecase.getIndex
import me.justin.application.usecase.project.getProjects
import route.ApiRoute

fun main(args: Array<String>) = EngineMain.main(args)

@Suppress("Unused")
fun Application.module() {
    routing { router() }
    install(ContentNegotiation) { json() }
}

private fun Routing.router() {
    val configurationService = ConfigurationService()
    val jsonService = JsonService()
    get("/") { call.respondHtml(HttpStatusCode.OK) { getIndex(configurationService) } }
    get(ApiRoute.ABOUT) { call.respond(getAboutMessage(configurationService)) }
    get(ApiRoute.CONTACT_ME) { call.respond(getContactMe(configurationService)) }
    get(ApiRoute.PROJECTS) {
        getProjects(jsonService)?.let {
            call.respondText(it, ContentType.Application.Json)
        } ?: call.respond(HttpStatusCode.InternalServerError)
    }
    static("/static") { resources() }
}
