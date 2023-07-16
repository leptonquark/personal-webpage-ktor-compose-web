package me.justin.application

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
import me.justin.application.usecase.getAboutMessage
import me.justin.application.usecase.getContactMe
import me.justin.application.usecase.getIndex
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
    val configurationService = ConfigurationService()
    routing { router(configurationService) }
    install(ContentNegotiation) { json() }
}

private fun Routing.router(configurationService: ConfigurationService) {
    get("/") { call.respondHtml(HttpStatusCode.OK) { getIndex(configurationService) } }
    get(ApiRoute.ABOUT) { call.respond(configurationService.getAboutMessage()) }
    get(ApiRoute.CONTACT_ME) { call.respond(configurationService.getContactMe()) }
    static("/static") { resources() }
}
