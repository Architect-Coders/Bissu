package com.architectcoders.bissu.observation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.architectcoders.domain.entities.DataResponse
import com.architectcoders.domain.usecases.CreateObservation
import com.architectcoders.domain.usecases.GetBook
import com.architectcoders.domain.usecases.GetSessionUser
import com.example.testshared.*
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
class ObservationViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var observer: Observer<ObservationViewModel.UiModel>

    @Mock
    lateinit var getSessionUser: GetSessionUser

    @Mock
    lateinit var getBook: GetBook

    @Mock
    lateinit var createObservation: CreateObservation

    private lateinit var observationViewModel: ObservationViewModel

    @Before
    fun init() {
        observationViewModel =
            ObservationViewModel(getSessionUser, getBook, createObservation, Dispatchers.Unconfined)
    }

    @Test
    fun getSessionError() {
        runBlocking {
            val expectedResponse = DataResponse.SessionError

            whenever(getSessionUser.invoke()).thenReturn(expectedResponse)

            observationViewModel.model.observeForever(observer)

            observationViewModel.onGetUserClicked()

            verify(observer).onChanged(ObservationViewModel.UiModel.SessionError)

        }
    }

    @Test
    fun getUserSessionSuccess() {
        runBlocking {
            val expectedResponse = DataResponse.Success(mockedUser)

            whenever(getSessionUser.invoke()).thenReturn(expectedResponse)

            observationViewModel.model.observeForever(observer)

            observationViewModel.onGetUserClicked()

            verify(observer).onChanged(ObservationViewModel.UiModel.UserContent(mockedUser))

        }
    }

    @Test
    fun getBookSuccess() {
        runBlocking {
            val expectedResponse = DataResponse.Success(mockedBook)

            whenever(getBook.invoke(mockedBook.id)).thenReturn(expectedResponse)

            observationViewModel.model.observeForever(observer)

            observationViewModel.getBook(mockedBook.id)

            verify(observer).onChanged(ObservationViewModel.UiModel.BookContent(mockedBook))
        }
    }

    @Test
    fun getBookServerError() {
        runBlocking {
            val expectedResponse = DataResponse.ServerError(mockedServerError)

            whenever(getBook.invoke(mockedBook.id)).thenReturn(expectedResponse)

            observationViewModel.model.observeForever(observer)

            observationViewModel.getBook(mockedBook.id)

            verify(observer).onChanged(ObservationViewModel.UiModel.ServerError)
        }
    }

    @Test
    fun createObservationNetworkError(){
        runBlocking {

            val expectedResponse = DataResponse.NetworkError

            whenever(createObservation.invoke(
                mockedUser.id,
                mockedBook.id,
                mockedDescription,
                mockedPages
            )).thenReturn(expectedResponse)

            observationViewModel.model.observeForever(observer)

            observationViewModel.createObservation(
                mockedUser.id,
                mockedBook.id,
                mockedDescription,
                mockedPages
            )

            verify(observer).onChanged(ObservationViewModel.UiModel.NetworkError)
        }
    }


   @Test
    fun createObservationServerError() {
        runBlocking {

            val expectedResponse = DataResponse.ServerError(mockedServerError)

            whenever(createObservation.invoke(
                mockedUser.id,
                mockedBook.id,
                mockedDescription,
                mockedPages
            )).thenReturn(expectedResponse)

            observationViewModel.model.observeForever(observer)
            observationViewModel.createObservation(
                mockedUser.id,
                mockedBook.id,
                mockedDescription,
                mockedPages
            )
            verify(observer).onChanged(ObservationViewModel.UiModel.ServerError)
        }
    }

       @Test
       fun createObservationSuccess(){

           runBlocking {
               val expectedResponse = DataResponse.Success(mockedObservation)

               whenever(createObservation.invoke(
                   mockedUser.id,
                   mockedBook.id,
                   mockedDescription,
                   mockedPages
               )).thenReturn(expectedResponse)

               observationViewModel.model.observeForever(observer)

               observationViewModel.createObservation(
                   mockedUser.id,
                   mockedBook.id,
                   mockedDescription,
                   mockedPages
               )

               verify(observer).onChanged(ObservationViewModel.UiModel.CreateObservationSuccess)
           }
       }


}