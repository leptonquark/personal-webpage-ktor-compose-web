package ui

import about.ui.AboutMe
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import contactme.ContactMeLink
import contactme.ui.ContactMe
import project.Project
import project.ui.ProjectCard
import ui.unit.Spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MainView(
    windowClass: WindowClass,
    about: String?,
    contactMeLinks: List<ContactMeLink>,
    projects: List<Project>,
    onContactMeClicked: (ContactMeLink) -> Unit,
) {
    MaterialTheme {
        Scaffold(
            topBar = { TopBar(windowClass, contactMeLinks, onContactMeClicked) },
        ) { paddingValues ->
            MainContent(paddingValues, windowClass, about, projects, contactMeLinks, onContactMeClicked)
        }
    }
}

@Composable
private fun MainContent(
    paddingValues: PaddingValues,
    windowClass: WindowClass,
    about: String?,
    projects: List<Project>,
    contactMeLinks: List<ContactMeLink>,
    onContactMeClicked: (ContactMeLink) -> Unit,
) {
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
        if (windowClass == WindowClass.Compact) {
            item(span = { GridItemSpan(maxLineSpan) }) {
                ContactMe(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(align = Alignment.CenterHorizontally),
                    links = contactMeLinks,
                    onContactMeClicked = onContactMeClicked
                )
            }
            item(span = { GridItemSpan(maxLineSpan) }) { Spacer(modifier = Modifier.height(Spacing.S)) }
        }
    }
}

