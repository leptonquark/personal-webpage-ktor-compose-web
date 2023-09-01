package me.justin.application.util

import kotlinx.serialization.json.Json

inline fun <reified T> String.fromJson() = Json.decodeFromString<T>(this)