package com.architectcoders.framework.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.architectcoders.framework.database.converters.CategoryConverter

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