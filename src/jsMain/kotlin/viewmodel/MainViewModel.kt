package viewmodel

import ExternalUrlHandler
import data.AboutRepository
import di.Singleton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import me.tatarka.inject.annotations.Inject

data class MainState(val about: String? = null)

sealed interface MainIntent {
    data object ContactMeClicked : MainIntent
}


@Singleton
class MainViewModel @Inject constructor(
    private val aboutRepository: AboutRepository,
    private val externalUrlHandler: ExternalUrlHandler,
) {

    private val viewModelScope = CoroutineScope(Dispatchers.Main)

    val state = flow {
        val about = aboutRepository.getAbout()
        emit(MainState(about))
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = MainState()
    )

    fun sendIntent(intent: MainIntent) {
        when (intent) {
            MainIntent.ContactMeClicked -> externalUrlHandler.navigateTo("https://www.linkedin.com/in/justinsaler/")
        }
    }

}
