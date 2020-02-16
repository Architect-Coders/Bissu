package com.architectcoders.bissu.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

/**
 * Created by Anibal Cortez on 2019-12-11.
 */
@Entity
@TypeConverters(CategoryListConverter::class)
data class User(
    @PrimaryKey val username: String,
    val email : String,
    val firstName: String,
    val lastName: String,
    val photoUrl: String?,
    val categories : List<Category>,
    val id: String
)