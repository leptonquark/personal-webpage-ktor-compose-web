package me.justin.application.usecase

import kotlinx.html.HTML
import kotlinx.html.body
import kotlinx.html.canvas
import kotlinx.html.div
import kotlinx.html.head
import kotlinx.html.id
import kotlinx.html.script
import kotlinx.html.title
import me.justin.application.data.ConfigurationService

fun HTML.getIndex(configurationService: ConfigurationService) {
    head {
        title(configurationService.title)
        script { src = "/static/skiko.js" }
    }
    body {
        canvas { id = "ComposeTarget" }
        div { id = "root" }
        script(src = "/static/CV.js") {}
    }
}
