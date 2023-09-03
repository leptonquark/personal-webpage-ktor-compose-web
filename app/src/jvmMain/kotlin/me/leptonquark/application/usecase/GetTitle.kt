package me.leptonquark.application.usecase

import me.leptonquark.application.data.MainContentService
import title.TitleText

private const val DEFAULT_TITLE = "CV"
internal fun getTitle(mainContentService: MainContentService) = mainContentService.mainContent?.title ?: DEFAULT_TITLE
fun getTitleText(mainContentService: MainContentService) = TitleText(getTitle(mainContentService))
