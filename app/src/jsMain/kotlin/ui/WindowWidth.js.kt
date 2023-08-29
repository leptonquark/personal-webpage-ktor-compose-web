package ui

import kotlinx.browser.document
import kotlinx.browser.window

actual val windowWidth get(): Int = document.documentElement?.clientWidth ?: window.innerWidth
