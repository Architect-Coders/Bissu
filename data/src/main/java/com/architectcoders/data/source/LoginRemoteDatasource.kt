package com.architectcoders.data.source

import com.architectcoders.domain.entities.User

/**
 * Created by Anibal Cortez on 2019-12-16.
 */
interface LoginRemoteDatasource {
    suspend fun createAccount(user: User, password: String): User?
    suspend fun doLogin(username : String, password : String): User?
    suspend fun existsUsername(username: String) : Boolean
    suspend fun updateAccount(user: User, password: String): User?
}