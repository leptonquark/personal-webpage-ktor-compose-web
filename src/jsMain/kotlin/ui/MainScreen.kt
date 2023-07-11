package ui

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import di.Singleton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import me.tatarka.inject.annotations.Inject
import org.jetbrains.skiko.wasm.onWasmReady
import viewmodel.MainState
import viewmodel.MainViewModel

@Singleton
class MainScreen @Inject constructor(private val viewModel: MainViewModel) {

    private val screenScope = CoroutineScope(Dispatchers.Main)

    init {
        screenScope.launch { viewModel.state.collectLatest { state -> onState(state) } }
    }

    @OptIn(ExperimentalComposeUiApi::class)
    private fun onState(state: MainState) {
        onWasmReady {
            CanvasBasedWindow("CV") {
                MainView(state.about)
            }
        }
    }
}