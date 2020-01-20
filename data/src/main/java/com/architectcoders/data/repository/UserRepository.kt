package com.architectcoders.data.repository

import com.architectcoders.data.source.LoginLocalDataSource
import com.architectcoders.data.source.LoginRemoteDatasource
import com.architectcoders.domain.User

/**
 * Created by Anibal Cortez on 2019-12-11.
 */
class UserRepository(private val localDataSource: LoginLocalDataSource, private val remoteDatasource: LoginRemoteDatasource) {

    suspend fun doLogin(username: String, password: String): Boolean {
        remoteDatasource.doLogin(username, password)?.let {
            localDataSource.saveUser(it)
        }
        if (!localDataSource.isEmpty()) {
            return true
        }
        return false
    }

    suspend fun createAccount(user: User, password: String): Boolean {
        val remoteUser : User? = remoteDatasource.createAccount(user, password)
        remoteUser?.let {
            return true
        }
        return false
    }

    suspend fun getUser(): User? {
        if (localDataSource.isEmpty())
            return null

        return localDataSource.getUser()
    }
}