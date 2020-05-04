package com.architectcoders.data.source

import com.architectcoders.domain.entities.Book

interface BookLocalDataSource {
    suspend fun isEmpty(): Boolean
    suspend fun saveBooks(bookList: List<Book>)
    suspend fun saveBook(book: Book)
    suspend fun getBooks() : List<Book>
    suspend fun getBook(id : String) : Book
    suspend fun updateBook(book: Book)
}