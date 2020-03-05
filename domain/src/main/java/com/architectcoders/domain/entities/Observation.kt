package com.architectcoders.domain.entities

data class Observation(
    val id: String,
    val user: User,
    val book: Book,
    val description: String,
    val page: String
)