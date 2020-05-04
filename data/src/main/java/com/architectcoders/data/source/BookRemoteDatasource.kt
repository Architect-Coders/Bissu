package com.architectcoders.data.source

import com.architectcoders.domain.entities.Book
import com.architectcoders.domain.entities.DataResponse


interface BookRemoteDatasource {
    suspend fun getBooks(): DataResponse<List<Book>>
    suspend fun createBook(title: String, author: String, pages: String, editorial: String, categoryId: String, description: String): DataResponse<Book>

}