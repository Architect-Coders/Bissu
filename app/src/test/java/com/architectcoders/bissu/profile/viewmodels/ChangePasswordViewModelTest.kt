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
class ChangePasswordViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var observer: Observer<ChangePasswordViewModel.UiModel>

    @Mock
    lateinit var updateAccount: UpdateAccount

    @Mock
    lateinit var getSessionUser: GetSessionUser

    lateinit var changePasswordViewModel: ChangePasswordViewModel

    @Before
    fun init() {
        changePasswordViewModel =  ChangePasswordViewModel(updateAccount, getSessionUser, Dispatchers.Unconfined)
    }


    @Test
    fun getSessionError() {
        runBlocking {

            val expectedResponse = DataResponse.SessionError

            whenever(getSessionUser.invoke()).thenReturn(expectedResponse)

            changePasswordViewModel.model.observeForever(observer)

            changePasswordViewModel.getCurrentUser()

            verify(observer).onChanged(ChangePasswordViewModel.UiModel.SessionError)
        }
    }

    @Test
    fun getSessionSuccess() {
        runBlocking {

            val expectedResponse = DataResponse.Success(mockedUser)

            whenever(getSessionUser.invoke()).thenReturn(expectedResponse)

            changePasswordViewModel.model.observeForever(observer)

            changePasswordViewModel.getCurrentUser()

            verify(observer).onChanged(ChangePasswordViewModel.UiModel.UserSessionContent(mockedUser))
        }
    }

    @Test
    fun changePasswordLoading() {
        runBlocking {
            changePasswordViewModel.model.observeForever(observer)

            changePasswordViewModel.getCurrentUser()

            verify(observer).onChanged(ChangePasswordViewModel.UiModel.Loading)
        }
    }

    @Test
    fun changePasswordSuccess() {
        runBlocking {

            val expectedResponse = DataResponse.Success(mockedUser)

            whenever(updateAccount.invoke(mockedUser)).thenReturn(expectedResponse)

            changePasswordViewModel.model.observeForever(observer)

            changePasswordViewModel.updateAccount(mockedUser, mockedUser.password)

            verify(observer).onChanged(ChangePasswordViewModel.UiModel.ChangePasswordContent(true))
        }
    }

    @Test
    fun changePasswordServerError() {
        runBlocking {

            val expectedResponse = DataResponse.ServerError(mockedServerError)

            whenever(updateAccount.invoke(mockedUser)).thenReturn(expectedResponse)

            changePasswordViewModel.model.observeForever(observer)

            changePasswordViewModel.updateAccount(mockedUser, mockedUser.password)

            verify(observer).onChanged(ChangePasswordViewModel.UiModel.ServerError)
        }
    }

    @Test
    fun changePasswordNetworkError() {
        runBlocking {

            val expectedResponse = DataResponse.NetworkError

            whenever(updateAccount.invoke(mockedUser)).thenReturn(expectedResponse)

            changePasswordViewModel.model.observeForever(observer)

            changePasswordViewModel.updateAccount(mockedUser, mockedUser.password)

            verify(observer).onChanged(ChangePasswordViewModel.UiModel.NetworkError)
        }
    }


    @Test
    fun navigationToHome(){

        changePasswordViewModel.model.observeForever(observer)

        changePasswordViewModel.navigateToHome()

        verify(observer).onChanged(ChangePasswordViewModel.UiModel.NavigationToHome)
    }
}