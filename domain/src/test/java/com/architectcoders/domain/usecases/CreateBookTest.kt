package com.architectcoders.domain.usecases

import com.architectcoders.domain.repositories.BookRepository
import com.example.testshared.mockedBook
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CreateBookTest {

    @Mock
    lateinit var bookRepository: BookRepository

    lateinit var createBook : CreateBook

    @Before
    fun init(){
        createBook = CreateBook(bookRepository)
    }

    @Test
    fun invokeBookRepository() {

        runBlocking {
            createBook.invoke(
                mockedBook.title, mockedBook.author, mockedBook.pages, mockedBook.editorial,
                mockedBook.category.id, mockedBook.description, mockedBook.photoUrl)

            verify(bookRepository).createBook(  mockedBook.title, mockedBook.author, mockedBook.pages, mockedBook.editorial,
                mockedBook.category.id, mockedBook.description, mockedBook.photoUrl)
        }
    }
}