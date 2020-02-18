package com.architectcoders.bissu.ui.home.bookList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.architectcoders.bissu.ui.common.ScopedViewModel
import com.architectcoders.domain.Book
import com.architectcoders.usecases.GetBooks
import kotlinx.coroutines.launch

class BookListViewModel(private val getBooks: GetBooks) : ScopedViewModel() {

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            return _model
        }

    sealed class UiModel {
        class Navigation : UiModel();
        class Refresh(val value: Boolean): UiModel()
        class Loading(val value: Boolean) : UiModel()
        class Content(val books: List<Book>) : UiModel()
    }

    init {
        initScope()
    }

    fun getBooks() {
        launch {
            _model.value = UiModel.Loading(true)
            _model.value = UiModel.Content(getBooks.invoke())
            _model.value = UiModel.Loading(false)
        }
    }

    fun refreshBooks(){
        launch {
            _model.value = UiModel.Refresh(true)
            _model.value = UiModel.Content(getBooks.invoke(true))
            _model.value = UiModel.Refresh(false)
        }

    }

    fun addBookClicked(){
        _model.value = UiModel.Navigation();
    }
}
