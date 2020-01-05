package com.architectcoders.usecases

import com.architectcoders.data.repository.UserRepository
import com.architectcoders.domain.User

/**
 * Created by Anibal Cortez on 2019-12-16.
 */
class CreateAccount(private val userRepository : UserRepository) {
    suspend fun invoke(user: User) : Boolean = userRepository.createAccount(user)
}