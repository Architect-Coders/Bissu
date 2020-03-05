package com.architectcoders.domain.interfaces

import com.architectcoders.domain.entities.User

/**
 * Created by Anibal Cortez on 2020-03-05.
 */
interface UserRepository {
    suspend fun doLogin(username: String, password: String): Boolean
    suspend fun createAccount(user: User, password: String): Boolean
    suspend fun getUser(): User?
    suspend fun updateUser(user: User, password: String): Boolean
}