package com.architectcoders.framework.database.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Observation(
    @PrimaryKey val id: String,
    @Embedded(prefix = "user_") val user: User,
    @Embedded(prefix = "book_") val book: Book,
    val page: String,
    val description: String
)