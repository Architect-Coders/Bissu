package com.architectcoders.bissu.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Anibal Cortez on 2020-01-19.
 */

@Entity
data class Category (
    @PrimaryKey val id : String,
    val name : String
)


