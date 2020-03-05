package com.architectcoders.domain.usecases

import com.architectcoders.domain.entities.Book
import com.architectcoders.domain.interfaces.BookRepository

class GetBooks(private val repository: BookRepository) {
    suspend fun invoke(forceRefresh : Boolean = false): List<Book> = repository.getBooks(forceRefresh)
}