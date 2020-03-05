package com.architectcoders.framework.server.book

import com.architectcoders.framework.mappers.toDomainBook
import com.architectcoders.framework.server.RetrofitClient
import com.architectcoders.framework.server.book.request.BookRequest
import com.architectcoders.data.source.BookRemoteDatasource
import com.architectcoders.domain.entities.Book
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BookDatasource : BookRemoteDatasource {

    override suspend fun getBooks(): List<Book> =
        withContext(Dispatchers.IO) {
            RetrofitClient().bookService.getBooks()
                .await()
                .books.map {
                it.toDomainBook()
            }
        }


    override suspend fun createBook( title: String, author: String, pages: String, editorial: String, categoryId: String, description: String): Book =
        withContext(Dispatchers.IO) {
            RetrofitClient().bookService.createBook(
                BookRequest(
                    title = title,
                    author = author,
                    pages = pages,
                    editorial = editorial,
                    category = categoryId,
                    description = description
                )
            ).await().book?.toDomainBook()
        }

}