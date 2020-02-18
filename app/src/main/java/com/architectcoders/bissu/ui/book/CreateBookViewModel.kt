package com.architectcoders.bissu.ui.book

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.architectcoders.bissu.ui.common.ScopedViewModel
import com.architectcoders.domain.Category
import com.architectcoders.usecases.GetCategories
import kotlinx.coroutines.launch

/**
 * Created by Anibal Cortez on 2020-02-18.
 */
class CreateBookViewModel(private val getCategories : GetCategories) :   ScopedViewModel() {

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            return _model
        }

    sealed class UiModel {
        object Loading : UiModel()
        class Content(val list : List<Category>) : UiModel()
    }

    init {
        initScope()
    }

    fun getCategories(){
        launch {
            _model.value = UiModel.Loading
            _model.value = UiModel.Content(getCategories.invoke() )
        }
    }

}