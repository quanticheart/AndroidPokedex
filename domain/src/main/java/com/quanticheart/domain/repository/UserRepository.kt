package com.quanticheart.domain.repository

import com.quanticheart.domain.model.user.Credentials
import com.quanticheart.domain.model.user.NewUser
import com.quanticheart.domain.model.user.User

interface UserRepository {
    suspend fun getSession(): Result<User>
    suspend fun login(credentials: Credentials): Result<User>
    suspend fun create(data: NewUser): Result<User>
    suspend fun recover(email: String): Result<String>
    suspend fun update(password: String): Result<String>
    suspend fun logout(): Result<Boolean>
}
