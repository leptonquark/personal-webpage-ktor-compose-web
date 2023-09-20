package me.leptonquark.application.usecase

import me.leptonquark.application.data.MainConfigurationService
import title.TitleText

private const val DEFAULT_TITLE = "CV"
internal fun getTitle(mainConfigurationService: MainConfigurationService) =
    mainConfigurationService.configuration?.title ?: DEFAULT_TITLE

fun getTitleText(mainConfigurationService: MainConfigurationService) = TitleText(getTitle(mainConfigurationService))
