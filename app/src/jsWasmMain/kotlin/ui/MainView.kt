package ui

import about.ui.AboutMe
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ui.theme.AppTheme
import contactme.ContactMeLink
import project.Project
import project.ui.ProjectCard
import project.ui.ProjectListItem
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
    AppTheme {
        Scaffold(
            topBar = { TopBar(windowClass, contactMeLinks, onContactMeClicked) },
            bottomBar = { BottomBar() },
        ) { paddingValues ->
            when (windowClass) {
                WindowClass.Compact -> MainContentCompact(paddingValues, about, projects)
                WindowClass.Medium,
                WindowClass.Expanded,
                -> MainContentLarge(paddingValues, about, projects)

            }
        }
    }
}

@Composable
private fun MainContentCompact(
    paddingValues: PaddingValues,
    about: String?,
    projects: List<Project>,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(paddingValues).padding(horizontal = Spacing.M),
        verticalArrangement = Arrangement.spacedBy(Spacing.S, Alignment.CenterVertically),
    ) {
        item { Spacer(modifier = Modifier.height(Spacing.S)) }
        item{ about?.let { AboutMe(it) } }
        item { Spacer(modifier = Modifier.height(Spacing.M)) }
        items(projects) { ProjectListItem(project = it) }
        item { Spacer(modifier = Modifier.height(Spacing.M)) }
    }
}

@Composable
private fun MainContentLarge(
    paddingValues: PaddingValues,
    about: String?,
    projects: List<Project>,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize().padding(paddingValues).padding(horizontal = Spacing.M),
        verticalArrangement = Arrangement.spacedBy(Spacing.S, Alignment.CenterVertically),
        horizontalArrangement = Arrangement.spacedBy(Spacing.S, Alignment.CenterHorizontally),
    ) {
        item(span = { GridItemSpan(maxLineSpan) }) { Spacer(modifier = Modifier.height(Spacing.S)) }
        item(span = { GridItemSpan(maxLineSpan) }) { about?.let { AboutMe(it) } }
        item(span = { GridItemSpan(maxLineSpan) }) { Spacer(modifier = Modifier.height(Spacing.M)) }
        items(projects) { ProjectCard(project = it) }
        item(span = { GridItemSpan(maxLineSpan) }) { Spacer(modifier = Modifier.height(Spacing.M)) }
    }
}
