package com.architectcoders.data.repository

import com.architectcoders.data.source.LoginLocalDataSource
import com.architectcoders.data.source.LoginRemoteDatasource
import com.architectcoders.domain.User

/**
 * Created by Anibal Cortez on 2019-12-11.
 */
class UserRepository (private val localDataSource: LoginLocalDataSource, private val remoteDatasource: LoginRemoteDatasource){

    suspend fun doLogin(username : String, password : String) : User? {
        if(localDataSource.isEmpty()){
            return null
        }
       return localDataSource.findById(username,password)
    }

    suspend fun createAccount(user: User) : User? {
        return remoteDatasource.createAccount(user)
    }

}