package com.architectcoders.bissu.book.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.architectcoders.domain.entities.DataResponse
import com.architectcoders.domain.usecases.GetBook
import com.example.testshared.mockedBook
import com.example.testshared.mockedServerError
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BookDetailViewModelTest{

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var  getBook: GetBook;

    @Mock
    lateinit var observer: Observer<BookDetailViewModel.UiModel>

    private lateinit var bookDetailViewModel: BookDetailViewModel

    @Before
    fun init(){
        bookDetailViewModel = BookDetailViewModel(getBook, Dispatchers.Unconfined)

    }

    @Test
    fun getBookLoading(){
        runBlocking {
            val expectedResponse = DataResponse.Success(mockedBook)
            whenever(getBook.invoke(mockedBook.id)).thenReturn(expectedResponse)

            bookDetailViewModel.model.observeForever(observer)

            bookDetailViewModel.getBook(mockedBook.id)

            verify(observer).onChanged(BookDetailViewModel.UiModel.Loading(true))
        }
   }

    @Test
    fun getBookSuccess(){
        runBlocking {
            val expectedResponse = DataResponse.Success(mockedBook)
            whenever(getBook.invoke(mockedBook.id)).thenReturn(expectedResponse)

            bookDetailViewModel.model.observeForever(observer)

            bookDetailViewModel.getBook(mockedBook.id)

            verify(observer).onChanged(BookDetailViewModel.UiModel.BookContent(expectedResponse.data))
        }
    }

    @Test
    fun getBookServerError(){
        runBlocking {
            val expectedResponse = DataResponse.ServerError(mockedServerError)
            whenever(getBook.invoke(mockedBook.id)).thenReturn(expectedResponse)

            bookDetailViewModel.model.observeForever(observer)

            bookDetailViewModel.getBook(mockedBook.id)

            verify(observer).onChanged(BookDetailViewModel.UiModel.ServerError)
        }
    }

}