package ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import contactme.ContactMeLink
import contactme.ui.ContactMeIcon
import ui.unit.BorderWidth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TopBar(
    windowClass: WindowClass,
    links: List<ContactMeLink>,
    onContactMeClicked: (ContactMeLink) -> Unit,
) {
    Column {
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
            actions = { links.forEach { ContactMeIcon(it, onClick = onContactMeClicked) } },
        )
        Divider(modifier = Modifier.fillMaxWidth(), thickness = BorderWidth.S, color = LocalContentColor.current)
    }
}
