package com.architectcoders.domain.usecases

import com.architectcoders.domain.reositories.ObservationRepository
import com.example.testshared.mockedObservation
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CreateObservationTest {

    @Mock
    lateinit var observationRepository: ObservationRepository

    lateinit var createObservation : CreateObservation

    @Before
    fun init(){
        createObservation = CreateObservation(observationRepository)
    }

    @Test
    fun invokeCreateObservationReposiitory() {
        runBlocking {

            createObservation.invoke(mockedObservation)

            verify(observationRepository).createObservation(mockedObservation)
        }

    }
}