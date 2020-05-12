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
class UpdateAccountTest{

    @Mock
    lateinit var userRepository : UserRepository

    lateinit var updateAccount: UpdateAccount

    @Before
    fun init(){
        updateAccount = UpdateAccount(userRepository)
    }

    @Test
    fun invoqueCategoriesRepository(){
        runBlocking {
            updateAccount.invoke(mockedUser)
            verify(userRepository).updateUser(mockedUser)
        }
    }

}