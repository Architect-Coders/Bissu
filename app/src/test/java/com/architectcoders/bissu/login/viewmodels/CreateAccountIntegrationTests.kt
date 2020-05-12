package com.architectcoders.bissu.login.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.architectcoders.bissu.FakeUserRemoteDatasource
import com.architectcoders.bissu.initMockedDi
import com.architectcoders.data.source.UserRemoteDatasource
import com.architectcoders.domain.entities.DataResponse
import com.architectcoders.domain.usecases.CreateAccount
import com.example.testshared.mockedServerError
import com.example.testshared.mockedUser
import com.nhaarman.mockitokotlin2.verify
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
class CreateAccountIntegrationTests :  AutoCloseKoinTest() {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var observer: Observer<CreateAccountViewModel.UiModel>

    private lateinit var viewModel: CreateAccountViewModel

    @Before
    fun setUp() {
        val vmModule = module {
            factory { CreateAccountViewModel(get(), get()) }
            factory { CreateAccount(get()) }
        }

        initMockedDi(vmModule)
        viewModel = get()
    }

    @Test
    fun createAccountSuccess(){

        viewModel.model.observeForever(observer)

        viewModel.createAccount(
            mockedUser.username,
            mockedUser.email,
            mockedUser.firstName,
            mockedUser.lastName,
            mockedUser.password,
            mockedUser.photoUrl
        )

        verify(observer).onChanged(CreateAccountViewModel.UiModel.CreateAccountContent(mockedUser))
    }

    @Test
    fun createAccountServerError(){
        val remoteDataSource = get<UserRemoteDatasource>() as FakeUserRemoteDatasource
        remoteDataSource.mockedResponse = DataResponse.ServerError(mockedServerError)

        viewModel.model.observeForever(observer)

        viewModel.createAccount(
            mockedUser.username,
            mockedUser.email,
            mockedUser.firstName,
            mockedUser.lastName,
            mockedUser.password,
            mockedUser.photoUrl
        )
        verify(observer).onChanged(CreateAccountViewModel.UiModel.ServerError)

    }

    @Test
    fun createAccountNetworkError(){
        val remoteDataSource = get<UserRemoteDatasource>() as FakeUserRemoteDatasource
        remoteDataSource.mockedResponse = DataResponse.NetworkError

        viewModel.model.observeForever(observer)

        viewModel.createAccount(
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