package project.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.ColorFilter
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.LoadState
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.rememberImageBitmap
import org.jetbrains.compose.resources.resource
import project.Project
import ui.unit.IconSize

private const val FALLBACK_PROJECT_ICON_RESOURCE = "static/unknown.png"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectListItem(project: Project) {
    ElevatedCard(modifier = Modifier.fillMaxHeight()) {
        ListItem(
            headlineText = { Text(text = project.projectName) },
            supportingText = { Text(text = project.client) },
            leadingContent = { ProjectIcon(project) },
        )
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun ProjectIcon(project: Project) {
    val iconId = project.icon?.takeIf { id -> isValidResource(id) } ?: FALLBACK_PROJECT_ICON_RESOURCE
    Image(
        painter = painterResource(iconId),
        contentDescription = project.projectName,
        modifier = Modifier.size(IconSize.M),
        colorFilter = ColorFilter.tint(LocalContentColor.current, BlendMode.SrcAtop)
    )
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun isValidResource(id: String) = resource(id).rememberImageBitmap() !is LoadState.Error
