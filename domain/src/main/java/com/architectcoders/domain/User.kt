package com.architectcoders.domain

/**
 * Created by Anibal Cortez on 2019-12-11.
 */
data class User (
    val username: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val photoUrl: String? = null
)