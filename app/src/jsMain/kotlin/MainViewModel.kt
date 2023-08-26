import about.AboutRepository
import contactme.ContactMeLink
import contactme.ContactMeRepository
import di.Singleton
import kotlinx.browser.window
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import me.tatarka.inject.annotations.Inject
import project.Project
import project.ProjectRepository
import ui.WindowClass

data class MainState(
    val about: String? = null,
    val contactMeLinks: List<ContactMeLink> = emptyList(),
    val projects: List<Project> = emptyList(),
    val windowClass: WindowClass = WindowClass.getCurrent(),
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
    private val windowClass = MutableStateFlow(WindowClass.getCurrent())

    val state = combine(aboutFlow, contactMe, projects, windowClass) { about, links, projects, windowClass ->
        MainState(
            about,
            links,
            projects,
            windowClass
        )
    }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = MainState()
        )

    init {
        window.addEventListener("resize", { _ -> windowClass.value = WindowClass.getCurrent() })
    }

    fun sendIntent(intent: MainIntent) {
        when (intent) {
            is MainIntent.ContactMeClicked -> externalUrlHandler.navigateTo(intent.contactMeLink.url)
        }
    }
}
