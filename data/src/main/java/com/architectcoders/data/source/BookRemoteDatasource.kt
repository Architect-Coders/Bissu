package com.architectcoders.data.source

import com.architectcoders.domain.Book


interface BookRemoteDatasource {
    suspend fun getBooks(): List<Book>
    suspend fun createBook(title: String, author: String, pages: String, editorial: String, categoryId: String, description: String): Book

}