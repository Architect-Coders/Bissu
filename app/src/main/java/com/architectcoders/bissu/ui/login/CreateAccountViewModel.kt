package com.architectcoders.bissu.ui.login

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.architectcoders.bissu.R
import com.architectcoders.bissu.ui.common.ScopedViewModel
import com.architectcoders.bissu.ui.common.validateInput
import com.architectcoders.bissu.ui.common.validateInputPasword
import com.architectcoders.domain.usecases.CreateAccount
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.launch

/**
 * Created by Anibal Cortez on 2019-12-11.
 */
class CreateAccountViewModel(private val createAccount: CreateAccount) : ScopedViewModel() {

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            return _model
        }

    sealed class UiModel {
        object Loading : UiModel()
        class CreateAccountContent(val success: Boolean) : UiModel()
        object NavigationLogin : UiModel()
    }

    init {
        initScope()
    }

    fun createAccount(username: String, email: String,firstName: String, lastName: String, password: String , photoUrl : String?) {
        launch {
            _model.value = UiModel.Loading
            _model.value = UiModel.CreateAccountContent(createAccount.invoke(username, email,firstName,lastName,password,photoUrl))
            _model.value = UiModel.NavigationLogin
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