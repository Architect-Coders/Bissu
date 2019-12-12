package com.architeccoders.bissu.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.architeccoders.bissu.ui.common.ScopedViewModel
import com.architectcoders.domain.User
import com.architectcoders.usecases.DoLogin
import kotlinx.coroutines.launch

/**
 * Created by Anibal Cortez on 2019-12-11.
 */
class LoginViewModel(private val doLogin : DoLogin) : ScopedViewModel() {

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            return _model
        }

    sealed class UiModel {
        object Loading : UiModel()
        class Content(val user: User?) : UiModel()

    }

    init {
        initScope()
    }

    fun doLogin(username : String, password : String){
        launch {
            _model.value = UiModel.Loading
            _model.value = UiModel.Content( doLogin.invoke(username, password) )
        }
    }
}