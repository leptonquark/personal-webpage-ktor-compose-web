package project.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import project.Project
import ui.unit.Spacing

@Composable
fun ProjectCard(project: Project) {
    ElevatedCard {
        Column(modifier = Modifier.padding(Spacing.S)) {
            Text(text = project.projectName, style = MaterialTheme.typography.headlineMedium)
            Text(text = project.client, style = MaterialTheme.typography.titleMedium)
            Text(text = project.description, style = MaterialTheme.typography.bodyMedium)
        }

    }
}
