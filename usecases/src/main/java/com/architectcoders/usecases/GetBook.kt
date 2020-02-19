package com.architectcoders.usecases


import com.architectcoders.data.repository.BookRepository
import com.architectcoders.domain.Book

class GetBook(private val repository: BookRepository) {
    suspend fun invoke(id: String): Book? = repository.getBook(id)
}