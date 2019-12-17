package com.architeccoders.bissu.data.server

import com.architectcoders.data.source.LoginRemoteDatasource
import com.architectcoders.domain.User
import com.google.firebase.FirebaseException
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

/**
 * Created by Anibal Cortez on 2019-12-16.
 */
class LoginFirebaseDBDatasource(private val db: FirebaseFirestore) : LoginRemoteDatasource {

    companion object {
        val USER_COLLERCTION: String = "users"
    }

    override suspend fun createAccount(user: User): User? {
        return try {
            var userResult: User? = null
            db.collection(USER_COLLERCTION).document().set(user)
                .addOnCompleteListener {
                    userResult = user
                }
                .addOnFailureListener {
                    userResult = null
                }
                .await()
            userResult

        } catch (e: FirebaseException) {
            null
        }
    }

}