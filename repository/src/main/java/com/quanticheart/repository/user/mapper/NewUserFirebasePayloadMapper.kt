package com.quanticheart.repository.user.mapper

import com.quanticheart.repository.user.models.NewUserFirebasePayload
import com.quanticheart.domain.model.user.NewUser
import com.quanticheart.domain.model.user.User

object NewUserFirebasePayloadMapper {

    fun mapToNewUser(newUserFirebasePayload: NewUserFirebasePayload) = NewUser(
        name = newUserFirebasePayload.name ?: "",
        email = newUserFirebasePayload.email ?: "",
        phone = newUserFirebasePayload.phone ?: "",
        password = newUserFirebasePayload.password ?: ""
    )

    fun mapToNewUserFirebasePayload(newUser: NewUser) = NewUserFirebasePayload(
        name = newUser.name,
        email = newUser.email,
        phone = newUser.phone,
        password = newUser.password
    )

    fun mapToUser(newUserFirebasePayload: NewUserFirebasePayload) = User(
        name = newUserFirebasePayload.name ?: ""
    )
}
