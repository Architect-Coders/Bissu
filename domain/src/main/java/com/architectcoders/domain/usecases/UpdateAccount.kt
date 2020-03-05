package com.architectcoders.domain.usecases

import com.architectcoders.domain.entities.User
import com.architectcoders.domain.interfaces.UserRepository

class UpdateAccount(val userRepository: UserRepository) {
    suspend fun invoke(user: User, password : String) : Boolean = userRepository.updateUser(user, password)
}