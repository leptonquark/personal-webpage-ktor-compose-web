package ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import contactme.ContactMeLink
import contactme.ui.ContactMe
import ui.unit.BorderWidth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TopBar(
    windowClass: WindowClass,
    contactMeLinks: List<ContactMeLink>,
    onContactMeClicked: (ContactMeLink) -> Unit,
) {
    TopAppBar(
        modifier = Modifier.border(BorderStroke(BorderWidth.S, Color.Black)),
        title = {
            Text(
                text = "Justin Salér",
                style = when (windowClass) {
                    WindowClass.Compact -> typography.headlineMedium
                    WindowClass.Medium,
                    WindowClass.Expanded,
                    -> typography.headlineLarge
                },
            )
        },
        actions = {
            ContactMe(
                links = contactMeLinks,
                onContactMeClicked = onContactMeClicked
            )
        },
    )
    /*
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
    }*/
}
