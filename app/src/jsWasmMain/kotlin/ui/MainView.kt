package ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import contactme.ContactMeLink
import project.Project
import ui.theme.AppTheme
import ui.unit.Spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MainView(
    windowClass: WindowClass,
    title: String?,
    about: String?,
    contactMeLinks: List<ContactMeLink>,
    projects: List<Project>,
    onContactMeClicked: (ContactMeLink) -> Unit,
) {
    AppTheme {
        Scaffold(
            topBar = { TopBar(windowClass, title, contactMeLinks, onContactMeClicked) },
            bottomBar = { BottomBar() },
        ) { paddingValues ->
            MainContent(
                windowClass = windowClass,
                modifier = Modifier.fillMaxSize().padding(paddingValues).padding(horizontal = Spacing.M),
                about = about,
                projects = projects
            )
        }
    }
}
