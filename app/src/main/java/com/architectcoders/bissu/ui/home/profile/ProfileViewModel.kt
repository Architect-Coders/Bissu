package com.architectcoders.bissu.ui.home.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.architectcoders.bissu.ui.common.ScopedViewModel
import com.architectcoders.domain.entities.User
import com.architectcoders.domain.usecases.GetAccount
import kotlinx.coroutines.launch

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
        class Content(val user: User?) : UiModel()
        class Navigation(): UiModel()
    }

    fun getAccount() {
        launch {
            _model.value = UiModel.Content( getAccount.invoke())
        }
    }

    fun onProfileEditClicked() {
        launch {
            _model.value = UiModel.Navigation()
        }
    }

}
