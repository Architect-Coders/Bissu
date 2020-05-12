package com.architectcoders.bissu.profile.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.Dispatchers
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ProfileViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var observer: Observer<ProfileViewModel.UiModel>

    private lateinit var profileViewModel: ProfileViewModel

    @Before
    fun init() {
        profileViewModel = ProfileViewModel(Dispatchers.Unconfined)
    }

    @Test
    fun closeSessionClicked() {

        profileViewModel.model.observeForever(observer)

        profileViewModel.logOutClicked()

        verify(observer).onChanged(ProfileViewModel.UiModel.CloseSession)
    }

    @Test
    fun updateAccountClicked() {

        profileViewModel.model.observeForever(observer)

        profileViewModel.updateAccountClicked()

        verify(observer).onChanged(ProfileViewModel.UiModel.UpdateAccountNavigation)
    }

    @Test
    fun changePasswordClicked() {

        profileViewModel.model.observeForever(observer)

        profileViewModel.changePasswordClicked()

        verify(observer).onChanged(ProfileViewModel.UiModel.ChangePasswordNavigation)
    }
}