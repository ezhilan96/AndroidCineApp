package compose.base.app.presentation.screen.home.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import compose.base.app.domain.usecase.GetUserInfoUseCase
import compose.base.app.domain.usecase.LogoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AccountUiState(
    val isLoading: Boolean = false,
    val userName: String? = null,
    val error: String = "",
    val navigateToHome: Boolean = false,
)

sealed class AccountUiEvent {

    data class OnUserNameChange(val name: String) : AccountUiEvent()
    data object OnLogout : AccountUiEvent()
}

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val getUserInfo: GetUserInfoUseCase,
    private val logout: LogoutUseCase,
) : ViewModel() {

    private var _accountUiState = MutableStateFlow(AccountUiState())
    val uiState: StateFlow<AccountUiState>
        get() = _accountUiState

    init {
        viewModelScope.launch {
            getUserInfo().collect { userName ->
                _accountUiState.update {
                    it.copy(userName = userName)
                }
            }
        }
    }

    fun handleEvent(event: AccountUiEvent) {
        if (!_accountUiState.value.isLoading) {
            when (event) {
                is AccountUiEvent.OnUserNameChange -> {}

                AccountUiEvent.OnLogout -> logout()
            }
        }
    }

    fun onScreenFinish() {
        _accountUiState.update { currentState ->
            currentState.copy(navigateToHome = false)
        }
    }
}