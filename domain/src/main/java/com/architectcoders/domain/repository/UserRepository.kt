package com.architectcoders.domain.repository

import com.architectcoders.domain.entities.User

/**
 * Created by Anibal Cortez on 2020-01-21.
 */
interface UserRepository {
    suspend fun doLogin(username: String, password: String) : Boolean
    suspend fun createAccount(user: User, password: String): Boolean
    suspend fun getUser(): User?
}