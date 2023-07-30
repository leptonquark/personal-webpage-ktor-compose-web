package ui

import about.ui.AboutMe
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize().padding(horizontal = Spacing.M),
            verticalArrangement = Arrangement.spacedBy(Spacing.S, Alignment.CenterVertically),
            horizontalArrangement = Arrangement.spacedBy(Spacing.S, Alignment.CenterHorizontally),
        ) {
            item(span = { GridItemSpan(maxLineSpan) }) { about?.let { AboutMe(it) } }
            item(span = { GridItemSpan(maxLineSpan) }) { Spacer(modifier = Modifier.height(Spacing.M)) }
            items(projects) { ProjectCard(project = it) }
            item(span = { GridItemSpan(maxLineSpan) }) { Spacer(modifier = Modifier.height(Spacing.M)) }
            item(span = { GridItemSpan(maxLineSpan) }) {
                ContactMe(
                    links = contactMeLinks,
                    onContactMeClicked = onContactMeClicked
                )
            }
        }
    }
}

