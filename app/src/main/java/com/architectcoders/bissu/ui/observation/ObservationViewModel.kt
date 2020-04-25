package com.architectcoders.bissu.ui.observation

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.architectcoders.bissu.R
import com.architectcoders.bissu.ui.common.ScopedViewModel
import com.architectcoders.bissu.ui.common.validateInput
import com.architectcoders.bissu.ui.observation.ObservationViewModel.UiModel.*
import com.architectcoders.domain.entities.Book
import com.architectcoders.domain.entities.Observation
import com.architectcoders.domain.entities.User
import com.architectcoders.domain.usecases.CreateObservation
import com.architectcoders.domain.usecases.GetAccount
import com.architectcoders.domain.usecases.GetBook
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
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
        class CreateAccountError() : UiModel()
        class NavigateToHome() : UiModel()
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

    fun validatePages( context: Context, pages: TextInputEditText, pagesInputLayout: TextInputLayout): Boolean {
        return pagesInputLayout.validateInput( pages, context.resources.getString(R.string.create_observation_pages_error))
    }


    fun validateObservation(context: Context,observation: TextInputEditText, observationInputLayout: TextInputLayout): Boolean {
        return observationInputLayout.validateInput( observation, context.resources.getString(R.string.create_observation_observation_error))
    }

    private fun createObservation(user: User, book: Book, description: String, page: String) {
        launch {
            _model.value = Loading(true)
            val observation = Observation("", user, book, description, page)
            createObservation.invoke(observation).let {
                if (it)  _model.value = NavigateToHome()
                 else _model.value = CreateAccountError()
            }
            _model.value = Loading(false)
        }
    }

    fun onCreateClicked(book: Book, pages: String, description: String) {
        launch {
            getAccount.invoke().let { user ->
                user?.let { createObservation(it, book, description, pages) }
            }
        }
    }

}
