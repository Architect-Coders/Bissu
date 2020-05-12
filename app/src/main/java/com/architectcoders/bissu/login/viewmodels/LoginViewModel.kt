package com.architectcoders.bissu.login.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.architectcoders.bissu.R
import com.architectcoders.bissu.common.ScopedViewModel
import com.architectcoders.bissu.common.validateInput
import com.architectcoders.domain.entities.DataResponse
import com.architectcoders.domain.usecases.DoLogin
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

/**
 * Created by Anibal Cortez on 2019-12-11.
 */
class LoginViewModel(private val doLogin: DoLogin, uiDispatcher: CoroutineDispatcher)
    : ScopedViewModel(uiDispatcher) {

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            return _model
        }

    sealed class UiModel {
        data class LoginContent(val success: Boolean) : UiModel()
        object Loading : UiModel()
        object Navigation : UiModel()
        object ServerError : UiModel()
        object NetworkError : UiModel()
    }

    init {
        initScope()
    }
    fun doLogin(username: String, password: String) {
        launch {
            _model.value = UiModel.Loading
            val response = doLogin.invoke(username, password)
            when(response){
                is DataResponse.Success ->  _model.value = UiModel.LoginContent(true)
                is DataResponse.ServerError ->  _model.value = UiModel.ServerError
                is DataResponse.NetworkError -> _model.value = UiModel.NetworkError
            }
        }
    }

    fun doLogin(context : Context, username: TextInputEditText, password: TextInputEditText, usernameTextInput: TextInputLayout,passwordTextInput: TextInputLayout) {
        if (usernameTextInput.validateInput(username, context.resources.getString(R.string.login_username_error) ) && passwordTextInput.validateInput(password, context.resources.getString(R.string.login_password_error)) )
            doLogin(username.text.toString(), password.text.toString())
    }

    fun onCreateAccountClicked() {
        _model.value = UiModel.Navigation
    }
}