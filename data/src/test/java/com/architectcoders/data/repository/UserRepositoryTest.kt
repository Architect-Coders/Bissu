package com.architectcoders.data.repository

import com.architectcoders.data.source.LoginLocalDataSource
import com.architectcoders.data.source.LoginRemoteDatasource
import com.example.testshared.mockedPassword
import com.example.testshared.mockedUser
import com.example.testshared.mockedUsername
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
class UserRepositoryTest{

    @Mock
    private lateinit var localDataSource: LoginLocalDataSource
    @Mock
    private lateinit var remoteDatasource: LoginRemoteDatasource

    lateinit var userRepository: UserRepository

    @Before
    fun init(){
        userRepository = UserRepository(localDataSource,remoteDatasource)
    }

    @Test
    fun doLoginValid(){
        runBlocking {
            whenever(remoteDatasource.doLogin(mockedUsername, mockedPassword)).thenReturn(mockedUser)
            val result : Boolean = userRepository.doLogin(mockedUsername, mockedPassword)
            assertTrue( result)
        }

    }

    @Test
    fun doLoginInvalid(){
        runBlocking {
            whenever(remoteDatasource.doLogin(mockedUsername, mockedPassword)).thenReturn(null)
            val result : Boolean = userRepository.doLogin(mockedUsername, mockedPassword)
            assertFalse( result)
        }
    }

    @Test
    fun doLoginAndSaveUser(){
        runBlocking {
            whenever(remoteDatasource.doLogin(mockedUsername, mockedPassword)).thenReturn(mockedUser)

            userRepository.doLogin(mockedUsername, mockedPassword)

            verify(localDataSource).saveUser(mockedUser)
        }
    }


}