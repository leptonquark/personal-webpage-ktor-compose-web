import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun MainView(about: String) {
    Column {
        Text(text = about)
    }
}
