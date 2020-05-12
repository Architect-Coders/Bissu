package com.architectcoders.bissu.profile.viewmodels

import android.app.Activity
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.architectcoders.bissu.R
import com.architectcoders.bissu.common.ScopedViewModel
import com.architectcoders.bissu.common.validateInput
import com.architectcoders.bissu.common.validateInputPasword
import com.architectcoders.domain.entities.DataResponse
import com.architectcoders.domain.entities.User
import com.architectcoders.domain.usecases.GetSessionUser
import com.architectcoders.domain.usecases.UpdateAccount
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

/**
 * Created by Anibal Cortez on 2020-03-05.
 */
class ChangePasswordViewModel(
    private val updateAccount: UpdateAccount,
    private val getSessionUser: GetSessionUser,
    uiDispatcher: CoroutineDispatcher
) : ScopedViewModel(uiDispatcher) {

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            return _model
        }

    init {
        initScope()
    }

    sealed class UiModel {
        data class ChangePasswordContent(var user: User) : UiModel()
        data class UserSessionContent(val user: User) : UiModel()
        object Loading : UiModel()
        object NavigationToHome : UiModel()
        object ServerError : UiModel()
        object NetworkError : UiModel()
        object SessionError : UiModel()

    }


    fun navigateToHome() {
        _model.value = UiModel.NavigationToHome
    }


    fun closeSession(activity: Activity) {
        launch {
            com.architectcoders.bissu.common.closeSession(activity)
        }
    }

    fun updateAccount(updatedUser: User) {
        launch {
            _model.value = UiModel.Loading

            val response = updateAccount.invoke(updatedUser)
            when (response) {
                is DataResponse.Success -> _model.value = UiModel.ChangePasswordContent(response.data)
                is DataResponse.ServerError -> _model.value = UiModel.ServerError
                is DataResponse.NetworkError -> _model.value = UiModel.NetworkError
            }
            _model.value = UiModel.Loading
        }
    }

    fun getCurrentUser() {
        launch {
            _model.value = UiModel.Loading
            val response = getSessionUser.invoke()
            when (response) {
                is DataResponse.Success -> _model.value = UiModel.UserSessionContent(response.data)
                is DataResponse.SessionError -> _model.value = UiModel.SessionError
            }

        }
    }

    fun validatePassword(
        context: Context,
        password: TextInputEditText,
        passwordInputLayout: TextInputLayout
    ): Boolean {
        return passwordInputLayout.validateInput(
            password,
            context.resources.getString(R.string.create_account_password_error)
        )
    }

    fun validateRepeatPassword(
        context: Context,
        repeatPassword: TextInputEditText,
        repeatPasswordInputLayout: TextInputLayout
    ): Boolean {
        return repeatPasswordInputLayout.validateInput(
            repeatPassword,
            context.resources.getString(R.string.create_account_repeat_password_error)
        )
    }

    fun validateInputPassword(
        context: Context,
        password: TextInputEditText,
        repeatPassword: TextInputEditText,
        repeatPasswordInputLayout: TextInputLayout
    ): Boolean {
        return repeatPasswordInputLayout.validateInputPasword(
            password,
            repeatPassword,
            context.resources.getString(R.string.create_account_validate_password_error)
        )
    }


}