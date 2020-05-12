package com.architectcoders.domain.usecases

import com.architectcoders.domain.repositories.BookRepository
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class GetBooksTest{

    @Mock
    lateinit var bookRepository: BookRepository

    lateinit var getBooks: GetBooks

    @Before
    fun init(){
        getBooks = GetBooks(bookRepository)
    }

    @Test
    fun invokeBooksRepository(){
        runBlocking {
            getBooks.invoke(false)
            verify(bookRepository).getBooks(false)
        }

    }
}