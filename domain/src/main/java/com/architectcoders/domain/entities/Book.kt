package com.architectcoders.domain.entities

data class Book (
    val id: String,
    val title: String,
    val author: String,
    val pages: String,
    val editorial: String,
    val category: Category,
    val description: String,
    var photoUrl: String?
)
