package com.architectcoders.bissu.ui.profile

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.architectcoders.bissu.R
import com.architectcoders.bissu.ui.common.ScopedViewModel
import com.architectcoders.bissu.ui.common.validateInput
import com.architectcoders.bissu.ui.common.validateInputPasword
import com.architectcoders.domain.entities.User
import com.architectcoders.domain.usecases.GetAccount
import com.architectcoders.domain.usecases.UpdateAccount
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.launch

/**
 * Created by Anibal Cortez on 2020-03-05.
 */
class ChangePasswordViewModel( private val updateAccount: UpdateAccount,private val getUser: GetAccount) : ScopedViewModel() {

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            return _model
        }

    init {
        initScope()
    }

    sealed class UiModel {
        object Loading : UiModel()
        class Content(val status: Boolean) : UiModel()
        class ContentEdit(val user: User?) : UiModel()
        object NavigationToHome : UiModel()
    }

    private fun getUser(currentUser : User, password : String) : User{
        return  User( id = currentUser.id,username = currentUser.username, email = currentUser.email,
            firstName = currentUser.firstName,lastName = currentUser.lastName, password = password, photoUrl = currentUser.photoUrl,categories = currentUser.categories )
    }

    fun navigateToProfile(){
        _model.value = UiModel.NavigationToHome
    }

    fun updateAccount(currentUser : User, password : String ) {
        launch {
            _model.value = UiModel.Loading
            _model.value = UiModel.Content(updateAccount.invoke(getUser(currentUser,password)) )
        }
    }

    fun getCurrentUser() {
        launch {
            _model.value = UiModel.Loading
            _model.value = UiModel.ContentEdit(getUser.invoke())
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