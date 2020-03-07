package com.architectcoders.bissu.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.architectcoders.bissu.ui.common.ScopedViewModel
import com.architectcoders.domain.entities.User
import com.architectcoders.domain.usecases.GetAccount
import com.architectcoders.domain.usecases.UpdateAccount
import kotlinx.coroutines.launch

/**
 * Created by Anibal Cortez on 2020-03-05.
 */
class UpdateAccountViewModel(private val updateAccount : UpdateAccount, private val getUser : GetAccount) :  ScopedViewModel() {

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            return _model
        }

    init {
        initScope()
    }

    sealed class UiModel {
        object Loading : UiModel()
        class Content(val status: Boolean) : UiModel()
        class ContentEdit(val user: User?) : UiModel()
        object NavigationProfile : UiModel()
    }

    fun updateAccount(user: User, password : String){
        launch {
            _model.value = UiModel.Loading
            _model.value = UiModel.Content(updateAccount.invoke(user, password))
            _model.value = UiModel.NavigationProfile
        }
    }

    fun getCurrentUser() {
        launch {
            _model.value = UiModel.Loading
            _model.value = UiModel.ContentEdit(getUser.invoke())
        }
    }

}