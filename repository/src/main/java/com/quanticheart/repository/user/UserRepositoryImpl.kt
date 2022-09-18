package com.quanticheart.repository.user

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.quanticheart.domain.model.user.Credentials
import com.quanticheart.domain.model.user.NewUser
import com.quanticheart.domain.model.user.User
import com.quanticheart.domain.repository.UserRepository
import com.quanticheart.repository.user.mapper.NewUserFirebasePayloadMapper
import kotlinx.coroutines.tasks.await

class UserRepositoryImpl(
    private val mAuth: FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore
) : UserRepository {

    override suspend fun getSession(): Result<User> {
        mAuth.currentUser?.reload()
        val firebaseUser = mAuth.currentUser
        return if (firebaseUser == null) {
            Result.failure(Exception("Usuário não logado"))
        } else {
            val user = firebaseFirestore.collection("users")
                .document(firebaseUser.uid).get().await().toObject(User::class.java)
            if (user == null) {
                Result.failure(Exception("Usuário não encontrado"))
            } else {
                user.id = firebaseUser.uid
                Result.success(user)
            }
        }
    }

    override suspend fun login(credentials: Credentials): Result<User> {
        return try {
            mAuth.signInWithEmailAndPassword(credentials.email, credentials.password).await()
            val firebaseUser = mAuth.currentUser
            if (firebaseUser == null) {
                Result.failure(Exception("Usuário ou senha inválido"))
            } else {
                Result.success(User(firebaseUser.displayName ?: ""))
            }
        } catch (e: Exception) {
            Result.failure(Exception(e))
        }
    }

    override suspend fun create(data: NewUser): Result<User> {
        return try {
            mAuth.createUserWithEmailAndPassword(data.email, data.password).await()
            val userId = mAuth.currentUser?.uid
            if (userId == null) {
                Result.failure(Exception("Não foi possível criar a conta"))
            } else {
                val newUserFirebasePayload =
                    NewUserFirebasePayloadMapper.mapToNewUserFirebasePayload(data)

                firebaseFirestore
                    .collection("users")
                    .document(userId)
                    .set(newUserFirebasePayload)
                    .await()

                Result.success(NewUserFirebasePayloadMapper.mapToUser(newUserFirebasePayload))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun recover(email: String): Result<String> {
        return try {
            mAuth.sendPasswordResetEmail(email).await()
            Result.success("Verifique sua caixa de e-mail")
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun update(password: String): Result<String> {
        return try {
            mAuth.currentUser?.updatePassword(password)?.await()
            Result.success("Verifique sua caixa de e-mail")
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun logout(): Result<Boolean> {
        return try {
            FirebaseAuth.getInstance().signOut()
            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}