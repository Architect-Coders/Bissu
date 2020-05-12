package com.architectcoders.bissu.login.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.architectcoders.bissu.FakeUserRemoteDatasource
import com.architectcoders.bissu.initMockedDi
import com.architectcoders.data.source.UserRemoteDatasource
import com.architectcoders.domain.entities.DataResponse
import com.architectcoders.domain.usecases.DoLogin
import com.example.testshared.mockedPassword
import com.example.testshared.mockedServerError
import com.example.testshared.mockedUsername
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
class LoginIntegrationTests  :  AutoCloseKoinTest()  {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var observer: Observer<LoginViewModel.UiModel>

    private lateinit var viewModel: LoginViewModel

    @Before
    fun setUp() {
        val vmModule = module {
            factory { LoginViewModel(get(), get()) }
            factory { DoLogin(get()) }
        }
        initMockedDi(vmModule)
        viewModel = get()
    }

    @Test
    fun doLoginSuccess(){
        viewModel.model.observeForever(observer)

        viewModel.doLogin(mockedUsername, mockedPassword)

        verify(observer).onChanged(LoginViewModel.UiModel.LoginContent(true))
    }

    @Test
    fun doLoginNetworkError(){
        runBlocking {

            val remoteDataSource = get<UserRemoteDatasource>() as FakeUserRemoteDatasource
            remoteDataSource.mockedResponse = DataResponse.NetworkError

            viewModel.model.observeForever(observer)

            viewModel.doLogin(mockedUsername, mockedPassword)

            verify(observer).onChanged(LoginViewModel.UiModel.NetworkError)
        }
    }

    @Test
    fun doLoginServerError(){
        runBlocking {

            val remoteDataSource = get<UserRemoteDatasource>() as FakeUserRemoteDatasource
            remoteDataSource.mockedResponse = DataResponse.ServerError(mockedServerError)

            viewModel.model.observeForever(observer)

            viewModel.doLogin(mockedUsername, mockedPassword)

            verify(observer).onChanged(LoginViewModel.UiModel.ServerError)
        }
    }

}