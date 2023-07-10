import io.ktor.client.*
import ui.MainScreen
import viewmodel.MainViewModel


suspend fun main() {
    val viewModel = MainViewModel(HttpClient())
    val mainScreen = MainScreen(viewModel)
    mainScreen.render()
}
