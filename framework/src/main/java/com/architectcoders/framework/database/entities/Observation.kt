package com.architectcoders.framework.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.architectcoders.framework.database.converters.BookConverter

@Entity
@TypeConverters(BookConverter::class)
data class Observation(
    @PrimaryKey val id: String,
    val userId: String,
    val book: Book,
    val page: String,
    val description: String
)


