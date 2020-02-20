package com.architectcoders.bissu.data.server.book

import com.architectcoders.bissu.data.mappers.toDomainBook
import com.architectcoders.bissu.data.server.RetrofitClient
import com.architectcoders.bissu.data.server.book.request.BookRequest
import com.architectcoders.data.source.BookRemoteDatasource
import com.architectcoders.domain.Book
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