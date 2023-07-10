package ui

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import org.jetbrains.skiko.wasm.onWasmReady
import viewmodel.MainViewModel

class MainScreen(private val viewModel: MainViewModel) {

    @OptIn(ExperimentalComposeUiApi::class)
    suspend fun render(){
        val about = viewModel.getAbout()
        onWasmReady {
            CanvasBasedWindow("CV") {
                MainView(about)
            }
        }
    }
}