package com.architeccoders.bissu.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.architeccoders.bissu.ui.common.ScopedViewModel
import com.architectcoders.domain.User
import com.architectcoders.usecases.CreateAccount
import kotlinx.coroutines.launch

/**
 * Created by Anibal Cortez on 2019-12-11.
 */
class LoginCreateAccountViewModel(private val createAccount : CreateAccount) : ScopedViewModel() {

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            return _model
        }

    sealed class UiModel {
        object Loading : UiModel()
        class Content(val status: Boolean) : UiModel()
    }

    init {
        initScope()
    }

    fun createAccount(user: User, password : String){
        launch {
            _model.value = UiModel.Loading
            _model.value = UiModel.Content(createAccount.invoke(user, password))
        }
    }
}