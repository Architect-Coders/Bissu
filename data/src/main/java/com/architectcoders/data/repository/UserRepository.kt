package com.architectcoders.data.repository

import com.architectcoders.data.source.LoginLocalDataSource
import com.architectcoders.data.source.LoginRemoteDatasource
import com.architectcoders.domain.entities.User
import com.architectcoders.domain.interfaces.UserRepository
/**
 * Created by Anibal Cortez on 2019-12-11.
 */
class UserRepository(private val localDataSource: LoginLocalDataSource, private val remoteDatasource: LoginRemoteDatasource) :
    UserRepository {

   override suspend fun doLogin(username: String, password: String): Boolean {
        remoteDatasource.doLogin(username, password)?.let {
            localDataSource.deleteAlluser()
            localDataSource.saveUser(it)
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

    override suspend fun updateUser(user: User, password: String): Boolean {
        val remoteUser : User? = remoteDatasource.updateAccount(user, password)
        remoteUser?.let {
            localDataSource.updateUser(remoteUser)
            return true
        }
        return false
    }

}