package me.leptonquark.application

import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.netty.EngineMain
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.routing.routing

fun main(args: Array<String>) = EngineMain.main(args)

@Suppress("Unused")
fun Application.module() {
    routing { router() }
    install(ContentNegotiation) { json() }
}

