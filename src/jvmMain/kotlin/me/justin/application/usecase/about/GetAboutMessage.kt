package me.justin.application.usecase.about

import about.AboutMessage
import me.justin.application.ConfigurationService

fun ConfigurationService.getAboutMessage() = AboutMessage(about)
