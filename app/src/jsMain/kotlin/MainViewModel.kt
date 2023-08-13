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
import project.Project
import project.ProjectRepository

data class MainState(
    val about: String? = null,
    val contactMeLinks: List<ContactMeLink> = emptyList(),
    val projects: List<Project> = emptyList(),
)

sealed interface MainIntent {
    data class ContactMeClicked(val contactMeLink: ContactMeLink) : MainIntent
}


@Singleton
class MainViewModel @Inject constructor(
    private val aboutRepository: AboutRepository,
    private val contactMeRepository: ContactMeRepository,
    private val projectRepository: ProjectRepository,
    private val externalUrlHandler: ExternalUrlHandler,
) {
    private val viewModelScope = CoroutineScope(Dispatchers.Main)

    private val aboutFlow = flow { emit(aboutRepository.getAbout()) }
    private val contactMe = flow { emit(contactMeRepository.getContactMeLinks()) }
    private val projects = flow { emit(projectRepository.getProjects()) }

    val state = combine(aboutFlow, contactMe, projects) { about, links, projects -> MainState(about, links, projects) }
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
