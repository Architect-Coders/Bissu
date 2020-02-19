package com.architectcoders.usecases

import com.architectcoders.data.repository.UserRepository
import com.architectcoders.domain.User

/**
 * Created by Anibal Cortez on 2020-01-04.
 */
class GetUser(private val userRepository : UserRepository) {
    suspend fun getUser(): User? = userRepository.getUser()
}