package com.architectcoders.domain.usescases


import com.architectcoders.domain.entities.User
import com.architectcoders.domain.repository.UserRepository

/**
 * Created by Anibal Cortez on 2019-12-16.
 */
class CreateAccount(private val userRepository : UserRepository) {
    suspend fun invoke(user: User, password : String) : Boolean = userRepository.createAccount(user, password)
}