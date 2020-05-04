package com.architectcoders.data.repository

import com.architectcoders.data.source.BookLocalDataSource
import com.architectcoders.data.source.BookRemoteDatasource
import com.architectcoders.domain.entities.Book
import com.architectcoders.domain.entities.DataResponse
import com.example.testshared.mockedBook
import com.example.testshared.mockedServerError
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BookRepositoryTest {

    @Mock
    lateinit var localDataSource: BookLocalDataSource
    @Mock
    lateinit var remoteDatasource: BookRemoteDatasource

    lateinit var bookRepository: BookRepository

    @Before
    fun init() {
        bookRepository = BookRepository(localDataSource, remoteDatasource)
    }

    @Test
    fun getBooksFromRemoteDatasource() {
        runBlocking {

            whenever(localDataSource.isEmpty()).thenReturn(true)
            bookRepository.getBooks(false)

            verify(remoteDatasource).getBooks()
        }
    }

    @Test
    fun getBooksFromLocalDatasource() {
        runBlocking {
            val localBooks = listOf(mockedBook)
            whenever(localDataSource.isEmpty()).thenReturn(false)
            whenever(localDataSource.getBooks()).thenReturn(localBooks)

            bookRepository.getBooks(false)

            verify(localDataSource).getBooks()
        }
    }

    @Test
    fun getBooksServerError(){
        runBlocking {
            val serverError : DataResponse<List<Book>> = DataResponse.ServerError(mockedServerError)

            whenever(localDataSource.isEmpty()).thenReturn(true)
            whenever(remoteDatasource.getBooks()).thenReturn(DataResponse.ServerError(
                mockedServerError))

            val result = bookRepository.getBooks(false)

            assertEquals(serverError,result)
        }
   }

    @Test
    fun getBooksNetworkError(){
        runBlocking {
            val networkError : DataResponse<List<Book>> = DataResponse.NetworkError

            whenever(localDataSource.isEmpty()).thenReturn(true)
            whenever(remoteDatasource.getBooks()).thenReturn(DataResponse.NetworkError)

            val result = bookRepository.getBooks(false)

            assertEquals(networkError,result)
        }
    }

    @Test
    fun saveBooksFromLocalDatasource() {
        runBlocking {

            val localBooks = DataResponse.Success(listOf(mockedBook))
            whenever(localDataSource.isEmpty()).thenReturn(true)
            whenever(remoteDatasource.getBooks()).thenReturn(localBooks)

            bookRepository.getBooks(false)

            verify(localDataSource).saveBooks(localBooks.data)

        }
    }
    @Test
    fun getBookFromLocalDatasource(){
        runBlocking {
            whenever(localDataSource.getBook("1")).thenReturn(mockedBook)

            val result = bookRepository.getBookById("1")

            assertEquals(mockedBook,result)
        }
    }

    @Test
    fun createBookAndSave(){
        runBlocking {

            whenever(remoteDatasource.createBook(mockedBook.title, mockedBook.author, mockedBook.pages,
                mockedBook.editorial, mockedBook.category.id, mockedBook.description)).thenReturn(DataResponse.Success(mockedBook))

             bookRepository.createBook(mockedBook.title, mockedBook.author, mockedBook.pages,
                mockedBook.editorial, mockedBook.category.id, mockedBook.description, mockedBook.photoUrl)

            verify(localDataSource).saveBook(mockedBook)
        }
    }
}