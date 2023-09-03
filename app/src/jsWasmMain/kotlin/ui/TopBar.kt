package ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import contactme.ContactMeLink
import contactme.ui.ContactMeIcon
import ui.unit.BorderWidth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TopBar(
    windowClass: WindowClass,
    title: String?,
    links: List<ContactMeLink>,
    onContactMeClicked: (ContactMeLink) -> Unit,
) {
    Column {
        TopAppBar(
            title = { title?.let { Text(text = it, style = windowClass.titleTextStyle) } },
            actions = { links.forEach { ContactMeIcon(it, onClick = onContactMeClicked) } },
        )
        Divider(modifier = Modifier.fillMaxWidth(), thickness = BorderWidth.S, color = LocalContentColor.current)
    }
}

private val WindowClass.titleTextStyle
    @Composable get() = when (this) {
        WindowClass.Compact -> typography.headlineMedium
        WindowClass.Medium,
        WindowClass.Expanded,
        -> typography.headlineLarge
    }
