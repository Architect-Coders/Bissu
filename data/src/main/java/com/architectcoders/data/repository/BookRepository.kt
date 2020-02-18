package com.architectcoders.data.repository

import com.architectcoders.data.source.BookLocalDataSource
import com.architectcoders.data.source.BookRemoteDatasource
import com.architectcoders.domain.Book

class BookRepository(  private val localDataSource: BookLocalDataSource,private val remoteDatasource: BookRemoteDatasource) {

    suspend fun getBooks(forceRefresh : Boolean ): List<Book> {

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
}