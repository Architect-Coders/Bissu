package com.architectcoders.bissu.data.server.book.request

/**
 * Created by Anibal Cortez on 2020-02-18.
 */
data class BookRequest (
    val title: String,
    val author: String,
    val pages: String,
    val editorial: String,
    val category: String,
    val description: String
)
