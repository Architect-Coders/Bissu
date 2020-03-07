package com.architectcoders.bissu.ui.observation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.architectcoders.bissu.R
import com.architectcoders.bissu.ui.common.ScopedViewModel
import com.architectcoders.bissu.ui.observation.ObservationViewModel.UiModel.*
import com.architectcoders.domain.entities.Book
import com.architectcoders.domain.entities.Observation
import com.architectcoders.domain.entities.User
import com.architectcoders.domain.usecases.CreateObservation
import com.architectcoders.domain.usecases.GetAccount
import com.architectcoders.domain.usecases.GetBook
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
        class ShowToast(val value: Int) : UiModel()
        class GoBack() : UiModel()
    }

    init {
        initScope()
    }

    fun getBook(bookId: String) {
        launch {
            _model.value = Loading(true)
            _model.value = ContentBook(getBook.invoke(bookId))
            _model.value = Loading(false)
        }
    }

    private fun createObservation(user: User, book: Book, description: String, page: String) {
        launch {
            _model.value = Loading(true)

            val observation =
                Observation("", user, book, description, page)

            createObservation.invoke(observation).let {
                if(it){
                    _model.value = ShowToast(R.string.observationfragment_observationcreated)
                    _model.value = GoBack()
                }else{
                    _model.value = ShowToast(R.string.observationfragment_cantcreateobservation)
                }
            }

            _model.value = Loading(false)
        }
    }

    fun onCreateClicked(
        book: Book?,
        selectedItemPosition: Int,
        page: String?,
        description: String?
    ) {
        launch {
            getAccount.invoke().let { user ->
                when {
                    selectedItemPosition == 0 || page == null -> {
                        _model.value = ShowToast(R.string.observationfragment_selectpage)
                    }
                    description.isNullOrEmpty() -> {
                        _model.value =
                            ShowToast(R.string.observationfragment_addobservation)
                    }
                    book == null -> {
                        _model.value = ShowToast(R.string.observationfragment_processissue)
                    }
                    user == null -> {
                        _model.value = ShowToast(R.string.observationfragment_processissue)
                    }
                    else -> {
                        createObservation(user, book, description, page)
                    }
                }
            }
        }
    }
}
