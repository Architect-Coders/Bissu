package com.architectcoders.bissu.data.server.book


import com.architectcoders.bissu.data.server.book.response.BookListResponse
import com.architectcoders.bissu.data.server.book.response.BookResponse
import com.architectcoders.bissu.data.server.entities.Book
import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface BookServices {

    @GET("/api/book/list")
    fun getBooks(): Deferred<BookListResponse>

    @POST("/api/book/")
    fun addBook(@Body book : Book) : Deferred<BookResponse>

    @POST ("/api/book/update")
    fun updateBook(@Body book : Book) : Deferred<BookResponse>
}