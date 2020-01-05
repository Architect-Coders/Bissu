package com.architectcoders.usecases

import com.architectcoders.data.repository.UserRepository

/**
 * Created by Anibal Cortez on 2019-12-11.
 */
class DoLogin(private val userRepository : UserRepository) {
    suspend fun invoke(username : String, password : String) : Boolean = userRepository.doLogin(username,password)
}