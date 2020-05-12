package com.architectcoders.bissu.home.myObservations

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.architectcoders.domain.entities.DataResponse
import com.architectcoders.domain.usecases.GetObservationsByUser
import com.architectcoders.domain.usecases.GetSessionUser
import com.example.testshared.mockedObservation
import com.example.testshared.mockedServerError
import com.example.testshared.mockedUser
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
class MyObservationsViewModelTest{

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var getAccount: GetSessionUser;
    @Mock
    private lateinit var getObservationsByUser: GetObservationsByUser;

    @Mock
    lateinit var observer: Observer<MyObservationsViewModel.UiModel>

    lateinit var myObservationsViewModel: MyObservationsViewModel

    @Before
    fun init(){
        myObservationsViewModel = MyObservationsViewModel(getAccount,getObservationsByUser,
            Dispatchers.Unconfined)
    }


    @Test
    fun getUserSessionSuccess(){
        runBlocking {
            val expectedResponse = DataResponse.Success(mockedUser)
            whenever(getAccount.invoke()).thenReturn(expectedResponse)

            myObservationsViewModel.model.observeForever(observer)
            myObservationsViewModel.getSessionUser()

            verify(observer).onChanged(MyObservationsViewModel.UiModel.UserContent(mockedUser))
        }
    }

    @Test
    fun getUserSessionSessionError(){
        runBlocking {
            val expectedResponse = DataResponse.SessionError
            whenever(getAccount.invoke()).thenReturn(expectedResponse)

            myObservationsViewModel.model.observeForever(observer)
            myObservationsViewModel.getSessionUser()

            verify(observer).onChanged(MyObservationsViewModel.UiModel.SessionError)
        }
    }

    @Test
    fun getObservationSuccess(){
        runBlocking {
            val observationList = listOf(mockedObservation)
            val expectedResponse = DataResponse.Success(observationList)
            whenever(getObservationsByUser.invoke(mockedUser.id)).thenReturn(expectedResponse)

            myObservationsViewModel.model.observeForever(observer)
            myObservationsViewModel.getObservationsByUser(mockedUser.id)

            verify(observer).onChanged(MyObservationsViewModel.UiModel.ObservationContent(observationList))
        }
    }

    @Test
    fun getObservationServerError(){
        runBlocking {

            val expectedResponse = DataResponse.ServerError(mockedServerError)
            whenever(getObservationsByUser.invoke(mockedUser.id)).thenReturn(expectedResponse)

            myObservationsViewModel.model.observeForever(observer)
            myObservationsViewModel.getObservationsByUser(mockedUser.id)

            verify(observer).onChanged(MyObservationsViewModel.UiModel.ServerError)
        }
    }

    @Test
    fun getObservationNetworkError(){
        runBlocking {

            val expectedResponse = DataResponse.NetworkError
            whenever(getObservationsByUser.invoke(mockedUser.id)).thenReturn(expectedResponse)

            myObservationsViewModel.model.observeForever(observer)
            myObservationsViewModel.getObservationsByUser(mockedUser.id)

            verify(observer).onChanged(MyObservationsViewModel.UiModel.NetworkError)
        }
    }

}