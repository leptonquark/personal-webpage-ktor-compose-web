package me.leptonquark.application.data

import java.net.URL

abstract class JsonService(private val resourcePath: String, private val sampleResourcePath: String) {
    protected val resource: URL? = javaClass.run { getResource(resourcePath) ?: getResource(sampleResourcePath) }
}