package com.architectcoders.bissu.data.database.entities

import androidx.room.*

@Entity
data class Observation(
    @PrimaryKey val id: String,
    @Embedded(prefix = "user_") val user: User,
    @Embedded(prefix = "book_") val book: Book,
    val page: String,
    val description: String
)