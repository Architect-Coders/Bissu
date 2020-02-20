package com.architectcoders.data.repository

import com.architectcoders.data.source.BookLocalDataSource
import com.architectcoders.data.source.BookRemoteDatasource
import com.architectcoders.domain.Book

class BookRepository(
    private val localDataSource: BookLocalDataSource,
    private val remoteDatasource: BookRemoteDatasource
) {

    suspend fun getBooks(forceRefresh: Boolean): List<Book> {

        if (localDataSource.isEmpty() || forceRefresh) {
            remoteDatasource.getBooks().let {
                localDataSource.saveBooks(it)
            }
        }
        return localDataSource.getBooks()
    }

    suspend fun getBook(id: String): Book {
        return localDataSource.getBook(id)
    }


    suspend fun createBook(
        title: String,
        author: String,
        pages: String,
        editorial: String,
        categoryId: String,
        description: String
    ): Boolean {
        val book = remoteDatasource.createBook(title, author, pages, editorial, categoryId, description)
        // insert book
        return true
    }

}