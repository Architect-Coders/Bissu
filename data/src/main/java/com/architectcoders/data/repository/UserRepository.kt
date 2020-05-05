package com.architectcoders.data.repository

import com.architectcoders.data.source.UserLocalDataSource
import com.architectcoders.data.source.UserRemoteDatasource
import com.architectcoders.domain.entities.DataResponse
import com.architectcoders.domain.entities.User
import com.architectcoders.domain.reositories.UserRepository

/**
 * Created by Anibal Cortez on 2019-12-11.
 */
class UserRepository(
    private val localDataSource: UserLocalDataSource,
    private val remoteDatasource: UserRemoteDatasource
) : UserRepository {

    override suspend fun doLogin(username: String, password: String): DataResponse<User> {
        val response = remoteDatasource.doLogin(username, password)
        if (response is DataResponse.Success) {
            localDataSource.deleteUser()
            localDataSource.saveUser(response.data)
        }
        return response
    }

    override suspend fun createAccount(
        username: String, email: String, firstName: String,
        lastName: String, password: String, photoUrl: String?
    ): DataResponse<User> {
        val response =remoteDatasource.createAccount(username, email, firstName, lastName, password, photoUrl)
        return response
    }

    override suspend fun getSessionUser(): DataResponse<User> {
        if (localDataSource.isEmpty())
            return DataResponse.SessionError
        return DataResponse.Success(localDataSource.getUser())
    }

    override suspend fun updateUser(user: User): DataResponse<User> {
        val response = remoteDatasource.updateAccount(user)
        if (response is DataResponse.Success){
            localDataSource.updateUser(response.data)
        }
        return response
    }
}