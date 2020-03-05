package com.architectcoders.bissu.ui.home.myObservations

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.architectcoders.bissu.ui.common.ScopedViewModel
import com.architectcoders.domain.entities.Observation
import com.architectcoders.domain.usecases.GetAccount
import com.architectcoders.domain.usecases.GetOwnerObservations
import kotlinx.coroutines.launch

class MyObservationsViewModel(
    private val getAccount: GetAccount,
    private val getOwnerObservations: GetOwnerObservations
) : ScopedViewModel() {

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

    fun getObservationsByOwner() {
        launch {
            _model.value = UiModel.Loading(true)

            getAccount.invoke()?.id?.let {
                _model.value = UiModel.Content(getOwnerObservations.invoke(it))
            }

            _model.value = UiModel.Loading(false)
        }
    }
}
