package com.architectcoders.bissu.ui.profile

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.architectcoders.bissu.R
import com.architectcoders.bissu.ui.common.ScopedViewModel
import com.architectcoders.bissu.ui.common.validateInput
import com.architectcoders.domain.entities.User
import com.architectcoders.domain.usecases.GetAccount
import com.architectcoders.domain.usecases.UpdateAccount
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.launch

/**
 * Created by Anibal Cortez on 2020-03-05.
 */
class UpdateAccountViewModel(
    private val updateAccount: UpdateAccount,
    private val getUser: GetAccount
) : ScopedViewModel() {

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

    fun navigateToProfile(){
        _model.value = UiModel.NavigationToHome
    }

    fun updateAccount(currentUser : User, username: String, email: String,firstName: String, lastName: String,photoUrl: String? = null ) {
        launch {
            _model.value = UiModel.Loading
            _model.value = UiModel.Content(
                updateAccount.invoke(
                    User(
                        id = currentUser.id,
                        username = username,
                        email = email,
                        firstName = firstName,
                        lastName = lastName,
                        password = currentUser.password,
                        photoUrl = photoUrl,
                        categories = currentUser.categories
                    )
                )
            )
        }
    }

    fun getCurrentUser() {
        launch {
            _model.value = UiModel.Loading
            _model.value = UiModel.ContentEdit(getUser.invoke())
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