package com.architectcoders.bissu.profile.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.architectcoders.bissu.FakeUserRemoteDatasource
import com.architectcoders.bissu.initMockedDi
import com.architectcoders.data.source.UserRemoteDatasource
import com.architectcoders.domain.entities.DataResponse
import com.architectcoders.domain.usecases.GetSessionUser
import com.architectcoders.domain.usecases.UpdateAccount
import com.example.testshared.mockedServerError
import com.example.testshared.mockedUser
import com.example.testshared.mockedUserUpdate
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
class ChangePasswordIntegrationTest  :  AutoCloseKoinTest(){

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var observer: Observer<ChangePasswordViewModel.UiModel>

    private lateinit var viewModel: ChangePasswordViewModel

    @Before
    fun setUp() {
        val vmModule = module {
            factory { ChangePasswordViewModel(get(), get(), get()) }
            factory { UpdateAccount(get()) }
            factory { GetSessionUser(get()) }
        }
        initMockedDi(vmModule)
        viewModel = get()
    }

    @Test
    fun getUserSessionSuccess(){

        viewModel.model.observeForever(observer)

        viewModel.getCurrentUser()

        verify(observer).onChanged(ChangePasswordViewModel.UiModel.UserSessionContent(mockedUser))
    }

    @Test
    fun updateAccountServerError(){

        val remoteDataSource = get<UserRemoteDatasource>() as FakeUserRemoteDatasource
        remoteDataSource.mockedResponse = DataResponse.ServerError(mockedServerError)

        viewModel.model.observeForever(observer)

        viewModel.updateAccount( mockedUser)

        verify(observer).onChanged(ChangePasswordViewModel.UiModel.ServerError)
    }

    @Test
    fun updateAccountNetworkError(){

        val remoteDataSource = get<UserRemoteDatasource>() as FakeUserRemoteDatasource
        remoteDataSource.mockedResponse = DataResponse.NetworkError

        viewModel.model.observeForever(observer)

        viewModel.updateAccount(mockedUser)

        verify(observer).onChanged(ChangePasswordViewModel.UiModel.NetworkError)
    }

    @Test
    fun updateAccountSuccess(){

        val remoteDataSource = get<UserRemoteDatasource>() as FakeUserRemoteDatasource
        remoteDataSource.user = mockedUserUpdate

        remoteDataSource.mockedResponse = DataResponse.Success(remoteDataSource.user)

        viewModel.model.observeForever(observer)

        viewModel.updateAccount(mockedUserUpdate)

        verify(observer).onChanged(ChangePasswordViewModel.UiModel.ChangePasswordContent(
            mockedUserUpdate
        ))

    }

}