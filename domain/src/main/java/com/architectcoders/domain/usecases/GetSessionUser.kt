package com.architectcoders.domain.usecases

import com.architectcoders.domain.entities.DataResponse
import com.architectcoders.domain.entities.User
import com.architectcoders.domain.reositories.UserRepository

/**
 * Created by Anibal Cortez on 2020-01-04.
 */

class GetSessionUser(private val userRepository : UserRepository) {
    suspend fun getUser(): DataResponse<User> = userRepository.getSessionUser()
}