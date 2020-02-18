package com.architectcoders.usecases


import com.architectcoders.data.repository.BookRepository
import com.architectcoders.domain.Book

class GetBooks(private val repository: BookRepository) {
    suspend fun invoke(forceRefresh : Boolean = false): List<Book> = repository.getBooks(forceRefresh)
}