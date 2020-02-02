package com.architectcoders.data.repository

import com.architectcoders.data.source.BookLocalDataSource
import com.architectcoders.data.source.BookRemoteDatasource
import com.architectcoders.domain.Book

class BookRepository( private val localDataSource: BookLocalDataSource, private val remoteDatasource: BookRemoteDatasource) {

    suspend fun getBooks(): List<Book>  {
        if (localDataSource.isEmpty()) {
            remoteDatasource.getBooks().let {
                localDataSource.saveBooks(it)
            }
        }
        return localDataSource.getBooks()
    }

    suspend fun getBook(id : String) : Book {
        if (localDataSource.isEmpty()) {
            remoteDatasource.getBook(id).let {
                it?.let {
                    localDataSource.saveBooks(arrayListOf(it))
                }
            }
        }
        return localDataSource.getBook(id)
    }
}