package com.architectcoders.bissu.data.server.entities

import com.google.gson.annotations.SerializedName

data class Observation(
    @SerializedName("id")
    val id: String,
    @SerializedName("user")
    val user: User,
    @SerializedName("book")
    val book: Book,
    @SerializedName("description")
    val description: String,
    @SerializedName("page")
    val page: String
)