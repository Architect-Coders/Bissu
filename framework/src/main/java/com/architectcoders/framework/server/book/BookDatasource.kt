package com.architectcoders.framework.server.book

import android.util.Log
import com.architectcoders.framework.mappers.toDomainBook
import com.architectcoders.framework.server.RetrofitClient
import com.architectcoders.framework.server.book.request.BookRequest
import com.architectcoders.data.source.BookRemoteDatasource
import com.architectcoders.domain.entities.Book
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class BookDatasource : BookRemoteDatasource {

    override suspend fun getBooks(): List<Book> =
        withContext(Dispatchers.IO) {
            try {
                RetrofitClient().bookService.getBooks()
                    .await()
                    .books.map {
                    it.toDomainBook()
                }
            }catch (e : Exception){
                Log.d("ERROR", e.message)
                listOf<Book>()
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