package me.justin.application

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.http.content.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.html.*

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
    val title =  environment.config.property("me.title").getString()
    val about = environment.config.property("me.about").getString()
    routing {
        get("/") {
            call.respondHtml(HttpStatusCode.OK) {
                index(title = title)
            }
        }
        get("/api/about"){
            call.respondText(about, ContentType.Text.Plain)
        }
        static("/static") {
            resources()
        }
    }
}
