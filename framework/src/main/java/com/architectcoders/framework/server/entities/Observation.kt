package com.architectcoders.framework.server.entities

import com.google.gson.annotations.SerializedName

data class Observation(
    @SerializedName("_id")
    val id: String,
    @SerializedName("user")
    val user: User,
    @SerializedName("book")
    val book: ObservationBook,
    @SerializedName("description")
    val description: String,
    @SerializedName("page")
    val page: String
)