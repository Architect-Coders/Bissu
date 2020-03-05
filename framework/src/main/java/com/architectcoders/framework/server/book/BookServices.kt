package com.architectcoders.framework.server.book


import com.architectcoders.framework.server.book.request.BookRequest
import com.architectcoders.framework.server.book.response.BookListResponse
import com.architectcoders.framework.server.book.response.BookResponse
import com.architectcoders.framework.server.entities.Book
import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface BookServices {

    @GET("/api/book/list")
    fun getBooks(): Deferred<BookListResponse>

    @POST("/api/book/add")
    fun createBook(@Body book : BookRequest) : Deferred<BookResponse>

    @POST ("/api/book/update")
    fun updateBook(@Body book : Book) : Deferred<BookResponse>
}