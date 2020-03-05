package com.architectcoders.domain.interfaces

import com.architectcoders.domain.entities.Book

/**
 * Created by Anibal Cortez on 2020-03-05.
 */
interface BookRepository {
    suspend fun getBooks(forceRefresh: Boolean): List<Book>
    suspend fun getBook(id: String): Book
    suspend fun createBook(  title: String, author: String,   pages: String, editorial: String, categoryId: String, description: String ): Boolean
}