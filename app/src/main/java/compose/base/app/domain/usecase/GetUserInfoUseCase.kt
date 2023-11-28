package compose.base.app.domain.usecase

import compose.base.app.domain.repository.AuthRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetUserInfoUseCase @Inject constructor(private val authRepository: AuthRepository) {

    suspend operator fun invoke() = authRepository.getUserInfo()
}