import kotlinx.browser.window
import me.tatarka.inject.annotations.Inject

class ExternalUrlHandler @Inject constructor() {
    fun navigateTo(url: String) {
        window.location.href = url
    }
}