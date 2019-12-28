package com.architectcoders.usecases

import com.architectcoders.data.repository.UserRepository
import com.architectcoders.domain.User

class GetAccount(val userRepository: UserRepository) {
    suspend fun invoke(userName: String): User? = userRepository.getUser(userName)
}