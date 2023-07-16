package me.justin.application

import data.AboutMessage
import data.ContactMeLink
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
import io.ktor.server.routing.Routing
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import kotlinx.html.HTML
import kotlinx.html.body
import kotlinx.html.canvas
import kotlinx.html.div
import kotlinx.html.head
import kotlinx.html.id
import kotlinx.html.script
import kotlinx.html.title
import route.ApiRoute


fun HTML.index(title: String) {
    head {
        title(title)
        script { src = "/static/skiko.js" }
    }
    body {
        canvas { id = "ComposeTarget" }
        div {
            id = "root"
        }
        script(src = "/static/CV.js") {}
    }
}

fun main(args: Array<String>) = EngineMain.main(args)

@Suppress("Unused")
fun Application.module() {
    val configurationService = ConfigurationService(environment.config)
    routing { router(configurationService) }
    install(ContentNegotiation) { json() }
}

private fun Routing.router(configurationService: ConfigurationService) {
    get("/") { call.respondHtml(HttpStatusCode.OK) { index(title = configurationService.title) } }
    get(ApiRoute.ABOUT) { call.respond(AboutMessage(configurationService.about)) }
    get(ApiRoute.CONTACT_ME) {
        //val file = File("data/contact-me.json")
        //val response = file.readText()
        val contactMeLinks = listOf(
            ContactMeLink("LinkedIn", "https://www.linkedin.com/in/justinsaler/", "static/linkedin.png"),
            ContactMeLink("GitHub", "https://github.com/leptonquark/", "static/github.png"),
            ContactMeLink("Twitter", "https://twitter.com/Leetkingen/", "static/twitter.png"),
        )
        call.respond(contactMeLinks)
    }
    static("/static") {
        resources()
    }
}