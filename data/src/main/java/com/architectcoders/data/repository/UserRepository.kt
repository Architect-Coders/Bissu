package com.architectcoders.data.repository

import com.architectcoders.data.source.LocalDataSource
import com.architectcoders.domain.User

/**
 * Created by Anibal Cortez on 2019-12-11.
 */
class UserRepository (private val localDataSource: LocalDataSource){

    suspend fun doLogin(username : String, password : String) : User? {
        if(localDataSource.isEmpty()){
            return null
        }
       return localDataSource.findById(username,password)
    }

}