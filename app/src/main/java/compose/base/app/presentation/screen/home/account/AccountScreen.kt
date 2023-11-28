package compose.base.app.presentation.screen.home.account

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import compose.base.app.config.util.rememberLifecycleEvent
import compose.base.app.presentation.ui.theme.CineTheme
import compose.base.app.presentation.ui.theme.spacing
import compose.base.app.presentation.ui.theme.textStyle

@ExperimentalMaterial3Api
@Composable
fun AccountRoute(
    modifier: Modifier = Modifier,
    viewModel: AccountViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(uiState.navigateToHome) {

    }
    if (rememberLifecycleEvent() == Lifecycle.Event.ON_STOP) viewModel.onScreenFinish()
    AccountScreen(
        modifier = modifier,
        uiState = uiState,
        uiEvent = viewModel::handleEvent,
    )
}

@ExperimentalMaterial3Api
@Composable
fun AccountScreen(
    modifier: Modifier = Modifier,
    uiState: AccountUiState,
    uiEvent: (AccountUiEvent) -> Unit,
) {
    Scaffold {
        Column(
            modifier = modifier
                .padding(it)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            uiState.userName?.let { username ->
                Column(
                    modifier = modifier.padding(MaterialTheme.spacing.extraLarge),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "Logged in as,",
                        style = MaterialTheme.textStyle.userNameLabel,
                    )
                    Text(
                        text = username,
                        style = MaterialTheme.textStyle.userName,
                    )
                }
                Button(
                    modifier = modifier.padding(MaterialTheme.spacing.extraLarge),
                    onClick = {
                        uiEvent(AccountUiEvent.OnLogout)
                    },
                    shape = MaterialTheme.shapes.medium,
                ) {
                    Text(text = "Logout")
                }
            }
        }
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun AccountScreenPreview() {
    CineTheme {
        AccountScreen(uiState = AccountUiState(), uiEvent = {})
    }
}