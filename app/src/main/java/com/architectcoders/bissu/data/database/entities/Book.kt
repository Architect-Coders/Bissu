package com.architectcoders.bissu.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity
@TypeConverters(CategoryConverter::class)
data class Book(
    @PrimaryKey val id: String,
    val title: String,
    val editorial: String,
    val author: String,
    val description: String,
    val pages: String,
    val category: Category,
    val photoUrl: String?
)