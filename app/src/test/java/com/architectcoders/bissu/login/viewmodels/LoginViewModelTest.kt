package com.architectcoders.bissu.login.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.architectcoders.domain.entities.DataResponse
import com.architectcoders.domain.usecases.DoLogin
import com.example.testshared.mockedPassword
import com.example.testshared.mockedServerError
import com.example.testshared.mockedUser
import com.example.testshared.mockedUsername
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
class LoginViewModelTest{

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var doLogin: DoLogin
    @Mock
    lateinit var observer: Observer<LoginViewModel.UiModel>

    private lateinit var loginViewModel: LoginViewModel

    @Before
    fun init(){
        loginViewModel = LoginViewModel(doLogin, Dispatchers.Unconfined)
    }

    @Test
    fun doLoginObserver(){
        runBlocking {
            val expectedResponse = DataResponse.Success(mockedUser)

            whenever(doLogin.invoke(mockedUsername, mockedPassword)).thenReturn(expectedResponse)

            loginViewModel.model.observeForever(observer)

            loginViewModel.doLogin(mockedUsername, mockedPassword)

            verify(observer).onChanged(LoginViewModel.UiModel.LoginContent(true))
        }
    }

    @Test
    fun doLoginServerError(){
        runBlocking {
            val expectedResponse = DataResponse.ServerError(mockedServerError)

            whenever(doLogin.invoke(mockedUsername, mockedPassword)).thenReturn(expectedResponse)

            loginViewModel.model.observeForever(observer)

            loginViewModel.doLogin(mockedUsername, mockedPassword)

            verify(observer).onChanged(LoginViewModel.UiModel.ServerError)

        }

    }

    @Test
    fun doLoginNetworkError(){
        runBlocking {
            val expectedResponse = DataResponse.NetworkError

            whenever(doLogin.invoke(mockedUsername, mockedPassword)).thenReturn(expectedResponse)

            loginViewModel.model.observeForever(observer)

            loginViewModel.doLogin(mockedUsername, mockedPassword)

            verify(observer).onChanged(LoginViewModel.UiModel.NetworkError)

        }

    }


    @Test
    fun doLoginLoading(){
        runBlocking {
            val expectedResponse = DataResponse.NetworkError

            whenever(doLogin.invoke(mockedUsername, mockedPassword)).thenReturn(expectedResponse)

            loginViewModel.model.observeForever(observer)

            loginViewModel.doLogin(mockedUsername, mockedPassword)

            verify(observer).onChanged(LoginViewModel.UiModel.Loading)

        }

    }


}