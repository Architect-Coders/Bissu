package com.architectcoders.domain.usecases


import com.architectcoders.domain.reositories.ObservationRepository
import com.example.testshared.mockedUser
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetObservationsByUserTest{
    @Mock
    lateinit var observationRepository: ObservationRepository

    lateinit var getObservationsByUser: GetObservationsByUser


    @Before
    fun init(){
        getObservationsByUser = GetObservationsByUser(observationRepository)
    }

    @Test
    fun invoqueCategoriesRepository(){
        runBlocking {
            getObservationsByUser.invoke(mockedUser.id,false)

            verify(observationRepository).getObservationsByUser(mockedUser.id,false)

        }
    }
}