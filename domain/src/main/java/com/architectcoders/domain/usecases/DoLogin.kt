package com.architectcoders.domain.usecases

import com.architectcoders.domain.entities.DataResponse
import com.architectcoders.domain.entities.User
import com.architectcoders.domain.reositories.UserRepository

/**
 * Created by Anibal Cortez on 2019-12-11.
 */
class DoLogin(private val userRepository: UserRepository) {
    suspend fun invoke(username: String, password: String)
            : DataResponse<User> = userRepository.doLogin(username, password)
}