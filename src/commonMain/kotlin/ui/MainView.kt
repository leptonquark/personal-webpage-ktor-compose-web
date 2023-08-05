package ui

import about.ui.AboutMe
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import contactme.ContactMeLink
import contactme.ui.ContactMe
import project.Project
import project.ui.ProjectCard
import ui.unit.BorderWidth
import ui.unit.Spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainView(
    about: String?,
    contactMeLinks: List<ContactMeLink>,
    projects: List<Project>,
    onContactMeClicked: (ContactMeLink) -> Unit,
) {
    MaterialTheme {
        Scaffold(
            topBar = { TopBar() },
        ) { paddingValues ->
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize().padding(paddingValues).padding(horizontal = Spacing.M),
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
                        onContactMeClicked = onContactMeClicked,
                    )
                }
            }
        }
    }
}

@Composable
private fun TopBar() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        border = BorderStroke(BorderWidth.S, Color.Black),
    ) {
        Row(
            modifier = Modifier.padding(vertical = Spacing.S).fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            Text("Justin Salér", style = MaterialTheme.typography.headlineLarge)
        }
    }
}
