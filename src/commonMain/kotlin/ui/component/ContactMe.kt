package ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import ui.unit.IconSize
import ui.unit.Spacing

@OptIn(ExperimentalResourceApi::class)
@Composable
fun ContactMe() {
    Text(
        text = "Contact me",
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.headlineMedium,
    )
    Row(horizontalArrangement = Arrangement.spacedBy(Spacing.S)) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(Spacing.XXS),
        ) {
            Image(
                painter = painterResource("static/github.png"),
                contentDescription = null,
                modifier = Modifier.size(IconSize.M),
            )
            Text(
                text = "GitHub",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelSmall,
            )
        }
        Text(
            text = "LinkedIn",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelSmall,
        )
        Text(
            text = "Email",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelSmall,
        )
    }
}