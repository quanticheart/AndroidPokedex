package com.quanticheart.domain.usecase.user

import com.quanticheart.domain.model.user.Credentials
import com.quanticheart.domain.model.user.User
import com.quanticheart.domain.model.user.exceptions.EmailBlankException
import com.quanticheart.domain.model.user.exceptions.PasswordBlankException
import com.quanticheart.domain.repository.UserRepository

class LoginUseCase(private val repository: UserRepository) {
    suspend operator fun invoke(userLogin: Credentials): Result<User> {
        return when {
            userLogin.email.isBlank() -> Result.failure(EmailBlankException())
            userLogin.password.isBlank() -> Result.failure(PasswordBlankException())
            else -> repository.login(userLogin)
        }
    }
}