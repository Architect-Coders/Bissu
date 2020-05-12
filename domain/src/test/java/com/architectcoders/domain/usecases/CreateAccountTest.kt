package com.architectcoders.domain.usecases


import com.architectcoders.domain.repositories.UserRepository
import com.example.testshared.mockedUser
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CreateAccountTest {

    @Mock
    lateinit var userRepository: UserRepository

    lateinit var createAccount : CreateAccount


    @Before
    fun init(){
        createAccount = CreateAccount(userRepository)
    }

    @Test
    fun invoqueUserRepository(){
        runBlocking {
             createAccount.invoke(mockedUser.username, mockedUser.email, mockedUser.firstName,
                mockedUser.lastName, mockedUser.password, mockedUser.photoUrl)

            verify(userRepository).createAccount(mockedUser.username, mockedUser.email, mockedUser.firstName,
                mockedUser.lastName, mockedUser.password, mockedUser.photoUrl)
        }

    }
}