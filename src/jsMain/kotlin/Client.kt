import io.ktor.client.*
import ui.MainScreen


suspend fun main() {
    val mainScreen = MainScreen(HttpClient())
    mainScreen.render()
}
