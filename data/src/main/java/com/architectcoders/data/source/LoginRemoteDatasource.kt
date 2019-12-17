package com.architectcoders.data.source

import com.architectcoders.domain.User

/**
 * Created by Anibal Cortez on 2019-12-16.
 */
interface LoginRemoteDatasource {
    suspend fun createAccount(user: User): User?
}