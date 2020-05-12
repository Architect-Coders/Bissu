package com.architectcoders.domain.usecases

import com.architectcoders.domain.entities.DataResponse
import com.architectcoders.domain.entities.User
import com.architectcoders.domain.repositories.UserRepository

class UpdateAccount(val userRepository: UserRepository) {
    suspend fun invoke(user: User) : DataResponse<User> = userRepository.updateUser(user)
}