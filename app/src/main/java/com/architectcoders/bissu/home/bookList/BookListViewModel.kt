package com.architectcoders.bissu.home.bookList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.architectcoders.bissu.common.ScopedViewModel
import com.architectcoders.domain.entities.Book
import com.architectcoders.domain.entities.DataResponse
import com.architectcoders.domain.usecases.GetBooks
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class BookListViewModel(private val getBooks: GetBooks,uiDispatcher: CoroutineDispatcher)
    : ScopedViewModel(uiDispatcher) {

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
            val response = getBooks.invoke()
            when(response){
                is DataResponse.Success ->  _model.value = UiModel.Content(response.data)
                is DataResponse.ServerError -> {}
                is DataResponse.NetworkError -> {}
            }

            _model.value = UiModel.Loading(false)
        }
    }

    fun refreshBooks(){
        launch {
            _model.value = UiModel.Refresh(true)
            val response = getBooks.invoke(true)
            when(response){
                is DataResponse.Success ->   _model.value = UiModel.Content(response.data)
                is DataResponse.ServerError -> {}
                is DataResponse.NetworkError -> {}
            }
            _model.value = UiModel.Refresh(false)
        }

    }

    fun addBookClicked(){
        _model.value = UiModel.Navigation();
    }
}
