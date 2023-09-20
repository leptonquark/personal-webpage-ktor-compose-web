import about.AboutRepository
import concurrency.eventFlow
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
import title.TitleRepository
import ui.WindowClass

data class MainState(
    val windowClass: WindowClass = WindowClass(),
    val title: String? = null,
    val about: String? = null,
    val contactMeLinks: List<ContactMeLink> = emptyList(),
    val projects: List<Project> = emptyList(),
)

sealed interface MainIntent {
    data class ContactMeClicked(val contactMeLink: ContactMeLink) : MainIntent
    data object BottomBarClicked : MainIntent
}


private const val SOURCE_CODE_URL = "https://github.com/leptonquark/personal-webpage-ktor-compose-web"

@Singleton
class MainViewModel @Inject constructor(
    private val titleRepository: TitleRepository,
    private val aboutRepository: AboutRepository,
    private val contactMeRepository: ContactMeRepository,
    private val projectRepository: ProjectRepository,
    private val externalUrlHandler: ExternalUrlHandler,
) {
    private val viewModelScope = CoroutineScope(Dispatchers.Main)

    private val windowClass = eventFlow("resize", WindowClass()) { WindowClass() }

    private val title = flow { emit(titleRepository.getTitle()) }
    private val about = flow { emit(aboutRepository.getAbout()) }
    private val contactMe = flow { emit(contactMeRepository.getContactMeLinks()) }
    private val projects = flow { emit(projectRepository.getProjects()) }

    val state = combine(windowClass, title, about, contactMe, projects) { windowClass, title, about, links, projects ->
        MainState(
            windowClass,
            title,
            about,
            links,
            projects,
        )
    }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = MainState()
        )

    fun sendIntent(intent: MainIntent) {
        when (intent) {
            is MainIntent.ContactMeClicked -> externalUrlHandler.navigateTo(intent.contactMeLink.url)
            MainIntent.BottomBarClicked -> externalUrlHandler.navigateTo(SOURCE_CODE_URL)
        }
    }
}
