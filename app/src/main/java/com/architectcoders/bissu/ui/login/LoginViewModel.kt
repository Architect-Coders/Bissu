package com.architectcoders.bissu.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.architectcoders.bissu.ui.common.ScopedViewModel
import com.architectcoders.domain.usecases.DoLogin
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
        object Navigation : UiModel()
        class Content(val success : Boolean) : UiModel()
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
    fun onCreateAccountClicked(){
        _model.value = UiModel.Navigation
    }

}