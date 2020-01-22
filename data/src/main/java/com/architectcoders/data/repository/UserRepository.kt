package com.architectcoders.data.repository

import com.architectcoders.data.source.LoginLocalDataSource
import com.architectcoders.data.source.LoginRemoteDatasource
import com.architectcoders.domain.entities.User
import com.architectcoders.domain.repository.UserRepository as DomainRepository

/**
 * Created by Anibal Cortez on 2019-12-11.
 */
class UserRepository(private val localDataSource: LoginLocalDataSource,
                     private val remoteDatasource: LoginRemoteDatasource) :
    DomainRepository  {

    override suspend fun doLogin(username: String, password: String): Boolean {
        remoteDatasource.doLogin(username, password)?.let {
            localDataSource.saveUser(it)
        }
        if (!localDataSource.isEmpty()) {
            return true
        }
        return false
    }

    override suspend fun createAccount(user: User, password: String): Boolean {
        val remoteUser : User? = remoteDatasource.createAccount(user, password)
        remoteUser?.let {
            return true
        }
        return false
    }

    override suspend fun getUser(): User? {
        if (localDataSource.isEmpty())
            return null

        return localDataSource.getUser()
    }

}