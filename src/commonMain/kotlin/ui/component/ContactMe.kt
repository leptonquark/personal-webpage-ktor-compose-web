package ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import ui.unit.Spacing

@Composable
fun ContactMe() {
    Text(
        text = "Contact me",
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.headlineMedium,
    )
    Row(horizontalArrangement = Arrangement.spacedBy(Spacing.S)) {
        Text(
            text = "Github",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium,
        )
        Text(
            text = "LinkedIn",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium,
        )
        Text(
            text = "Email",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}