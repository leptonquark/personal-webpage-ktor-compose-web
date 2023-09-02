package ui

import about.ui.AboutMe
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import project.Project
import project.ui.ProjectCard
import project.ui.ProjectListItem
import ui.unit.Spacing

@Composable
internal fun MainContent(
    windowClass: WindowClass,
    modifier: Modifier = Modifier,
    about: String?,
    projects: List<Project>,
) {
    when (windowClass) {
        WindowClass.Compact -> MainContentCompact(modifier = modifier, about, projects)
        WindowClass.Medium,
        WindowClass.Expanded,
        -> MainContentLarge(modifier = modifier, about, projects)
    }
}

@Composable
private fun MainContentCompact(modifier: Modifier = Modifier, about: String?, projects: List<Project>) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(Spacing.S, Alignment.CenterVertically),
    ) {
        item { Spacer(modifier = Modifier.height(Spacing.S)) }
        item { about?.let { AboutMe(it) } }
        item { Spacer(modifier = Modifier.height(Spacing.M)) }
        items(projects) { ProjectListItem(project = it) }
        item { Spacer(modifier = Modifier.height(Spacing.M)) }
    }
}

@Composable
private fun MainContentLarge(modifier: Modifier = Modifier, about: String?, projects: List<Project>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier,
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