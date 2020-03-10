package com.architectcoders.domain.entities

/**
 * Created by Anibal Cortez on 2019-12-11.
 */
data class User (
    val id: String,
    val username: String,
    val email : String,
    val firstName: String,
    val lastName: String,
    val password : String,
    val photoUrl: String? = null,
    val categories : List<Category>
)