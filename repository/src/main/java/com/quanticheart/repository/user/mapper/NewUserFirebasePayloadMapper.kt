package com.quanticheart.repository.user.mapper

import com.quanticheart.domain.model.user.NewUser
import com.quanticheart.domain.model.user.User
import com.quanticheart.repository.user.models.NewUserFirebasePayload

object NewUserFirebasePayloadMapper {

    fun mapToNewUser(newUserFirebasePayload: NewUserFirebasePayload) = NewUser(
        name = newUserFirebasePayload.name ?: "",
        email = newUserFirebasePayload.email ?: "",
        password = newUserFirebasePayload.password ?: ""
    )

    fun mapToNewUserFirebasePayload(newUser: NewUser) = NewUserFirebasePayload(
        name = newUser.name,
        email = newUser.email,
        password = newUser.password
    )

    fun mapToUser(newUserFirebasePayload: NewUserFirebasePayload) = User(
        name = newUserFirebasePayload.name ?: ""
    )
}
