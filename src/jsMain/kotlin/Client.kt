import data.AboutRepository
import io.ktor.client.*
import ui.MainScreen
import viewmodel.MainViewModel


suspend fun main() {
    val repository = AboutRepository(HttpClient())
    val viewModel = MainViewModel(repository)
    val mainScreen = MainScreen(viewModel)
    mainScreen.render()
}
