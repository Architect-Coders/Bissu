package com.architectcoders.bissu.data.server.observation.request

import com.google.gson.annotations.SerializedName

data class CreateObservationRequest(
    @SerializedName("user")
    val user: String,
    @SerializedName("book")
    val book: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("page")
    val page: String
)