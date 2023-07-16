package me.justin.application.usecase

import data.AboutMessage
import me.justin.application.ConfigurationService

fun ConfigurationService.getAboutMessage() = AboutMessage(about)