package contactme.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.ColorFilter
import contactme.ContactMeLink
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import ui.unit.IconSize

@OptIn(ExperimentalResourceApi::class)
@Composable
fun ContactMeIcon(contactMeLink: ContactMeLink, onClick: (ContactMeLink) -> Unit) {
    IconButton(onClick = { onClick(contactMeLink) }) {
        Image(
            painter = painterResource(contactMeLink.icon),
            contentDescription = contactMeLink.name,
            modifier = Modifier.size(IconSize.M),
            colorFilter = ColorFilter.tint(LocalContentColor.current, BlendMode.SrcAtop)
        )
    }
}
