package ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


private const val ABOUT_ME_MAX_WIDTH = 600

@Composable
fun MainView(about: String?) {
    MaterialTheme {
        Column(
            modifier = Modifier.fillMaxSize().padding(horizontal = 32.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            about?.let { AboutMe(it) }
        }
    }
}

@Composable
private fun AboutMe(about: String) {
    Text(
        text = "About me",
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.headlineMedium,
    )
    Text(
        modifier = Modifier.widthIn(max = ABOUT_ME_MAX_WIDTH.dp),
        text = about,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.bodyMedium,
    )
}
