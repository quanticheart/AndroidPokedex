package com.quanticheart.domain.usecase.user

import com.quanticheart.domain.model.user.exceptions.EmailBlankException
import com.quanticheart.domain.repository.UserRepository

class RecoverPasswordUseCase(private val repository: UserRepository) {
    suspend operator fun invoke(email: String): Result<String> {
        return when {
            email.isBlank() -> Result.failure(EmailBlankException())
            else -> repository.recover(email)
        }
    }
}