package me.justin.application

import data.AboutMessage
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.config.*
import io.ktor.server.html.*
import io.ktor.server.http.content.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.html.*
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
    val title =  environment.config.getPropertyOrNull("me.title") ?: DEFAULT_TITLE
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
    staticResources("/static","resources")
}

private fun ApplicationConfig.getPropertyOrNull(path: String) = try {
    property(path).getString()
} catch (_: ApplicationConfigurationException) {
    null
}
