package me.leptonquark.application.usecase

import kotlinx.html.HTML
import kotlinx.html.body
import kotlinx.html.canvas
import kotlinx.html.div
import kotlinx.html.head
import kotlinx.html.id
import kotlinx.html.link
import kotlinx.html.script
import kotlinx.html.title
import me.leptonquark.application.data.MainConfigurationService

fun HTML.getIndex(mainConfigurationService: MainConfigurationService) {
    head {
        title(getTitle(mainConfigurationService))
        link(rel = "stylesheet", href = "/styles.css", type = "text/css")
        script { src = "/static/skiko.js" }
    }
    body {
        canvas { id = "ComposeTarget" }
        div { id = "root" }
        script(src = "/static/app.js") {}
    }
}

