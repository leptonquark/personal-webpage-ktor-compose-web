package ui

import about.ui.AboutMe
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import contactme.ContactMeLink
import contactme.ui.ContactMe
import project.Project
import project.ui.ProjectListView
import ui.unit.Spacing

@Composable
fun MainView(
    about: String?,
    contactMeLinks: List<ContactMeLink>,
    projects: List<Project>,
    onContactMeClicked: (ContactMeLink) -> Unit
) {
    MaterialTheme {
        Column(
            modifier = Modifier.fillMaxSize().padding(horizontal = Spacing.M).verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(Spacing.S, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            about?.let { AboutMe(it) }
            ContactMe(links = contactMeLinks, onContactMeClicked = onContactMeClicked)
            ProjectListView(projects = projects)
        }
    }
}

