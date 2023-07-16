package viewmodel

import ExternalUrlHandler
import data.AboutRepository
import data.ContactMeItem
import data.ContactMeRepository
import di.Singleton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import me.tatarka.inject.annotations.Inject

data class MainState(
    val about: String? = null,
    val contactMeItems: List<ContactMeItem> = emptyList(),
)

sealed interface MainIntent {
    data class ContactMeClicked(val contactMeItem: ContactMeItem) : MainIntent
}


@Singleton
class MainViewModel @Inject constructor(
    private val aboutRepository: AboutRepository,
    private val contactMeRepository: ContactMeRepository,
    private val externalUrlHandler: ExternalUrlHandler,
) {

    private val viewModelScope = CoroutineScope(Dispatchers.Main)

    val state = flow {
        val about = aboutRepository.getAbout()
        val contactMeItems = contactMeRepository.getContactMeItems()
        emit(MainState(about, contactMeItems))
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = MainState()
    )

    fun sendIntent(intent: MainIntent) {
        when (intent) {
            is MainIntent.ContactMeClicked -> externalUrlHandler.navigateTo(intent.contactMeItem.url)
        }
    }

}
