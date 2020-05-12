package com.architectcoders.domain.usecases

import com.architectcoders.domain.entities.Book
import com.architectcoders.domain.entities.DataResponse
import com.architectcoders.domain.repositories.BookRepository

class GetBook(private val repository: BookRepository) {
    suspend fun invoke(id: String): DataResponse<Book> = repository.getBookById(id)
}