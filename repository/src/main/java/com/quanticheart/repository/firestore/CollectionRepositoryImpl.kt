package com.quanticheart.repository.firestore

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.Source
import com.quanticheart.domain.model.pokemon.Card
import com.quanticheart.domain.model.pokemon.CardCollection
import com.quanticheart.domain.repository.CollectionRepository
import com.quanticheart.repository.firestore.mapper.FirebaseCollectionToCard
import com.quanticheart.repository.firestore.mapper.handleError
import com.quanticheart.repository.firestore.request.RequestInsertCard
import kotlinx.coroutines.tasks.await

class CollectionRepositoryImpl(
    private val context: Context,
    private val mAuth: FirebaseAuth,
    private val fireStore: FirebaseFirestore
) : CollectionRepository {

    private val database = "collection"
    private val table = "cards"
    override suspend fun addCollection(cardID: String, name: String): Result<Boolean> {
        val card = hashMapOf<String, Any>(
            "cardID" to cardID,
            "name" to name
        )

        return try {
            update(card)
        } catch (e: Exception) {
            return when (e) {
                is FirebaseFirestoreException -> when (e.code) {
                    FirebaseFirestoreException.Code.NOT_FOUND -> insert(card)
                    else -> context.handleError(e)
                }
                else -> context.handleError(e)
            }
        }
    }

    override suspend fun allCollection(): Result<List<Card>> {
        return try {
            selectAll()
        } catch (e: Exception) {
            context.handleError(e)
        }
    }

    override suspend fun findCollectionBy(id: String): Result<CardCollection> {
        return try {
            select(id)
        } catch (e: Exception) {
            context.handleError(e)
        }
    }

    override suspend fun deleteCollectionBy(cardID: String, name: String): Result<Boolean> {
        return try {
            delete(cardID, name)
        } catch (e: Exception) {
            context.handleError(e)
        }
    }

    /**
     * CRUD
     */
    private suspend fun insert(card: HashMap<String, Any>): Result<Boolean> {
        return mAuth.currentUser?.let {
            val data = hashMapOf<String, Any>(
                table to arrayListOf(card)
            )
            fireStore.collection(database)
                .document(it.uid)
                .set(data)
                .await()
            Result.success(true)
        } ?: run {
            getSessionError()
        }
    }

    private suspend fun update(card: HashMap<String, Any>): Result<Boolean> {
        mAuth.currentUser?.let {
            fireStore.collection(database)
                .document(it.uid)
                .update(table, FieldValue.arrayUnion(card))
                .await()
        }
        return Result.success(true)
    }

    private suspend fun selectAll(): Result<List<Card>> {
        return mAuth.currentUser?.let {
            val data = fireStore.collection(database)
                .document(it.uid)
                .get(Source.SERVER)
                .await()
                .toObject(RequestInsertCard::class.java)

            Result.success(FirebaseCollectionToCard().map(data))
        } ?: run {
            getSessionError()
        }
    }

    private suspend fun select(cardID: String): Result<CardCollection> {
        return mAuth.currentUser?.let {
            val data = fireStore.collection(database)
                .document(it.uid)
                .get(Source.SERVER)
                .await()
                .toObject(RequestInsertCard::class.java)

            FirebaseCollectionToCard().map(data).filter { card ->
                card.id == cardID
            }.getOrNull(0)?.let { sendCard ->
                Result.success(CardCollection(true, sendCard))
            } ?: run {
                Result.success(CardCollection())
            }

//            FirebaseFirestore.setLoggingEnabled(true)
//            val data = fireStore.collection(database + "." + it.uid)
//                .whereIn("$table.cardID", arrayListOf("base1-5"))
//                .get(Source.SERVER)
//                .await()
//
//            Log.e("TEST", data.isEmpty.toString())
//            Result.success(Card())
        } ?: run {
            getSessionError()
        }
    }

    private suspend fun delete(cardID: String, name: String): Result<Boolean> {
        val remove = hashMapOf<String, Any>(
            "cardID" to cardID,
            "name" to name
        )
        return mAuth.currentUser?.let {
            mAuth.currentUser?.let {
                fireStore.collection(database)
                    .document(it.uid)
                    .update(table, FieldValue.arrayRemove(remove))
                    .await()
            }
            return Result.success(true)
        } ?: run {
            getSessionError()
        }
    }

    private fun <T> getSessionError(): Result<T> =
        Result.failure(Throwable("Session Error"))
}
