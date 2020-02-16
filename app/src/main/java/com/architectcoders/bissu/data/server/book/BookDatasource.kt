package com.architectcoders.bissu.data.server.book

import com.architectcoders.bissu.data.mappers.toDomainBook
import com.architectcoders.bissu.data.server.RetrofitClient
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
}