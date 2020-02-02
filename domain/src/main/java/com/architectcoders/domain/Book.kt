package com.architectcoders.domain

data class Book (
    val id: String,
    val title: String,
    val author: String,
    val pages: String,
    val editorial: String,
    val category: String,
    val description: String,
    val photoUrl: String?
)