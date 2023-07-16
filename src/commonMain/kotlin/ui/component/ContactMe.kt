package ui.component

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
import data.ContactMeItem
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import ui.unit.IconSize
import ui.unit.Spacing

@Composable
fun ContactMe(items: List<ContactMeItem>, onContactMeClicked: (ContactMeItem) -> Unit) {
    Text(
        text = "Contact me",
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.headlineSmall,
    )
    Row(horizontalArrangement = Arrangement.spacedBy(Spacing.S)) {
        items.forEach { ContactMeIcon(it, onClick = onContactMeClicked) }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun ContactMeIcon(contactMeItem: ContactMeItem, onClick: (ContactMeItem) -> Unit) {
    Column(
        modifier = Modifier.clickable { onClick(contactMeItem) },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(Spacing.XXS),
    ) {
        Image(
            painter = painterResource(contactMeItem.icon),
            contentDescription = null,
            modifier = Modifier.size(IconSize.M),
        )
        Text(
            text = contactMeItem.name,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelSmall,
        )
    }
}
