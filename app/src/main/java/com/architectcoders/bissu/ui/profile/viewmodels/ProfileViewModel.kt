package com.architectcoders.bissu.ui.profile.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.architectcoders.bissu.ui.common.ScopedViewModel
import com.architectcoders.domain.usecases.GetAccount

class ProfileViewModel(private val getAccount: GetAccount) : ScopedViewModel() {
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

    fun updateAccountClicked(){
        _model.value =
            UiModel.UpdateAccountNavigation
    }
    fun changePasswordClicked(){
        _model.value =
            UiModel.ChangePasswordNavigation
    }
    fun logOutClicked(){
        _model.value =
            UiModel.CloseSession
    }
}
