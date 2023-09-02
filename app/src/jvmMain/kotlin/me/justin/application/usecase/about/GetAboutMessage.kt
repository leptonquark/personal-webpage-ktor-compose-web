package me.justin.application.usecase.about

import about.AboutMessage
import me.justin.application.data.MainContentService

fun getAboutMessage(mainContentService: MainContentService) = AboutMessage(mainContentService.mainContent?.about)
