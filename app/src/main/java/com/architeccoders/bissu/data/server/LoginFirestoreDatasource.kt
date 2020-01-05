package com.architeccoders.bissu.data.server

import com.architectcoders.data.source.LoginRemoteDatasource
import com.architectcoders.domain.User
import com.google.firebase.FirebaseException
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

/**
 * Created by Anibal Cortez on 2019-12-16.
 */
class LoginFirestoreDatasource(private val db: FirebaseFirestore) : LoginRemoteDatasource {

    companion object {
        val USER_COLLERCTION: String = "users"
    }


    override suspend fun existsUsername(username: String): Boolean {
        return try {
            var response = false
            db.collection(USER_COLLERCTION)
                .whereEqualTo("username", username)
                .get()
                .addOnSuccessListener {documents ->
                    val usernameResponse : String? = documents.documents[0]["username"] as String?
                    if (!usernameResponse.isNullOrBlank())
                        response = true
                }
                .await()
            response
        } catch (e: FirebaseException) {
            false
        }
    }


    override suspend fun doLogin(username: String, password: String): User? {
        return try {
            var user: User? = null
            db.collection(USER_COLLERCTION)
                .whereEqualTo("username", username)
                .whereEqualTo("password", password)
                .limit(1)
                .get()
                .addOnSuccessListener { documents ->
                    val usernameResponse = documents.documents[0]?.data?.get("username") as String?
                    val passwordResponse = documents.documents[0]?.data?.get("password") as String?
                    val firstNameResponse = documents.documents[0]?.data?.get("firstName") as String?
                    val lastNameResponse = documents.documents[0]?.data?.get("lastName") as String?
                    val photoUrlResponse = documents.documents[0]?.data?.get("photoUrl") as String?

                    if (!usernameResponse.isNullOrEmpty() && !passwordResponse.isNullOrEmpty() && !firstNameResponse.isNullOrEmpty() && !lastNameResponse.isNullOrEmpty())
                        user = User(
                            username = usernameResponse,
                            password = passwordResponse,
                            firstName = firstNameResponse,
                            lastName = lastNameResponse,
                            photoUrl = photoUrlResponse
                        )
                }
                .addOnFailureListener {

                }
                .await()
            user
        } catch (e: FirebaseException) {
            null
        }
    }
    override suspend fun createAccount(user: User): Boolean {
        return try {
            var status = false;
            db.collection(USER_COLLERCTION).document().set(user)
                .addOnCompleteListener {
                    status = true
                }
                .addOnFailureListener {
                    status = false
                }
                .await()
            status

        } catch (e: FirebaseException) {
            false
        }
    }


}