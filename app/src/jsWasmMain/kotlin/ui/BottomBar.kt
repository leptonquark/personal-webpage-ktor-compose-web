package ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import ui.unit.Spacing

@Composable
fun BottomBar() {
    val message = buildAnnotatedString {
        append(
            "This webpage uses Kotlin Multiplatform, KTOR, Kotlin/JS, Compose for Web (experimental) and Kotlin/WASM (experimental). The source code is "
        )
        withStyle(SpanStyle(color = MaterialTheme.colorScheme.primary)) {
            append("available on GitHub.")
        }
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RectangleShape
    ) {
        Text(
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().padding(Spacing.XS),
            text = message,
            style = MaterialTheme.typography.bodySmall,
        )
    }
}
