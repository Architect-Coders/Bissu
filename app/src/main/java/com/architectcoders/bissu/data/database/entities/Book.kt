package com.architectcoders.bissu.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Anibal Cortez on 2019-12-11.
 */
@Entity
data class Book(
    @PrimaryKey val id: String,
    val title: String,
    val editorial: String,
    val author: String,
    val description: String,
    val pages: String,
    val category: String,
    val photoUrl: String?
)