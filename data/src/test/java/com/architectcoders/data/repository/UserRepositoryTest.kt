package com.architectcoders.data.repository

import com.architectcoders.data.source.UserLocalDataSource
import com.architectcoders.data.source.UserRemoteDatasource
import com.architectcoders.domain.entities.DataResponse
import com.architectcoders.domain.entities.User
import com.example.testshared.mockedPassword
import com.example.testshared.mockedServerError
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
class UserRepositoryTest {

    @Mock
    private lateinit var localDataSource: UserLocalDataSource
    @Mock
    private lateinit var remoteDatasource: UserRemoteDatasource

    lateinit var userRepository: UserRepository

    @Before
    fun init() {
        userRepository = UserRepository(localDataSource, remoteDatasource)
    }

    @Test
    fun doLoginValid() {
        runBlocking {
            val loginResponse = DataResponse.Success(mockedUser)
            whenever(remoteDatasource.doLogin(mockedUsername, mockedPassword)).thenReturn(
                loginResponse
            )
            val result = userRepository.doLogin(mockedUsername, mockedPassword)
            assertEquals(loginResponse, result)
        }

    }

    @Test
    fun doLoginInvalid() {
        runBlocking {
            val loginResponse = DataResponse.ServerError(mockedServerError)
            whenever(remoteDatasource.doLogin(mockedUsername, mockedPassword)).thenReturn(
                loginResponse
            )
            val result = userRepository.doLogin(mockedUsername, mockedPassword)
            assertEquals(loginResponse, result)
        }
    }

    @Test
    fun doLoginAndSaveUser() {
        runBlocking {
            val loginResponse = DataResponse.Success(mockedUser)
            whenever(remoteDatasource.doLogin(mockedUsername, mockedPassword)).thenReturn(
                loginResponse
            )

            userRepository.doLogin(mockedUsername, mockedPassword)

            verify(localDataSource).saveUser(mockedUser)
        }
    }

    @Test
    fun doLoginNetworkError() {
        runBlocking {
            val loginResponse = DataResponse.NetworkError
            whenever(remoteDatasource.doLogin(mockedUsername, mockedPassword)).thenReturn(
                loginResponse
            )
            val result = userRepository.doLogin(mockedUsername, mockedPassword)
            assertEquals(loginResponse, result)
        }
    }

    @Test
    fun updateAccountAndSave() {
        runBlocking {
            val loginResponse = DataResponse.Success(mockedUser)
            whenever(remoteDatasource.updateAccount(mockedUser)).thenReturn(loginResponse)

            userRepository.updateUser(mockedUser)

            verify(localDataSource).updateUser(mockedUser)
        }
    }

    @Test
    fun getSessionUserValid() {
        runBlocking {
            val loginResponse = DataResponse.Success(mockedUser)
            whenever(localDataSource.isEmpty()).thenReturn(false)
            whenever(localDataSource.getUser()).thenReturn(mockedUser)

            val result: DataResponse<User> = userRepository.getSessionUser()
            assertEquals(loginResponse, result)
        }
    }

    @Test
    fun getSessionUserErrorSession() {
        runBlocking {

            val loginResponse = DataResponse.SessionError
            whenever(localDataSource.isEmpty()).thenReturn(true)

            val result: DataResponse<User> = userRepository.getSessionUser()

            assertEquals(loginResponse, result)
        }
    }

}