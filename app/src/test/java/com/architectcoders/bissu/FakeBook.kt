package com.architectcoders.bissu

import com.architectcoders.data.source.BookLocalDataSource
import com.architectcoders.data.source.BookRemoteDatasource
import com.architectcoders.domain.entities.Book
import com.architectcoders.domain.entities.DataResponse
import com.example.testshared.mockedBook

class FakeBookRemoteDatasource : BookRemoteDatasource{

    var book : Book = mockedBook
    var mockedBookList : List<Book> = listOf(mockedBook)

    var mockedListResponse  = DataResponse.Success(mockedBookList)
    var mockedBookResponse  = DataResponse.Success(book)

    override suspend fun getBooks(): DataResponse<List<Book>> {
       return mockedListResponse
    }

    override suspend fun createBook(
        title: String,
        author: String,
        pages: String,
        editorial: String,
        categoryId: String,
        description: String
    ): DataResponse<Book> {

        return mockedBookResponse
    }

}

class FakeBookLocalDatasource : BookLocalDataSource{
    var book : Book = mockedBook
    var bookList : List<Book> = mutableListOf(book)
    var isEmpty : Boolean = false

    override suspend fun isEmpty(): Boolean {
        return isEmpty
    }

    override suspend fun saveBooks(bookList: List<Book>) {
       this.bookList = bookList.toMutableList()
    }

    override suspend fun saveBook(book: Book) {
        val mutableBookList : MutableList<Book> = bookList.toMutableList()
        mutableBookList.add(book)
        bookList =  mutableBookList
    }

    override suspend fun getBooks(): List<Book> {
        return bookList
    }

    override suspend fun getBook(id: String): Book {
       return bookList.first { it.id == id}
    }

    override suspend fun updateBook(book: Book) {
        bookList = bookList.filterNot { it.id == book.id }  + book
    }
}