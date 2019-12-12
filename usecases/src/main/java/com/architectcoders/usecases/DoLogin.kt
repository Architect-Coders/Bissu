package com.architectcoders.usecases

import com.architectcoders.data.repository.UserRepository
import com.architectcoders.domain.User

/**
 * Created by Anibal Cortez on 2019-12-11.
 */
class DoLogin(private val userRepository : UserRepository) {
    suspend fun invoke(username : String, password : String) : User? = userRepository.doLogin(username,password)
}