package com.architectcoders.domain.reositories

import com.architectcoders.domain.entities.Book
import com.architectcoders.domain.entities.DataResponse

/**
 * Created by Anibal Cortez on 2020-03-05.
 */
interface BookRepository {
    suspend fun getBooks(forceRefresh: Boolean): DataResponse<List<Book>>
    suspend fun getBookById(id: String):DataResponse<Book>
    suspend fun createBook(title: String, author: String, pages: String, editorial: String,
        categoryId: String, description: String, photoUrl: String?
    ): DataResponse<Book>

}