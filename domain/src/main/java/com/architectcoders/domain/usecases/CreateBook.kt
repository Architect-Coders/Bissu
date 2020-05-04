package com.architectcoders.domain.usecases

import com.architectcoders.domain.entities.Book
import com.architectcoders.domain.entities.DataResponse
import com.architectcoders.domain.interfaces.BookRepository

/**
 * Created by Anibal Cortez on 2020-02-18.
 */
class CreateBook(private val repository: BookRepository) {
    suspend fun invoke(title: String, author: String, pages: String, editorial: String, categoryId: String, description: String, photoUrl: String?)
            : DataResponse<Book> = repository.createBook(title,author, pages, editorial, categoryId, description, photoUrl)
}