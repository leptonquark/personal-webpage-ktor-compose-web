package concurrency

import kotlinx.browser.window
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import org.w3c.dom.events.Event

fun <T> eventFlow(type: String, defaultValue: T? = null, onEvent: () -> T) = callbackFlow {
    defaultValue?.let { send(it) }
    val listener: (Event) -> Unit = { _ -> trySend(onEvent()) }

    window.addEventListener(type, listener)
    awaitClose { window.removeEventListener(type, listener) }
}
