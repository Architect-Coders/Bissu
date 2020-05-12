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
        data class UserContent(val user: User) : UiModel()
        data class Loading(val value: Boolean) : UiModel()
        data class ObservationContent(val observations: List<Observation>) : UiModel()
        object ServerError : UiModel()
        object NetworkError : UiModel()
        object SessionError : UiModel()
    }

    init {
        initScope()
    }

    fun getSessionUser() {
        launch {
            _model.value = UiModel.Loading(true)

            val response = getAccount.invoke()

            when (response) {
                is DataResponse.Success -> _model.value = UiModel.UserContent(response.data)
                is DataResponse.SessionError -> _model.value =UiModel.SessionError
            }
            _model.value = UiModel.Loading(false)
        }
    }

    fun getObservationsByUser(userId: String, forceRefresh: Boolean = false) {
        launch {
            _model.value = UiModel.Loading(true)
            val response = getObservationsByUser.invoke(userId, forceRefresh)
            when (response) {
                is DataResponse.Success -> _model.value = UiModel.ObservationContent(response.data)
                is DataResponse.ServerError -> _model.value = UiModel.ServerError
                is DataResponse.NetworkError -> _model.value = UiModel.NetworkError
            }
            _model.value = UiModel.Loading(false)
        }
    }
}
