package com.architectcoders.domain.entities

import com.architectcoders.domain.entities.Category

/**
 * Created by Anibal Cortez on 2019-12-11.
 */
data class User (
    val username: String,
    val email : String,
    val firstName: String,
    val lastName: String,
    val photoUrl: String?,
    val categories : List<Category>
)