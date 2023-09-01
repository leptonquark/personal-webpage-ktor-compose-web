package me.justin.application.usecase

import me.justin.application.data.MainContentService

private const val DEFAULT_TITLE = "CV"
fun getTitle(mainContentService: MainContentService) = mainContentService.mainContent?.title ?: DEFAULT_TITLE
