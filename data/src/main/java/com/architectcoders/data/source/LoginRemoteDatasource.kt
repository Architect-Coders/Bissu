package com.architectcoders.data.source

import com.architectcoders.domain.entities.User

/**
 * Created by Anibal Cortez on 2019-12-16.
 */
interface LoginRemoteDatasource {
    suspend fun createAccount(username: String, email: String,firstName: String, lastName: String, password: String , photoUrl : String?): User?
    suspend fun doLogin(username : String, password : String): User?
    suspend fun updateAccount(user: User): User?
}