package com.architectcoders.bissu.ui.login.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.architectcoders.bissu.R
import com.architectcoders.bissu.ui.common.ScopedViewModel
import com.architectcoders.bissu.ui.common.validateInput
import com.architectcoders.domain.usecases.DoLogin
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.launch

/**
 * Created by Anibal Cortez on 2019-12-11.
 */
class LoginViewModel(private val doLogin: DoLogin) : ScopedViewModel() {

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            return _model
        }

    sealed class UiModel {
        object Loading : UiModel()
        object Navigation : UiModel()
        class LoginContent(val success: Boolean) : UiModel()
    }

    init {
        initScope()
    }
    private  fun doLogin(username: String, password: String) {
        launch {
            _model.value =
                UiModel.Loading
            _model.value =
                UiModel.LoginContent(
                    doLogin.invoke(username, password)
                )
        }
    }

    fun doLogin(context : Context, username: TextInputEditText, password: TextInputEditText, usernameTextInput: TextInputLayout,passwordTextInput: TextInputLayout) {
        if (usernameTextInput.validateInput(username, context.resources.getString(R.string.login_username_error) ) && passwordTextInput.validateInput(password, context.resources.getString(R.string.login_password_error)) )
            doLogin(username.text.toString(), password.text.toString())
    }

    fun onCreateAccountClicked() {
        _model.value =
            UiModel.Navigation
    }
}