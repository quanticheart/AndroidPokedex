package com.quanticheart.domain.usecase.user

import com.quanticheart.domain.model.user.NewUser
import com.quanticheart.domain.repository.UserRepository

class CreateUserUseCase(private val repository: UserRepository) {
    suspend operator fun invoke(newUser: NewUser) = repository.create(newUser)
}
