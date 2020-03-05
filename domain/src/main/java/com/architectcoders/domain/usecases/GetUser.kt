package com.architectcoders.domain.usecases

import com.architectcoders.domain.entities.User
import com.architectcoders.domain.interfaces.UserRepository

/**
 * Created by Anibal Cortez on 2020-01-04.
 */

class GetUser(private val userRepository : UserRepository) {
    suspend fun getUser(): User? = userRepository.getUser()
}