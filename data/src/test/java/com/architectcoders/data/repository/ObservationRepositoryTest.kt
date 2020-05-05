package com.architectcoders.data.repository

import com.architectcoders.data.source.ObservationLocalDataSource
import com.architectcoders.data.source.ObservationRemoteDatasource
import com.architectcoders.domain.entities.DataResponse
import com.architectcoders.domain.entities.Observation
import com.example.testshared.mockedObservation
import com.example.testshared.mockedServerError
import com.example.testshared.mockedUser
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ObservationRepositoryTest {

    @Mock
    lateinit var localDataSource: ObservationLocalDataSource
    @Mock
    lateinit var remoteDataSource: ObservationRemoteDatasource

    lateinit var observationRepository: ObservationRepository

    @Before
    fun init() {
        observationRepository = ObservationRepository(localDataSource, remoteDataSource)
    }

    @Test
    fun createObservationValid() {
        runBlocking {

            observationRepository.createObservation(mockedObservation)

            verify(remoteDataSource).createObservation(mockedObservation)
        }
    }

    @Test
    fun createObservationNetworkError() {
        runBlocking {
            val resultExpected = DataResponse.NetworkError
            whenever(remoteDataSource.createObservation(mockedObservation)).thenReturn(
                resultExpected
            )
            val result = observationRepository.createObservation(mockedObservation)

            assertEquals(resultExpected, result)
        }
    }

    @Test
    fun createObservationServerError() {
        runBlocking {
            val resultExpected = DataResponse.ServerError(mockedServerError)

            whenever(remoteDataSource.createObservation(mockedObservation)).thenReturn(
                resultExpected
            )

            val result = observationRepository.createObservation(mockedObservation)

            assertEquals(resultExpected, result)
        }
    }

    @Test
    fun getObservationsForceRefresh() {
        runBlocking {
            val response: DataResponse<List<Observation>> =
                DataResponse.Success(listOf(mockedObservation))

            whenever(localDataSource.isEmpty()).thenReturn(false)
            whenever(remoteDataSource.getObservationsByUser(mockedUser.id)).thenReturn(response)

            observationRepository.getObservationsByUser(mockedUser.id, true)

            verify(remoteDataSource).getObservationsByUser(mockedUser.id)
        }
    }

    @Test
    fun getObservationsNetworkError() {
        runBlocking {
            val resultExpected: DataResponse<List<Observation>> = DataResponse.NetworkError

            whenever(localDataSource.isEmpty()).thenReturn(true)
            whenever(remoteDataSource.getObservationsByUser(mockedUser.id)).thenReturn(
                resultExpected
            )

            val result = observationRepository.getObservationsByUser(mockedUser.id, true)

            assertEquals(resultExpected, result)
        }
    }

    @Test
    fun getObservationsLocalRepository() {
        runBlocking {

            whenever(localDataSource.isEmpty()).thenReturn(false)
            whenever(localDataSource.getObservationsByUser(mockedUser.id)).thenReturn(
                listOf(
                    mockedObservation
                )
            )
            observationRepository.getObservationsByUser(mockedUser.id, false)

            verify(localDataSource).getObservationsByUser(mockedUser.id)
        }
    }

}

