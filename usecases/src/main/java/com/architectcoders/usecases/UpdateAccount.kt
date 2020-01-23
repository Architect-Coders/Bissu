package com.architectcoders.usecases

import com.architectcoders.data.repository.UserRepository
import com.architectcoders.domain.User

class UpdateAccount(val userRepository: UserRepository) {
    suspend fun invoke(user: User, password : String) : Boolean = userRepository.updateUser(user, password)

}