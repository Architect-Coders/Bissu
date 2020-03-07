package com.architectcoders.framework.server.entities

import com.google.gson.annotations.SerializedName

data class CreateObservation(
    @SerializedName("_id")
    val id: String,
    @SerializedName("user")
    val user: String,
    @SerializedName("book")
    val book: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("page")
    val page: String
)