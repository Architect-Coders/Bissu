package com.architeccoders.bissu.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.architeccoders.bissu.ui.common.ScopedViewModel
import com.architectcoders.domain.User
import kotlinx.coroutines.launch

class ProfileViewModel(private val user: User) : ScopedViewModel() {
    init {
        initScope()
    }
    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            if ( _model.value == null) doRefresh()
            return _model
        }

    sealed class UiModel() {
        class Content(val user: User) : UiModel()
        class Navigation(val user: User): UiModel()
    }

    fun doRefresh() {
        launch {
            _model.value = UiModel.Content(user)
        }
    }

    fun onProfileEditClicked(user: User) {
        _model.value = UiModel.Navigation(user)
    }

}
