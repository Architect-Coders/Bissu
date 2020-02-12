package com.architectcoders.bissu.ui.observation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.architectcoders.bissu.ui.book.BookDetailViewModel
import com.architectcoders.bissu.ui.common.ScopedViewModel
import com.architectcoders.domain.Book
import com.architectcoders.domain.Observation
import com.architectcoders.usecases.CreateObservation
import com.architectcoders.usecases.GetAccount
import com.architectcoders.usecases.GetBook
import kotlinx.coroutines.launch

class ObservationViewModel(
    private val getAccount: GetAccount,
    private val getBook: GetBook,
    private val createObservation: CreateObservation
) : ScopedViewModel() {

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            return _model
        }

    sealed class UiModel {
        class Loading(val value: Boolean) : UiModel()
        class ContentBook(val book: Book?) : UiModel()
        class Content(val value: Boolean) : UiModel()
    }

    init {
        initScope()
    }

    fun getBook(bookId: String) {
        launch {
            _model.value = UiModel.Loading(true)
            _model.value = UiModel.ContentBook(getBook.invoke(bookId))
            _model.value = UiModel.Loading(false)
        }
    }

    fun createObservation(observation: Observation) {
        launch {
            _model.value = UiModel.Loading(true)

            val user = getAccount.invoke()

            if (user == null) {
                _model.value = UiModel.Content(false)
            } else {
                createObservation.invoke(observation).let {
                    _model.value = UiModel.Content(it)
                }
            }

            _model.value = UiModel.Loading(false)
        }
    }
}
