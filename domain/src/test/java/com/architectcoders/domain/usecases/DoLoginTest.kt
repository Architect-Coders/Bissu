package com.architectcoders.domain.usecases

import com.architectcoders.domain.reositories.UserRepository
import com.example.testshared.mockedPassword
import com.example.testshared.mockedUsername
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DoLoginTest{

    @Mock
    lateinit var  userRepository: UserRepository

    lateinit var  doLogin: DoLogin

    @Before
    fun init(){
        doLogin = DoLogin(userRepository)
    }

    @Test
    fun invoqueUserRepository(){
        runBlocking {
            doLogin.invoke(mockedUsername, mockedPassword)
            verify(userRepository).doLogin(mockedUsername, mockedPassword)
        }

    }
}