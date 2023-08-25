package ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import contactme.ContactMeLink
import contactme.ui.ContactMe
import ui.unit.BorderWidth
import ui.unit.Spacing

@Composable
internal fun TopBar(contactMeLinks: List<ContactMeLink>, onContactMeClicked: (ContactMeLink) -> Unit) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        border = BorderStroke(BorderWidth.S, Color.Black),
    ) {
        Box(
            modifier = Modifier.padding(vertical = Spacing.S, horizontal = Spacing.M).fillMaxWidth(),
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "Justin Salér",
                style = MaterialTheme.typography.headlineLarge
            )
            ContactMe(
                modifier = Modifier.align(Alignment.CenterEnd),
                links = contactMeLinks,
                onContactMeClicked = onContactMeClicked
            )
        }
    }
}
