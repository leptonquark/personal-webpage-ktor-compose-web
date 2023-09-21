package me.leptonquark.application.data

import java.net.URL

open class JsonService(private val resourcePath: String) {
    protected val resource: URL? = javaClass.run { getResource(resourcePath) }
}
