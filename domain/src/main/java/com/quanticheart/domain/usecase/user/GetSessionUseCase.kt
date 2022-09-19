package com.quanticheart.domain.usecase.user

import com.quanticheart.domain.repository.UserRepository

class GetSessionUseCase(private val repository: UserRepository) {
    suspend operator fun invoke() = repository.getSession()
}