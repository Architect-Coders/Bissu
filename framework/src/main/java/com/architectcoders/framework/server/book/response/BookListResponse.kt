package com.architectcoders.framework.server.book.response

import com.architectcoders.framework.server.entities.Book
import com.google.gson.annotations.SerializedName

data class BookListResponse(
    @SerializedName("status")
    var status: String,
    @SerializedName("books")
    var books: List<Book>
)