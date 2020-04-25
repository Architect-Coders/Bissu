package com.architectcoders.bissu.ui.book

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.architectcoders.bissu.ui.common.ScopedViewModel
import com.architectcoders.domain.entities.Book
import com.architectcoders.domain.entities.Observation
import com.architectcoders.domain.usecases.GetBook
import com.architectcoders.domain.usecases.GetObservations
import kotlinx.coroutines.launch

class BookDetailViewModel(private val getBook: GetBook, private val getObservations: GetObservations) : ScopedViewModel() {

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            return _model
        }

    sealed class UiModel {
        class Loading(val value: Boolean) : UiModel()
        class Content(val book: Book?) : UiModel()
        class LoadingObservations(val value: Boolean) : UiModel()
        class Observations(val observations: ArrayList<Observation>) : UiModel()
    }

    init {
        initScope()
    }

    fun getBook(bookId: String) {
        launch {
          //  _model.value = UiModel.Loading(true)
            _model.value = UiModel.Content(getBook.invoke(bookId))
          //  _model.value = UiModel.Loading(false)
        }
    }

    fun fetchObservations(bookId: String){
        launch {
            _model.value = UiModel.LoadingObservations(true)
            _model.value = UiModel.Observations(getObservations.invoke(bookId))
            _model.value = UiModel.LoadingObservations(false)
        }
    }
}
