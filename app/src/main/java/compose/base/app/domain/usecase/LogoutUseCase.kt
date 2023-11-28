package compose.base.app.domain.usecase

import compose.base.app.domain.repository.AuthRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LogoutUseCase @Inject constructor(private val authRepository: AuthRepository) {

    operator fun invoke() = authRepository.logout()
}