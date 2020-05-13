package com.architectcoders.bissu.profile.viewmodels

import android.app.Activity
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.architectcoders.bissu.R
import com.architectcoders.bissu.common.ScopedViewModel
import com.architectcoders.bissu.common.validateInput
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
class UpdateAccountViewModel(
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
        data class UpdateAccountContent(val user: User) : UiModel()
        data class UserSessionContent(val user: User) : UiModel()
        data class Loading(val status : Boolean) : UiModel()
        object ServerError :  UiModel()
        object SessionError :  UiModel()
        object NetworkError :  UiModel()
        object NavigationToHome : UiModel()
    }

    fun navigateToProfile(){
        _model.value =
            UiModel.NavigationToHome
    }

    fun closeSession(activity: Activity){
        launch {
            com.architectcoders.bissu.common.closeSession(activity)
        }
    }

    fun updateAccount(userUpdated : User) {
        launch {
            _model.value = UiModel.Loading(true)
            val response =  updateAccount.invoke(userUpdated)
            when(response){
                is DataResponse.Success ->  _model.value = UiModel.UpdateAccountContent(response.data)
                is DataResponse.ServerError ->    _model.value = UiModel.ServerError
                is DataResponse.NetworkError ->    _model.value = UiModel.NetworkError
            }
            _model.value = UiModel.Loading(false)
        }
    }

    fun getCurrentUser() {
        launch {
            _model.value = UiModel.Loading(true)
            val response =  getSessionUser.invoke()
            when(response){
                is DataResponse.Success -> _model.value = UiModel.UserSessionContent(response.data)
                is DataResponse.SessionError -> _model.value = UiModel.SessionError
            }
            _model.value = UiModel.Loading(false)
        }
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

}