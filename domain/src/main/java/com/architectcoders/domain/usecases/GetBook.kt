package com.architectcoders.domain.usecases

import com.architectcoders.domain.entities.Book
import com.architectcoders.domain.reositories.BookRepository

class GetBook(private val repository: BookRepository) {
    suspend fun invoke(id: String): Book? = repository.getBookById(id)
}