import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import di.Singleton
import me.tatarka.inject.annotations.Inject
import org.jetbrains.skiko.wasm.onWasmReady
import ui.MainView

@OptIn(ExperimentalComposeUiApi::class)
@Singleton
class MainScreen @Inject constructor(private val viewModel: MainViewModel) {

    fun initialize() {
        onWasmReady {
            CanvasBasedWindow("CV") {
                val state by viewModel.state.collectAsState()
                MainView(
                    windowClass = state.windowClass,
                    about = state.about,
                    contactMeLinks = state.contactMeLinks,
                    projects = state.projects,
                ) { sendIntent(MainIntent.ContactMeClicked(it)) }
            }
        }
    }

    private fun sendIntent(state: MainIntent) = viewModel.sendIntent(state)
}
