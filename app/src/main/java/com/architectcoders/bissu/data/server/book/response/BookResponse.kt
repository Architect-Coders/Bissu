package com.architectcoders.bissu.data.server.book.response

import com.architectcoders.bissu.data.server.entities.Book
import com.google.gson.annotations.SerializedName

data class BookResponse(
    @SerializedName("status")
    var status: String,
    @SerializedName("book")
    var book: Book
)