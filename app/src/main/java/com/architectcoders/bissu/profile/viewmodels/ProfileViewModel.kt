package com.architectcoders.bissu.profile.viewmodels

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.architectcoders.bissu.common.ScopedViewModel
import com.architectcoders.domain.usecases.GetSessionUser
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class ProfileViewModel(
    uiDispatcher: CoroutineDispatcher
)
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
        object CloseSession : UiModel()
        object UpdateAccountNavigation: UiModel()
        object ChangePasswordNavigation: UiModel()
    }

    fun closeSession(activity : Activity){
        launch {
            com.architectcoders.bissu.common.closeSession(activity)
        }
    }

    fun updateAccountClicked(){
        _model.value = UiModel.UpdateAccountNavigation
    }
    fun changePasswordClicked(){
        _model.value = UiModel.ChangePasswordNavigation
    }
    fun logOutClicked(){
        _model.value = UiModel.CloseSession
    }
}
