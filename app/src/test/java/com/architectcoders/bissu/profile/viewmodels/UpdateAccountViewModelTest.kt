package com.architectcoders.bissu.profile.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.architectcoders.domain.entities.DataResponse
import com.architectcoders.domain.usecases.GetSessionUser
import com.architectcoders.domain.usecases.UpdateAccount
import com.example.testshared.mockedServerError
import com.example.testshared.mockedUser
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UpdateAccountViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var updateAccount: UpdateAccount

    @Mock
    lateinit var getSessionUser: GetSessionUser

    @Mock
    lateinit var observer: Observer<UpdateAccountViewModel.UiModel>

    private lateinit var updateAccountViewModel: UpdateAccountViewModel

    @Before
    fun init() {
        updateAccountViewModel =
            UpdateAccountViewModel(updateAccount, getSessionUser, Dispatchers.Unconfined)
    }

    @Test
    fun getSessionError() {
        runBlocking {
            val expectedResponse = DataResponse.SessionError

            whenever(getSessionUser.invoke()).thenReturn(expectedResponse)

            updateAccountViewModel.model.observeForever(observer)

            updateAccountViewModel.getCurrentUser()

            verify(observer).onChanged(UpdateAccountViewModel.UiModel.SessionError)

        }
    }

    @Test
    fun getSessionSuccess() {
        runBlocking {
            val expectedResponse = DataResponse.Success(mockedUser)

            whenever(getSessionUser.invoke()).thenReturn(expectedResponse)

            updateAccountViewModel.model.observeForever(observer)

            updateAccountViewModel.getCurrentUser()

            verify(observer).onChanged(UpdateAccountViewModel.UiModel.UserSessionContent(mockedUser))

        }
    }

    @Test
    fun updateAccountSuccess(){

        runBlocking {
            val expectedResponse = DataResponse.Success(mockedUser)

            whenever(updateAccount.invoke(mockedUser)).thenReturn(expectedResponse)

            updateAccountViewModel.model.observeForever(observer)

            updateAccountViewModel.updateAccount( mockedUser)

            verify(observer).onChanged(UpdateAccountViewModel.UiModel.UpdateAccountContent(mockedUser))

        }
    }

    @Test
    fun updateAccountServerError(){

        runBlocking {
            val expectedResponse = DataResponse.ServerError(mockedServerError)

            whenever(updateAccount.invoke(mockedUser)).thenReturn(expectedResponse)

            updateAccountViewModel.model.observeForever(observer)

            updateAccountViewModel.updateAccount(
                mockedUser
            )
            verify(observer).onChanged(UpdateAccountViewModel.UiModel.ServerError)

        }
    }

    @Test
    fun updateAccountNetworkError(){

        runBlocking {
            val expectedResponse = DataResponse.NetworkError

            whenever(updateAccount.invoke(mockedUser)).thenReturn(expectedResponse)

            updateAccountViewModel.model.observeForever(observer)

            updateAccountViewModel.updateAccount(mockedUser)

            verify(observer).onChanged(UpdateAccountViewModel.UiModel.NetworkError)

        }
    }

}