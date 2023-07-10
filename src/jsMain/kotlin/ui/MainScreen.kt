package ui

import MainView
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import org.jetbrains.skiko.wasm.onWasmReady

class MainScreen(private val client: HttpClient) {

    private suspend fun HttpClient.getAbout() = get("/api/about").bodyAsText()

    @OptIn(ExperimentalComposeUiApi::class)
    suspend fun render(){
        val about = client.getAbout()
        onWasmReady {
            CanvasBasedWindow("CV") {
                MainView(about)
            }
        }
    }
}