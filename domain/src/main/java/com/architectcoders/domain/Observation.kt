package com.architectcoders.domain

data class Observation(
    val id: String,
    val user: User,
    val book: Book,
    val description: String,
    val page: String
)