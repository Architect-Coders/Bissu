package com.architectcoders.bissu.home.myObservations

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.architectcoders.bissu.FakeObservationLocalDatasource
import com.architectcoders.bissu.FakeObservationRemoteDatasource
import com.architectcoders.bissu.FakeUserLocalDatasource
import com.architectcoders.bissu.book.viewModel.CreateBookViewModel
import com.architectcoders.bissu.initMockedDi
import com.architectcoders.data.source.ObservationLocalDataSource
import com.architectcoders.data.source.ObservationRemoteDatasource
import com.architectcoders.data.source.UserLocalDataSource
import com.architectcoders.domain.entities.DataResponse
import com.architectcoders.domain.usecases.CreateBook
import com.architectcoders.domain.usecases.GetCategories
import com.architectcoders.domain.usecases.GetObservationsByUser
import com.architectcoders.domain.usecases.GetSessionUser
import com.example.testshared.mockedObservation
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
class MyObservationsIntegrationTest : AutoCloseKoinTest(){

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var observer: Observer<MyObservationsViewModel.UiModel>

    private lateinit var viewModel: MyObservationsViewModel

    @Before
    fun setUp() {
        val vmModule = module {
            factory { MyObservationsViewModel(get(), get(), get()) }
            factory { GetSessionUser(get()) }
            factory { GetObservationsByUser(get()) }
        }
        initMockedDi(vmModule)
        viewModel = get()
    }

    @Test
    fun getSessionUserSessionError(){
        val localDataSource = get<UserLocalDataSource>() as FakeUserLocalDatasource

        localDataSource.isEmpty = true

        viewModel.model.observeForever(observer)

        viewModel.getSessionUser()

        verify(observer).onChanged(MyObservationsViewModel.UiModel.SessionError)
    }

    @Test
    fun getSessionUserSessionSuccess(){

        viewModel.model.observeForever(observer)

        viewModel.getSessionUser()

        verify(observer).onChanged(MyObservationsViewModel.UiModel.UserContent(mockedUser))
    }

    @Test
    fun getObservationByLocalDatasource(){

        val localDataSource = get<ObservationLocalDataSource>() as FakeObservationLocalDatasource

        localDataSource.isEmpty = false

        viewModel.model.observeForever(observer)

        viewModel.getObservationsByUser(mockedUser.id)

        verify(observer).onChanged(MyObservationsViewModel.UiModel.ObservationContent(
            listOf(mockedObservation)))

    }

    @Test
    fun getObservationByRemoteDatasource(){

        viewModel.model.observeForever(observer)

        viewModel.getObservationsByUser(mockedUser.id)

        verify(observer).onChanged(MyObservationsViewModel.UiModel.ObservationContent(
            listOf(mockedObservation)))

    }

    @Test
    fun getObservationByRemoteDatasourceNetworkError(){

        val localDataSource = get<ObservationLocalDataSource>() as FakeObservationLocalDatasource
        localDataSource.isEmpty = true

        val remoteDataSource = get<ObservationRemoteDatasource>() as FakeObservationRemoteDatasource
        remoteDataSource.mockedListResponse = DataResponse.NetworkError

        viewModel.model.observeForever(observer)

        viewModel.getObservationsByUser(mockedUser.id)

        verify(observer).onChanged(MyObservationsViewModel.UiModel.NetworkError)

    }

    @Test
    fun getObservationByRemoteDatasourceServerError(){

        val localDataSource = get<ObservationLocalDataSource>() as FakeObservationLocalDatasource
        localDataSource.isEmpty = true

        val remoteDataSource = get<ObservationRemoteDatasource>() as FakeObservationRemoteDatasource
        remoteDataSource.mockedListResponse = DataResponse.ServerError(mockedServerError)

        viewModel.model.observeForever(observer)

        viewModel.getObservationsByUser(mockedUser.id)

        verify(observer).onChanged(MyObservationsViewModel.UiModel.ServerError)

    }

}