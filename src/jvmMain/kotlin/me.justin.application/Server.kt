package me.justin.application

import data.AboutMessage
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.application.install
import io.ktor.server.config.ApplicationConfig
import io.ktor.server.config.ApplicationConfigurationException
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

private const val DEFAULT_TITLE = "CV"

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

fun main(args: Array<String>): Unit = EngineMain.main(args)

@Suppress("Unused")
fun Application.module() {
    val title = environment.config.getPropertyOrNull("me.title") ?: DEFAULT_TITLE
    val about = environment.config.getPropertyOrNull("me.about")
    routing { router(title, about) }
    install(ContentNegotiation) { json() }
}

private fun Routing.router(title: String, about: String?) {
    get("/") {
        call.respondHtml(HttpStatusCode.OK) {
            index(title = title)
        }
    }
    get(ApiRoute.ABOUT) {
        call.respond(AboutMessage(about))
    }
    static("/static") {
        resources()
    }
}

private fun ApplicationConfig.getPropertyOrNull(path: String) = try {
    property(path).getString()
} catch (_: ApplicationConfigurationException) {
    null
}
