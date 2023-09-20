package ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import ui.unit.Spacing

private const val BOTTOM_BAR_MESSAGE =
    "This webpage uses Kotlin Multiplatform, KTOR and Compose for Web (experimental)."

@Composable
fun BottomBar(onBottomBarClicked: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onBottomBarClicked,
            ),
        shape = RectangleShape,
    ) {
        Text(
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().padding(Spacing.XS),
            text = BOTTOM_BAR_MESSAGE,
            style = MaterialTheme.typography.bodySmall,
        )
    }
}
