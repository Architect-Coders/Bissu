package com.architectcoders.domain.usecases

import com.architectcoders.domain.entities.User
import com.architectcoders.domain.interfaces.UserRepository

/**
 * Created by Anibal Cortez on 2019-12-16.
 */
class CreateAccount(private val userRepository : UserRepository) {
    suspend fun invoke(user: User, password : String) : Boolean = userRepository.createAccount(user, password)
    suspend fun invoke() : User? = userRepository.getUser()
    suspend fun update(user: User, password : String) : Boolean = userRepository.updateUser(user, password)
}