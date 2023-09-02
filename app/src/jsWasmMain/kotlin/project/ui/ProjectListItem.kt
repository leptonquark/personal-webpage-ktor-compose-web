package project.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.ColorFilter
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import project.Project
import ui.unit.IconSize
import ui.unit.Spacing

@OptIn(ExperimentalResourceApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ProjectListItem(project: Project) {
    ElevatedCard(modifier = Modifier.fillMaxHeight()) {
        ListItem(
            headlineText = { Text(text = project.projectName) },
            supportingText = { Text(text = project.client) },
            leadingContent = {
                Image(
                    painter = painterResource("static/github.png"),
                    contentDescription = project.projectName,
                    modifier = Modifier.size(IconSize.M),
                    colorFilter = ColorFilter.tint(LocalContentColor.current, BlendMode.SrcAtop)
                )
            },
        )
    }
}
