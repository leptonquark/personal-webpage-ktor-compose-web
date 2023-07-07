import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import org.jetbrains.skiko.wasm.onWasmReady


suspend fun main() {
    val client = HttpClient()
    val about = client.getAbout()
    initializeCompose(about)
}

private suspend fun HttpClient.getAbout() = get("/api/about").bodyAsText()

@OptIn(ExperimentalComposeUiApi::class)
private fun initializeCompose(about: String) {
    onWasmReady {
        CanvasBasedWindow("CV") {
            MainView(about)
        }
    }
}

