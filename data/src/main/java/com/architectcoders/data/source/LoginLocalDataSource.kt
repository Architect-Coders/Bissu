package com.architectcoders.data.source

import com.architectcoders.domain.entities.User

/**
 * Created by Anibal Cortez on 2019-12-11.
 */
interface LoginLocalDataSource {
    suspend fun isEmpty(): Boolean
    suspend fun saveUser(user: User)
    suspend fun getUser(): User
}