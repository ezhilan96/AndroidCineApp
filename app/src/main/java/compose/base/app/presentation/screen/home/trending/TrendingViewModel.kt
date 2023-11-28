package compose.base.app.presentation.screen.home.favorite

import androidx.lifecycle.ViewModel
import compose.base.app.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class FavoriteUiState(
    val isLoading: Boolean = false,
    val userName: String = "",
    val error: String = "",
    val navigateToHome: Boolean = false,
)

sealed class FavoriteUiEvent {

    data class OnUserNameChange(val name: String) : FavoriteUiEvent()
    object OnSubmit : FavoriteUiEvent()
}

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val favoriteUseCase: LoginUseCase) :
    ViewModel() {

    private var _favoriteUiState = MutableStateFlow(FavoriteUiState())
    val uiState: StateFlow<FavoriteUiState>
        get() = _favoriteUiState

    fun handleEvent(event: FavoriteUiEvent) {
        if (!_favoriteUiState.value.isLoading) {
            when (event) {
                is FavoriteUiEvent.OnUserNameChange -> _favoriteUiState.update { currentState ->
                    currentState.copy(userName = event.name)
                }

                FavoriteUiEvent.OnSubmit -> _favoriteUiState.update { currentState ->
                    currentState.copy(navigateToHome = true)
                }
            }
        }
    }

    fun onScreenFinish() {
        _favoriteUiState.update { currentState ->
            currentState.copy(navigateToHome = false)
        }
    }
}