package com.architectcoders.framework.server.book

import android.content.Context
import android.util.Log
import com.architectcoders.framework.mappers.toDomainBook
import com.architectcoders.framework.server.RetrofitClient
import com.architectcoders.framework.server.book.request.BookRequest
import com.architectcoders.data.source.BookRemoteDatasource
import com.architectcoders.domain.entities.Book
import com.architectcoders.domain.entities.DataResponse
import com.architectcoders.framework.util.ErrorCode
import com.architectcoders.framework.util.isAvailableNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class BookDatasource(val context: Context) : BookRemoteDatasource {

    override suspend fun getBooks(): DataResponse<List<Book>> =
        withContext(Dispatchers.IO) {

            if (!context.isAvailableNetwork()) return@withContext DataResponse.NetworkError

            val result = RetrofitClient().bookService.getBooks().await()
            if (result.isSuccessful) {
                if (result.body() == null) return@withContext DataResponse.ServerError(ErrorCode.BAD_REQUEST)
                val bookList: List<Book> = result.body()!!.books.map { it.toDomainBook() }
                return@withContext DataResponse.Success(bookList)
            }
            return@withContext DataResponse.ServerError(ErrorCode.SERVER_ERROR)
        }


    override suspend fun createBook(
        title: String,
        author: String,
        pages: String,
        editorial: String,
        categoryId: String,
        description: String
    ): DataResponse<Book> =
        withContext(Dispatchers.IO) {
            if (!context.isAvailableNetwork()) return@withContext DataResponse.NetworkError

            val result = RetrofitClient().bookService.createBook(
                BookRequest(
                    title = title, author = author, pages = pages,
                    editorial = editorial, category = categoryId, description = description
                )
            ).await()

            if (result.isSuccessful) {
                if (result.body() == null) return@withContext DataResponse.ServerError(ErrorCode.BAD_REQUEST)
                val book: Book = result.body()!!.book.toDomainBook()
                return@withContext DataResponse.Success(book)
            }
            return@withContext DataResponse.ServerError(ErrorCode.SERVER_ERROR)
        }

}