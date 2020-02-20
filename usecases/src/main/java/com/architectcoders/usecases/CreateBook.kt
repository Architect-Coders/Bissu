package com.architectcoders.usecases

import com.architectcoders.data.repository.BookRepository

/**
 * Created by Anibal Cortez on 2020-02-18.
 */
class CreateBook(private val repository: BookRepository) {
    suspend fun invoke(title: String, author: String, pages: String, editorial: String, categoryId: String, description: String) : Boolean
            = repository.createBook(title,author, pages, editorial, categoryId, description)
}