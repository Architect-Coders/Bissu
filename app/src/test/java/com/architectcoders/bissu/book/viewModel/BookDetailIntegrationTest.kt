package com.architectcoders.bissu.book.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.architectcoders.bissu.initMockedDi
import com.architectcoders.domain.usecases.GetBook
import com.example.testshared.mockedBook
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest
import org.koin.test.get
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BookDetailIntegrationTest :   AutoCloseKoinTest() {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var observer: Observer<BookDetailViewModel.UiModel>

    private lateinit var viewModel: BookDetailViewModel

    @Before
    fun setUp() {
        val vmModule = module {
            factory { BookDetailViewModel(get(), get()) }
            factory { GetBook(get()) }
        }
        initMockedDi(vmModule)
        viewModel = get()
    }

    @Test
    fun getBookSuccess(){
        runBlocking {
            viewModel.model.observeForever(observer)

            viewModel.getBook(mockedBook.id)

            verify(observer).onChanged(BookDetailViewModel.UiModel.BookContent(mockedBook))
        }
   }

}