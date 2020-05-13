package com.architectcoders.domain.entities

data class ObservationBook (
    val id: String,
    val title: String,
    val author: String,
    val pages: String,
    val editorial: String,
    val category: String,
    val description: String,
    var photoUrl: String?
)
