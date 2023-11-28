package compose.base.app.domain.usecase

import compose.base.app.config.util.NetworkResponse
import compose.base.app.data.model.request.LoginRequest
import compose.base.app.data.model.response.LoginResponse
import compose.base.app.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginUseCase @Inject constructor(
    private val repo: AuthRepository
) {
    suspend operator fun invoke(userName: String): Flow<NetworkResponse<LoginResponse>> {
        repo.saveLoginInfo(userName)
        return repo.login(LoginRequest(userName = userName)).onEach { response ->
            if (response is NetworkResponse.Success) {
                repo.saveLoginInfo(userName = response.responseData.token)
            }
        }
    }
}