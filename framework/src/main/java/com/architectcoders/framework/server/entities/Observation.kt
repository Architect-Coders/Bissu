package com.architectcoders.framework.server.entities

import com.google.gson.annotations.SerializedName

data class Observation(
    @SerializedName("_id")
    val id: String,
    @SerializedName("user")
    val userId: String,
    @SerializedName("book")
    val book: Book,
    @SerializedName("description")
    val description: String,
    @SerializedName("page")
    val page: String
)