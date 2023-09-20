package me.leptonquark.application.usecase.contactme

import contactme.ContactMeLink
import me.leptonquark.application.data.MainConfigurationService

fun getContactMe(mainConfigurationService: MainConfigurationService) =
    mainConfigurationService.configuration?.run {
        contactMe.mapNotNull { url ->
            ContactMeWebsite.fromUrl(url)?.let { website ->
                ContactMeLink(
                    name = website.siteName,
                    url = url,
                    icon = website.icon,
                )
            }
        }
    }.orEmpty()

private enum class ContactMeWebsite {
    Github,
    LinkedIn,
    Twitter, ;

    companion object {
        fun fromUrl(url: String): ContactMeWebsite? {
            return when {
                url.contains("github") -> Github
                url.contains("linkedin") -> LinkedIn
                url.contains("twitter") -> Twitter
                else -> null
            }
        }
    }
}

private val ContactMeWebsite.siteName
    get() = when (this) {
        ContactMeWebsite.Github -> "Github"
        ContactMeWebsite.LinkedIn -> "LinkedIn"
        ContactMeWebsite.Twitter -> "Twitter"
    }

private val ContactMeWebsite.icon
    get() = when (this) {
        ContactMeWebsite.Github -> "static/github.png"
        ContactMeWebsite.LinkedIn -> "static/linkedin.png"
        ContactMeWebsite.Twitter -> "static/twitter.png"
    }
