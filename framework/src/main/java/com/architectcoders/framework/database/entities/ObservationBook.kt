package com.architectcoders.framework.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class ObservationBook(
    @PrimaryKey val id: String,
    val title: String,
    val editorial: String,
    val author: String,
    val description: String,
    val pages: String,
    val category: String,
    val photoUrl: String?
)