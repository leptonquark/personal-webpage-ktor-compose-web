package ui

import kotlinx.browser.document
import kotlinx.browser.window

actual fun getWindowWidth(): Int = document.documentElement?.clientWidth ?: window.innerWidth
