package com.architectcoders.bissu.login.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.architectcoders.bissu.R
import com.architectcoders.bissu.common.ScopedViewModel
import com.architectcoders.bissu.common.validateInput
import com.architectcoders.bissu.common.validateInputPasword
import com.architectcoders.domain.entities.DataResponse
import com.architectcoders.domain.entities.User
import com.architectcoders.domain.usecases.CreateAccount
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

/**
 * Created by Anibal Cortez on 2019-12-11.
 */
class CreateAccountViewModel(private val createAccount: CreateAccount, uiDispatcher: CoroutineDispatcher)
    : ScopedViewModel(uiDispatcher) {

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            return _model
        }

    sealed class UiModel {
        data class Loading(val value : Boolean) : UiModel()
        data class CreateAccountContent(val user : User) : UiModel()
        object NavigationLogin : UiModel()
        object ServerError : UiModel()
        object NetworkError: UiModel()
    }

    init {
        initScope()
    }

    fun createAccount(username: String, email: String,firstName: String, lastName: String, password: String , photoUrl : String?) {
        launch {
            _model.value = UiModel.Loading(true)

            val response =  createAccount.invoke(username, email, firstName, lastName, password, photoUrl)
            when(response){
                is DataResponse.Success ->  _model.value = UiModel.CreateAccountContent(response.data)
                is DataResponse.ServerError -> _model.value = UiModel.ServerError
                is DataResponse.NetworkError -> _model.value = UiModel.NetworkError
            }
            _model.value = UiModel.Loading(false)
        }
    }

    fun loginNavigation() {
        _model.value = UiModel.NavigationLogin
    }

    fun validateUsername(
        context: Context,
        username: TextInputEditText,
        usernameInputLayout: TextInputLayout
    ): Boolean {
        return usernameInputLayout.validateInput(
            username,
            context.resources.getString(R.string.create_account_username_error)
        )
    }

    fun validateEmail(
        context: Context,
        email: TextInputEditText,
        emailInputLayout: TextInputLayout
    ): Boolean {
        return emailInputLayout.validateInput(
            email,
            context.resources.getString(R.string.create_account_email_error)
        )
    }

    fun validateFirstName(
        context: Context,
        firstName: TextInputEditText,
        firstNameInputLayout: TextInputLayout
    ): Boolean {
        return firstNameInputLayout.validateInput(
            firstName,
            context.resources.getString(R.string.create_account_name_error)
        )
    }

    fun validateLastName(
        context: Context,
        lastName: TextInputEditText,
        lastNameInputLayout: TextInputLayout
    ): Boolean {
        return lastNameInputLayout.validateInput(
            lastName,
            context.resources.getString(R.string.create_account_last_name_error)
        )
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