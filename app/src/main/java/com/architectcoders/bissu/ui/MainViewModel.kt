package com.architectcoders.bissu.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.architectcoders.bissu.ui.common.ScopedViewModel
import com.architectcoders.domain.usecases.GetAccount
import kotlinx.coroutines.launch

class MainViewModel(private val getAccount: GetAccount) : ScopedViewModel() {

    init {
        initScope()
    }

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            return _model
        }


    sealed class UiModel() {
        object NavigateToHome : UiModel()
        object NavigateToLogin : UiModel()
    }

    fun getUser(){
        launch {
            if (getAccount.invoke() == null) _model.value = UiModel.NavigateToLogin
            else _model.value =  UiModel.NavigateToHome
        }
    }
}