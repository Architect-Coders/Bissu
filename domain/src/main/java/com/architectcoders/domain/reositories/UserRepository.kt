package com.architectcoders.domain.reositories

import com.architectcoders.domain.entities.DataResponse
import com.architectcoders.domain.entities.User

/**
 * Created by Anibal Cortez on 2020-03-05.
 */
interface UserRepository {
    suspend fun doLogin(username: String, password: String): DataResponse<User>
    suspend fun createAccount(username: String, email: String, firstName: String, lastName: String, password: String,photoUrl : String?): DataResponse<User>
    suspend fun getSessionUser(): DataResponse<User>
    suspend fun updateUser(user: User): DataResponse<User>
}