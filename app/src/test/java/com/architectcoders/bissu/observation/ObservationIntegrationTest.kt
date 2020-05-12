package com.architectcoders.bissu.observation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.architectcoders.bissu.FakeObservationRemoteDatasource
import com.architectcoders.bissu.initMockedDi
import com.architectcoders.data.source.ObservationRemoteDatasource
import com.architectcoders.domain.entities.DataResponse
import com.architectcoders.domain.usecases.CreateObservation
import com.architectcoders.domain.usecases.GetBook
import com.architectcoders.domain.usecases.GetSessionUser
import com.example.testshared.*
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
class ObservationIntegrationTest : AutoCloseKoinTest() {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var observer: Observer<ObservationViewModel.UiModel>

    private lateinit var viewModel: ObservationViewModel

    @Before
    fun setUp() {
        val vmModule = module {
            factory { ObservationViewModel(get(), get(), get(), get()) }
            factory { GetSessionUser(get()) }
            factory { GetBook(get()) }
            factory { CreateObservation(get()) }
        }
        initMockedDi(vmModule)
        viewModel = get()
    }

    @Test
    fun CreateObservationSuccess() {

        viewModel.model.observeForever(observer)

        viewModel.createObservation(
            mockedUser.id,
            mockedBook.id,
            mockedDescription,
            mockedPages
        )

        verify(observer).onChanged(ObservationViewModel.UiModel.CreateObservationSuccess)
    }

    @Test
    fun createObservationServerError() {
        val remoteDataSource = get<ObservationRemoteDatasource>() as FakeObservationRemoteDatasource
        remoteDataSource.mockedObservationResponse = DataResponse.ServerError(mockedServerError)

        viewModel.model.observeForever(observer)

        viewModel.createObservation(
            mockedUser.id,
            mockedBook.id,
            mockedDescription,
            mockedPages
        )

        verify(observer).onChanged(ObservationViewModel.UiModel.ServerError)
    }

    @Test
    fun createObservationNetworkError() {
        val remoteDataSource = get<ObservationRemoteDatasource>() as FakeObservationRemoteDatasource
        remoteDataSource.mockedObservationResponse = DataResponse.NetworkError

        viewModel.model.observeForever(observer)

        viewModel.createObservation(
            mockedUser.id,
            mockedBook.id,
            mockedDescription,
            mockedPages
        )
        verify(observer).onChanged(ObservationViewModel.UiModel.NetworkError)
    }

}