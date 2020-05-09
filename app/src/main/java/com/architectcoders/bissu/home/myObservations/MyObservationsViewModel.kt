package com.architectcoders.bissu.home.myObservations

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.architectcoders.bissu.common.ScopedViewModel
import com.architectcoders.domain.entities.DataResponse
import com.architectcoders.domain.entities.Observation
import com.architectcoders.domain.entities.User
import com.architectcoders.domain.usecases.GetObservationsByUser
import com.architectcoders.domain.usecases.GetSessionUser
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class MyObservationsViewModel(
    private val getAccount: GetSessionUser,
    private val getObservationsByUser: GetObservationsByUser,
    uiDispatcher: CoroutineDispatcher
) : ScopedViewModel(uiDispatcher) {

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            return _model
        }

    sealed class UiModel {
        class Loading(val value: Boolean) : UiModel()
        class ObservationContent(val observations: List<Observation>) : UiModel()
        object SessionError : UiModel()
        class UserContent(val user: User) : UiModel()
    }

    init {
        initScope()
    }

    fun getSessionUser() {
        launch {
            _model.value = UiModel.Loading(true)
            val response = getAccount.invoke()
            when (response) {
                is DataResponse.Success -> UiModel.UserContent(response.data)
                is DataResponse.SessionError -> UiModel.SessionError
            }
        }
    }

    fun getObservationsByUser(userId: String, forceRefresh: Boolean = false) {
        launch {
            _model.value = UiModel.Loading(true)
            val response = getObservationsByUser.invoke(userId, forceRefresh)
            when (response) {
                is DataResponse.Success -> _model.value = UiModel.ObservationContent(response.data)
                is DataResponse.ServerError -> {
                }
                is DataResponse.NetworkError -> {
                }
            }
            _model.value = UiModel.Loading(false)
        }
    }
}
