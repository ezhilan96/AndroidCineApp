package compose.base.app.presentation.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@ExperimentalMaterial3Api
@Composable
fun NoInternetScreen(modifier: Modifier = Modifier) {
    Scaffold {
        Box(modifier = modifier.padding(it).fillMaxSize()) {
            Text(
                modifier = modifier.align(Alignment.Center),
                text = "No Internet",
            )
        }
    }
}