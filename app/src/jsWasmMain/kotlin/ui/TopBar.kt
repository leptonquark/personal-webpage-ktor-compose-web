package ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import contactme.ContactMeLink
import contactme.ui.ContactMeIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TopBar(
    windowClass: WindowClass,
    links: List<ContactMeLink>,
    onContactMeClicked: (ContactMeLink) -> Unit,
) {
    TopAppBar(
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
            links.forEach { ContactMeIcon(it, onClick = onContactMeClicked) }
        },
    )
}
