package ui

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import di.Singleton
import me.tatarka.inject.annotations.Inject
import org.jetbrains.skiko.wasm.onWasmReady
import ui.component.MainView
import viewmodel.MainIntent
import viewmodel.MainViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Singleton
class MainScreen @Inject constructor(private val viewModel: MainViewModel) {

    fun initialize() {
        onWasmReady {
            CanvasBasedWindow("CV") {
                val state by viewModel.state.collectAsState()
                MainView(
                    about = state.about,
                    contactMeItems = state.contactMeItems,
                    onContactMeClicked = { sendIntent(MainIntent.ContactMeClicked(it)) }
                )
            }
        }
    }

    private fun sendIntent(state: MainIntent) = viewModel.sendIntent(state)
}
