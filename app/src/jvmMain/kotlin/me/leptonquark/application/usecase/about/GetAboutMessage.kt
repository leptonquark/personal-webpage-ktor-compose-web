package me.leptonquark.application.usecase.about

import about.AboutMessage
import me.leptonquark.application.data.MainConfigurationService

fun getAboutMessage(mainConfigurationService: MainConfigurationService) =
    AboutMessage(mainConfigurationService.configuration?.about)
