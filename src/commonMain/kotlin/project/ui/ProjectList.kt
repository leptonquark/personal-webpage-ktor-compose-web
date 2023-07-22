package project.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import project.Project

@Composable
fun ProjectListView(projects: List<Project>) {
    Text(projects.toString())
}
