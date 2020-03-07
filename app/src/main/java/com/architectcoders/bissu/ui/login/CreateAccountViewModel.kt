package com.architectcoders.bissu.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.architectcoders.bissu.ui.common.ScopedViewModel
import com.architectcoders.domain.entities.User
import com.architectcoders.domain.usecases.CreateAccount
import kotlinx.coroutines.launch

/**
 * Created by Anibal Cortez on 2019-12-11.
 */
class CreateAccountViewModel(private val createAccount : CreateAccount) : ScopedViewModel() {

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            return _model
        }

    sealed class UiModel {
        object Loading : UiModel()
        class Content(val status: Boolean) : UiModel()
        object NavigationLogin : UiModel()
    }

    init {
        initScope()
    }

    fun createAccount(user: User, password : String){
        launch {
            _model.value = UiModel.Loading
            _model.value = UiModel.Content(createAccount.invoke(user, password))
            _model.value = UiModel.NavigationLogin
        }
    }
    fun loginNavigation(){
        _model.value = UiModel.NavigationLogin
    }

}