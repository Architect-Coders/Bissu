package com.architectcoders.bissu.book.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.architectcoders.bissu.common.ScopedViewModel
import com.architectcoders.domain.entities.Book
import com.architectcoders.domain.entities.DataResponse
import com.architectcoders.domain.usecases.GetBook
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class BookDetailViewModel(
    private val getBook: GetBook,
    uiDispacher : CoroutineDispatcher
) : ScopedViewModel(uiDispacher) {

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            return _model
        }

    sealed class UiModel {
        data class Loading(val value: Boolean) : UiModel()
        data class BookContent(val book: Book) : UiModel()
        object ServerError : UiModel()
    }

    init {
        initScope()
    }

    fun getBook(bookId: String) {
        launch {
            _model.value = UiModel.Loading(true)
            val response = getBook.invoke(bookId)
            when(response){
                is DataResponse.Success -> _model.value = UiModel.BookContent( response.data)
                is DataResponse.ServerError -> _model.value = UiModel.ServerError
            }
            _model.value =UiModel.Loading(false)
        }
    }

}
