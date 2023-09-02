package me.leptonquark.application.usecase.about

import about.AboutMessage
import me.leptonquark.application.data.MainContentService

fun getAboutMessage(mainContentService: MainContentService) = AboutMessage(mainContentService.mainContent?.about)
