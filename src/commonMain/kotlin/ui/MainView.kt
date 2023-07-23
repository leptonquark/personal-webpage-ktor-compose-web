package ui

import about.ui.AboutMe
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import contactme.ContactMeLink
import contactme.ui.ContactMe
import project.Project
import project.ui.ProjectCard
import ui.unit.Spacing

@Composable
fun MainView(
    about: String?,
    contactMeLinks: List<ContactMeLink>,
    projects: List<Project>,
    onContactMeClicked: (ContactMeLink) -> Unit
) {
    MaterialTheme {
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(horizontal = Spacing.M),
            verticalArrangement = Arrangement.spacedBy(Spacing.S, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            item { about?.let { AboutMe(it) } }
            item { ContactMe(links = contactMeLinks, onContactMeClicked = onContactMeClicked) }
            items(projects) { ProjectCard(project = it) }
        }
    }
}

