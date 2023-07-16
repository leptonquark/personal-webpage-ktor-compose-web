package me.justin.application.usecase

import data.ContactMeLink
import me.justin.application.ConfigurationService

fun ConfigurationService.getContactMe(): List<ContactMeLink> = contactMe