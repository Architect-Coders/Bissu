package com.architectcoders.bissu.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.architectcoders.bissu.common.ScopedViewModel
import com.architectcoders.domain.entities.DataResponse
import com.architectcoders.domain.usecases.GetSessionUser
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class MainViewModel(private val getSessionUser: GetSessionUser, uiDispatcher: CoroutineDispatcher)
    : ScopedViewModel(uiDispatcher) {

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
            val response = getSessionUser.invoke()
            when(response){
                is DataResponse.Success -> _model.value = UiModel.NavigateToHome
                is DataResponse.SessionError ->  _model.value = UiModel.NavigateToLogin
            }
        }
    }
}