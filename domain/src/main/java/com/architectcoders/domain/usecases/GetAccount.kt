package com.architectcoders.domain.usecases

import com.architectcoders.domain.entities.User
import com.architectcoders.domain.interfaces.UserRepository

class GetAccount(val userRepository: UserRepository) {
    suspend fun invoke(): User? = userRepository.getUser()
}