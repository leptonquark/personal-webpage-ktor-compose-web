import about.AboutRepository
import contactme.ContactMeLink
import contactme.ContactMeRepository
import di.Singleton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import me.tatarka.inject.annotations.Inject

data class MainState(
    val about: String? = null,
    val contactMeLinks: List<ContactMeLink> = emptyList(),
)

sealed interface MainIntent {
    data class ContactMeClicked(val contactMeLink: ContactMeLink) : MainIntent
}


@Singleton
class MainViewModel @Inject constructor(
    private val aboutRepository: AboutRepository,
    private val contactMeRepository: ContactMeRepository,
    private val externalUrlHandler: ExternalUrlHandler,
) {
    private val viewModelScope = CoroutineScope(Dispatchers.Main)

    private val aboutFlow = flow { emit(aboutRepository.getAbout()) }
    private val contactMe = flow { emit(contactMeRepository.getContactMeLinks()) }

    val state = combine(aboutFlow, contactMe) { about, links -> MainState(about, links) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = MainState()
        )

    fun sendIntent(intent: MainIntent) {
        when (intent) {
            is MainIntent.ContactMeClicked -> externalUrlHandler.navigateTo(intent.contactMeLink.url)
        }
    }
}
