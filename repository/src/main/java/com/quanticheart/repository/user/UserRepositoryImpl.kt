package com.quanticheart.repository.user

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.quanticheart.domain.model.user.Credentials
import com.quanticheart.domain.model.user.NewUser
import com.quanticheart.domain.model.user.User
import com.quanticheart.domain.repository.UserRepository
import com.quanticheart.repository.user.mapper.FirebaseHandleError
import com.quanticheart.repository.user.mapper.NewUserFirebasePayloadMapper
import kotlinx.coroutines.tasks.await

class UserRepositoryImpl(
    private val context: Context,
    private val mAuth: FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore
) : UserRepository {

    override suspend fun getSession(): Result<User> {
        return try {
            mAuth.currentUser?.reload()
            return mAuth.currentUser?.let {
                val user = firebaseFirestore.collection("users")
                    .document(it.uid).get().await().toObject(User::class.java)
                if (user == null) {
                    Result.failure(Exception("Usuário não encontrado"))
                } else {
                    user.id = it.uid
                    Result.success(user)
                }
            } ?: run {
                Result.failure(Exception("Usuário não logado"))
            }
        } catch (e: Exception) {
            handleError(e)
        }
    }

    override suspend fun login(credentials: Credentials): Result<User> {
        return try {
            mAuth.signInWithEmailAndPassword(credentials.email, credentials.password).await()
            mAuth.currentUser?.let {
                Result.success(User(it.displayName ?: ""))
            } ?: run {
                Result.failure(Exception("Usuário ou senha inválido"))
            }
        } catch (e: Exception) {
            handleError(e)
        }
    }

    override suspend fun create(data: NewUser): Result<User> {
        return try {
            mAuth.createUserWithEmailAndPassword(data.email, data.password).await()
            mAuth.currentUser?.uid?.let {
                val newUserFirebasePayload =
                    NewUserFirebasePayloadMapper.mapToNewUserFirebasePayload(data)

                firebaseFirestore
                    .collection("users")
                    .document(it)
                    .set(newUserFirebasePayload)
                    .await()

                Result.success(NewUserFirebasePayloadMapper.mapToUser(newUserFirebasePayload))
            } ?: run {
                Result.failure(Exception("Não foi possível criar a conta"))
            }
        } catch (e: Exception) {
            handleError(e)
        }
    }

    override suspend fun recover(email: String): Result<String> {
        return try {
            mAuth.sendPasswordResetEmail(email).await()
            Result.success("Verifique sua caixa de e-mail")
        } catch (e: Exception) {
            handleError(e)
        }
    }

    override suspend fun update(password: String): Result<String> {
        return try {
            mAuth.currentUser?.let {
                it.updatePassword(password).await()
                Result.success("Verifique sua caixa de e-mail")
            } ?: run {
                Result.failure(Exception("Usuário não encontrado"))
            }
        } catch (e: Exception) {
            handleError(e)
        }
    }

    override suspend fun logout(): Result<Boolean> {
        return try {
            mAuth.signOut()
            Result.success(true)
        } catch (e: Exception) {
            handleError(e)
        }
    }

    private fun <T> handleError(e: Exception): Result<T> {
        val intRes = FirebaseHandleError().map(e)
        return Result.failure(Exception(context.getString(intRes)))
    }
}