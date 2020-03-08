package com.architectcoders.domain.usecases

import com.architectcoders.domain.interfaces.UserRepository
/**
 * Created by Anibal Cortez on 2019-12-16.
 */
class CreateAccount(private val userRepository : UserRepository) {
    suspend fun invoke(username: String, email: String,firstName: String, lastName: String, password: String , photoUrl : String?) : Boolean
            = userRepository.createAccount(username, email,firstName, lastName, password , photoUrl)

}