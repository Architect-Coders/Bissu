package com.architectcoders.bissu.ui.home.myObservations

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.architectcoders.bissu.AndroidApplication
import com.architectcoders.bissu.ui.common.ScopedViewModel
import com.architectcoders.bissu.ui.common.isAvailableNetwork
import com.architectcoders.domain.entities.DataResponse
import com.architectcoders.domain.entities.Observation
import com.architectcoders.domain.usecases.GetAccount
import com.architectcoders.domain.usecases.GetObservationsByUser
import kotlinx.coroutines.launch

class MyObservationsViewModel(private val getAccount: GetAccount, private val getObservationsByUser: GetObservationsByUser) : ScopedViewModel() {

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            return _model
        }

    sealed class UiModel {
        class Loading(val value: Boolean) : UiModel()
        class Content(val observations: List<Observation>) : UiModel()
    }

    init {
        initScope()
    }

    fun getObservationsByUser() {
        launch {

            _model.value = UiModel.Loading(true)


                getAccount.invoke()?.id?.let {

                    val result = getObservationsByUser.invoke(it)
                    when(result){
                        is DataResponse.Success ->  _model.value = UiModel.Content(result.data)
                        is DataResponse.ServerError -> { }
                        is DataResponse.NetworkError ->{}
                    }

                }

            _model.value = UiModel.Loading(false)
        }
    }
}
