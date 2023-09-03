package me.leptonquark.application

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.html.respondHtml
import io.ktor.server.http.content.staticResources
import io.ktor.server.response.respond
import io.ktor.server.routing.Routing
import io.ktor.server.routing.get
import me.leptonquark.application.data.MainContentService
import me.leptonquark.application.data.ProjectService
import me.leptonquark.application.usecase.STYLES
import me.leptonquark.application.usecase.about.getAboutMessage
import me.leptonquark.application.usecase.contactme.getContactMe
import me.leptonquark.application.usecase.getIndex
import me.leptonquark.application.usecase.getStyles
import me.leptonquark.application.usecase.getTitleText
import me.leptonquark.application.usecase.project.getProjects
import me.leptonquark.application.usecase.respondCss
import route.ApiRoute

fun Routing.router() {
    val mainContentService = MainContentService()
    val projectService = ProjectService()
    get("/") { call.respondHtml(HttpStatusCode.OK) { getIndex(mainContentService) } }
    get(ApiRoute.TITLE) { call.respond(getTitleText(mainContentService)) }
    get(ApiRoute.ABOUT) { call.respond(getAboutMessage(mainContentService)) }
    get(ApiRoute.CONTACT_ME) { call.respond(getContactMe(mainContentService)) }
    get(ApiRoute.PROJECTS) { call.respond(getProjects(projectService)) }
    get(STYLES) { call.respondCss { getStyles() } }
    staticResources("/static", "/")
}

