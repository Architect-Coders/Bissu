package com.architectcoders.data.source

import com.architectcoders.domain.Book


interface BookRemoteDatasource {
    suspend fun getBooks(): List<Book>
}