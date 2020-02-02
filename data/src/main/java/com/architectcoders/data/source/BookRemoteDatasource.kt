package com.architectcoders.data.source

import com.architectcoders.domain.Book


interface BookRemoteDatasource {
    suspend fun getBook(id: String): Book?
    suspend fun getBooks(): List<Book>
    suspend fun addBook(book: Book) : Book
    suspend fun updateBook(book: Book) : Book
}