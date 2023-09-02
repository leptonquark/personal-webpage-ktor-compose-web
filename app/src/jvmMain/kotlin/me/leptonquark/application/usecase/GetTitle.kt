package me.leptonquark.application.usecase

import me.leptonquark.application.data.MainContentService

private const val DEFAULT_TITLE = "CV"
fun getTitle(mainContentService: MainContentService) = mainContentService.mainContent?.title ?: DEFAULT_TITLE
