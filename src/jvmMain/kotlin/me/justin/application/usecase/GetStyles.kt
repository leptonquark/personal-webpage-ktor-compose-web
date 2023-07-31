package me.justin.application.usecase

import io.ktor.http.ContentType
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respondText
import kotlinx.css.CssBuilder
import kotlinx.css.Overflow
import kotlinx.css.body
import kotlinx.css.overflow


const val STYLES = "/styles.css"

fun CssBuilder.getStyles() {
    body {
        overflow = Overflow.hidden
    }
}

suspend inline fun ApplicationCall.respondCss(builder: CssBuilder.() -> Unit) {
    respondText(CssBuilder().apply(builder).toString(), ContentType.Text.CSS)
}