package com.architectcoders.domain.usecases

import com.architectcoders.domain.reositories.BookRepository
import com.example.testshared.mockedBook
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetBookTest{

    @Mock
    lateinit var bookRepository: BookRepository

    lateinit var getBook: GetBook

    @Before
    fun init(){
        getBook = GetBook(bookRepository)
    }

    @Test
    fun invokeBookRepository(){
        runBlocking {
            getBook.invoke(mockedBook.id)
            verify(bookRepository).getBookById(mockedBook.id)
        }

    }
}