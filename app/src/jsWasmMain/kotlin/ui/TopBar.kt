package ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.typography
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
internal fun TopBar(
    windowClass: WindowClass,
    contactMeLinks: List<ContactMeLink>,
    onContactMeClicked: (ContactMeLink) -> Unit,
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        border = BorderStroke(BorderWidth.S, Color.Black),
    ) {
        Row(
            modifier = Modifier.padding(Spacing.S).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "Justin Salér",
                style = when (windowClass) {
                    WindowClass.Compact -> typography.headlineMedium
                    WindowClass.Medium,
                    WindowClass.Expanded,
                    -> typography.headlineLarge
                },
            )
            ContactMe(
                links = contactMeLinks,
                onContactMeClicked = onContactMeClicked
            )
        }
    }
}
