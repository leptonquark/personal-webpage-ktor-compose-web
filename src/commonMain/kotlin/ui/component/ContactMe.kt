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
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import ui.unit.IconSize
import ui.unit.Spacing

@Composable
fun ContactMe() {
    Text(
        text = "Contact me",
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.headlineSmall,
    )
    Row(horizontalArrangement = Arrangement.spacedBy(Spacing.S)) {
        ContactMeIcon("GitHub", "static/github.png")
        ContactMeIcon("LinkedIn", "static/linkedin.png")
        ContactMeIcon("Email", "static/email.png")
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun ContactMeIcon(name: String, iconResource: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(Spacing.XXS),
    ) {
        Image(
            painter = painterResource(iconResource),
            contentDescription = null,
            modifier = Modifier.size(IconSize.M),
        )
        Text(
            text = name,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelSmall,
        )
    }
}
