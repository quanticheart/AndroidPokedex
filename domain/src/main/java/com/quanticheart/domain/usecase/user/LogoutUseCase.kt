package com.quanticheart.domain.usecase.user

import com.quanticheart.domain.repository.UserRepository

class LogoutUseCase(private val repository: UserRepository) {
    suspend operator fun invoke() = repository.logout()
}