package com.architectcoders.bissu.book.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.architectcoders.bissu.login.viewmodels.LoginViewModel
import com.architectcoders.domain.entities.DataResponse
import com.architectcoders.domain.usecases.CreateBook
import com.architectcoders.domain.usecases.GetCategories
import com.example.testshared.mockedBook
import com.example.testshared.mockedCategory
import com.example.testshared.mockedPassword
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
class CreateBookViewModelTest{

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var getCategories : GetCategories
    @Mock
    lateinit var createBook : CreateBook
    @Mock
    lateinit var observer: Observer<CreateBookViewModel.UiModel>

    private lateinit var createBookViewModel: CreateBookViewModel

    @Before
    fun init(){
        createBookViewModel = CreateBookViewModel(getCategories,createBook ,Dispatchers.Unconfined)
    }


    @Test
    fun geCategoriesLoading(){

        runBlocking {
            val expectedResponse = DataResponse.Success(listOf(mockedCategory))

            whenever(getCategories.invoke()).thenReturn(expectedResponse)

            createBookViewModel.model.observeForever(observer)

            createBookViewModel.getCategories()

            verify(observer).onChanged(CreateBookViewModel.UiModel.Loading)

        }

    }

    @Test
    fun geCategoriesSuccess(){

        runBlocking {
            val expectedResponse = DataResponse.Success(listOf(mockedCategory))

            whenever(getCategories.invoke()).thenReturn(expectedResponse)

            createBookViewModel.model.observeForever(observer)

            createBookViewModel.getCategories()

            verify(observer).onChanged(CreateBookViewModel.UiModel.CategoryContent(listOf(
                mockedCategory)))

        }

    }

    @Test
    fun geCategoriesServerError(){

        runBlocking {
            val expectedResponse = DataResponse.ServerError(mockedServerError)

            whenever(getCategories.invoke()).thenReturn(expectedResponse)

            createBookViewModel.model.observeForever(observer)

            createBookViewModel.getCategories()

            verify(observer).onChanged(CreateBookViewModel.UiModel.ServerError)

        }
    }

    @Test
    fun geCategoriesNetworkError(){

        runBlocking {
            val expectedResponse = DataResponse.NetworkError

            whenever(getCategories.invoke()).thenReturn(expectedResponse)

            createBookViewModel.model.observeForever(observer)

            createBookViewModel.getCategories()

            verify(observer).onChanged(CreateBookViewModel.UiModel.NetworkError)

        }
    }

    @Test
    fun createBookSuccess(){

        runBlocking {
            val expectedResponse = DataResponse.Success(mockedBook)

            whenever(createBook.invoke(
                mockedBook.title,
                mockedBook.author,
                mockedBook.pages,
                mockedBook.editorial,
                mockedBook.category.id,
                mockedBook.description,
                mockedBook.photoUrl
            )).thenReturn(expectedResponse)

            createBookViewModel.model.observeForever(observer)

            createBookViewModel.createBook(
                mockedBook.title,
                mockedBook.author,
                mockedBook.pages,
                mockedBook.editorial,
                mockedBook.category.id,
                mockedBook.description,
                mockedBook.photoUrl)

            verify(observer).onChanged(CreateBookViewModel.UiModel.CreateBookSuccess)

        }
    }

    @Test
    fun createBookServerError(){

        runBlocking {
            val expectedResponse = DataResponse.ServerError(mockedServerError)

            whenever(createBook.invoke(
                mockedBook.title,
                mockedBook.author,
                mockedBook.pages,
                mockedBook.editorial,
                mockedBook.category.id,
                mockedBook.description,
                mockedBook.photoUrl
            )).thenReturn(expectedResponse)

            createBookViewModel.model.observeForever(observer)

            createBookViewModel.createBook(
                mockedBook.title,
                mockedBook.author,
                mockedBook.pages,
                mockedBook.editorial,
                mockedBook.category.id,
                mockedBook.description,
                mockedBook.photoUrl)

            verify(observer).onChanged(CreateBookViewModel.UiModel.ServerError)

        }
    }

    @Test
    fun createBookNetworkError(){

        runBlocking {
            val expectedResponse = DataResponse.NetworkError

            whenever(createBook.invoke(
                mockedBook.title,
                mockedBook.author,
                mockedBook.pages,
                mockedBook.editorial,
                mockedBook.category.id,
                mockedBook.description,
                mockedBook.photoUrl
            )).thenReturn(expectedResponse)

            createBookViewModel.model.observeForever(observer)

            createBookViewModel.createBook(
                mockedBook.title,
                mockedBook.author,
                mockedBook.pages,
                mockedBook.editorial,
                mockedBook.category.id,
                mockedBook.description,
                mockedBook.photoUrl)

            verify(observer).onChanged(CreateBookViewModel.UiModel.NetworkError)

        }
    }


}