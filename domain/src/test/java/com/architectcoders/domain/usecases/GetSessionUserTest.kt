package com.architectcoders.domain.usecases

import com.architectcoders.domain.reositories.UserRepository
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetSessionUserTest{

    @Mock
    lateinit var userRepository : UserRepository

    lateinit var getSessionUser: GetSessionUser

    @Before
    fun init(){
        getSessionUser = GetSessionUser(userRepository)
    }

    @Test
    fun invoqueCategoriesRepository(){
        runBlocking {
            getSessionUser.invoke()

            verify(userRepository).getSessionUser()

        }
    }
}