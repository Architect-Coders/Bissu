package com.architectcoders.bissu.login.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.architectcoders.domain.entities.DataResponse
import com.architectcoders.domain.usecases.CreateAccount
import com.example.testshared.mockedServerError
import com.example.testshared.mockedUser
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CreateAccountViewModelTest{

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var observer: Observer<CreateAccountViewModel.UiModel>

    @Mock
    lateinit var createAccount : CreateAccount

    lateinit var createAccountViewModel : CreateAccountViewModel

    @Before
    fun init(){
        createAccountViewModel = CreateAccountViewModel(createAccount, Dispatchers.Unconfined)
    }
    @Test
    fun createAccountLoading(){

        runBlocking {
            val expectedResponse = DataResponse.Success(mockedUser)

            whenever(createAccount.invoke(
                mockedUser.username,
                mockedUser.email,
                mockedUser.firstName,
                mockedUser.lastName,
                mockedUser.password,
                mockedUser.photoUrl
            )).thenReturn(expectedResponse)

            createAccountViewModel.model.observeForever(observer)

            createAccountViewModel.createAccount(
                mockedUser.username,
                mockedUser.email,
                mockedUser.firstName,
                mockedUser.lastName,
                mockedUser.password,
                mockedUser.photoUrl
            )

            verify(observer).onChanged(CreateAccountViewModel.UiModel.Loading(true))

        }

    }

    @Test
    fun createAccountSuccess(){

        runBlocking {
            val expectedResponse = DataResponse.Success(mockedUser)
            whenever(createAccount.invoke(
                mockedUser.username,
                mockedUser.email,
                mockedUser.firstName,
                mockedUser.lastName,
                mockedUser.password,
                mockedUser.photoUrl
            )).thenReturn(expectedResponse)

            createAccountViewModel.model.observeForever(observer)

            createAccountViewModel.createAccount(
                mockedUser.username,
                mockedUser.email,
                mockedUser.firstName,
                mockedUser.lastName,
                mockedUser.password,
                mockedUser.photoUrl
            )

            verify(observer).onChanged(CreateAccountViewModel.UiModel.CreateAccountContent(
                mockedUser))
        }

    }

    @Test
    fun createAccountServerError(){

        runBlocking {

            val expectedResponse = DataResponse.ServerError(mockedServerError)
            whenever(createAccount.invoke(
                mockedUser.username,
                mockedUser.email,
                mockedUser.firstName,
                mockedUser.lastName,
                mockedUser.password,
                mockedUser.photoUrl
            )).thenReturn(expectedResponse)

            createAccountViewModel.model.observeForever(observer)

            createAccountViewModel.createAccount(
                mockedUser.username,
                mockedUser.email,
                mockedUser.firstName,
                mockedUser.lastName,
                mockedUser.password,
                mockedUser.photoUrl
            )

            verify(observer).onChanged(CreateAccountViewModel.UiModel.ServerError)
        }

    }

    @Test
    fun createAccountNetworkError(){

        runBlocking {
            val expectedResponse = DataResponse.NetworkError
            whenever(createAccount.invoke(
                mockedUser.username,
                mockedUser.email,
                mockedUser.firstName,
                mockedUser.lastName,
                mockedUser.password,
                mockedUser.photoUrl
            )).thenReturn(expectedResponse)

            createAccountViewModel.model.observeForever(observer)

            createAccountViewModel.createAccount(
                mockedUser.username,
                mockedUser.email,
                mockedUser.firstName,
                mockedUser.lastName,
                mockedUser.password,
                mockedUser.photoUrl
            )

            verify(observer).onChanged(CreateAccountViewModel.UiModel.NetworkError)
        }

    }
}