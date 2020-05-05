package com.architectcoders.data.repository

import com.architectcoders.data.source.BookLocalDataSource
import com.architectcoders.data.source.BookRemoteDatasource
import com.architectcoders.domain.entities.Book
import com.architectcoders.domain.entities.DataResponse
import com.architectcoders.domain.reositories.BookRepository

class BookRepository(
    private val localDataSource: BookLocalDataSource,
    private val remoteDatasource: BookRemoteDatasource
) : BookRepository {

    override suspend fun getBooks(forceRefresh: Boolean): DataResponse<List<Book>> {
        if (localDataSource.isEmpty() || forceRefresh) {
            val response = remoteDatasource.getBooks()
            if (response is DataResponse.Success)
                localDataSource.saveBooks(response.data)
            return response
        }
        return DataResponse.Success(localDataSource.getBooks())
    }

    override suspend fun getBookById(id: String): Book {
        return localDataSource.getBook(id)
    }

    override suspend fun createBook(
        title: String, author: String, pages: String,
        editorial: String, categoryId: String, description: String, photoUrl: String?
    ): DataResponse<Book> {
        val response = remoteDatasource.createBook(title, author, pages, editorial, categoryId, description)
        if (response is DataResponse.Success)
            localDataSource.saveBook(response.data)
        return response
    }

}