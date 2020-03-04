package com.architectcoders.bissu.data.server.entities

import com.google.gson.annotations.SerializedName

data class ObservationBook(
    @SerializedName("_id")
    val _id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("author")
    val author: String,
    @SerializedName("pages")
    val pages: String,
    @SerializedName("editorial")
    val editorial: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("photoUrl")
    val photoUrl: String?
)