package com.architeccoders.bissu.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Anibal Cortez on 2019-12-11.
 */
@Entity
data class User(
    @PrimaryKey val username: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val photoUrl: String? = null
)