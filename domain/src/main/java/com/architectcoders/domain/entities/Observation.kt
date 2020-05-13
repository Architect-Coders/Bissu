package com.architectcoders.domain.entities

data class Observation(
    val id: String,
    val userId: String,
    val book: ObservationBook,
    val description: String,
    val page: String
)