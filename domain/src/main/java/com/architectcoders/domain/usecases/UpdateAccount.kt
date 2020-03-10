package com.architectcoders.domain.usecases

import com.architectcoders.domain.entities.User
import com.architectcoders.domain.interfaces.UserRepository

class UpdateAccount(val userRepository: UserRepository) {
    suspend fun invoke(user: User) : Boolean = userRepository.updateUser(user)
}