package contactme.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import contactme.ContactMeLink
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import ui.unit.IconSize
import ui.unit.Spacing

@Composable
fun ContactMe(links: List<ContactMeLink>, onContactMeClicked: (ContactMeLink) -> Unit, modifier: Modifier = Modifier) {
    if (links.isNotEmpty()) {
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(Spacing.XXS),
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(Spacing.S)) {
                links.forEach { ContactMeIcon(it, onClick = onContactMeClicked) }
            }
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun ContactMeIcon(contactMeLink: ContactMeLink, onClick: (ContactMeLink) -> Unit) {
    Column(
        modifier = Modifier.clickable { onClick(contactMeLink) },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(Spacing.XXS),
    ) {
        Image(
            painter = painterResource(contactMeLink.icon),
            contentDescription = null,
            modifier = Modifier.size(IconSize.M),
        )
        Text(
            text = contactMeLink.name,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelSmall,
        )
    }
}
