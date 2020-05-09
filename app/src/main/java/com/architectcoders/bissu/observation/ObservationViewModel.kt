package com.architectcoders.bissu.observation

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.architectcoders.bissu.R
import com.architectcoders.bissu.common.ScopedViewModel
import com.architectcoders.bissu.common.validateInput
import com.architectcoders.domain.entities.Book
import com.architectcoders.domain.entities.DataResponse
import com.architectcoders.domain.entities.Observation
import com.architectcoders.domain.entities.User
import com.architectcoders.domain.usecases.CreateObservation
import com.architectcoders.domain.usecases.GetBook
import com.architectcoders.domain.usecases.GetSessionUser
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class ObservationViewModel(
    private val getSessionUser: GetSessionUser,
    private val getBook: GetBook,
    private val createObservation: CreateObservation,
    uiDispatcher: CoroutineDispatcher
) : ScopedViewModel(uiDispatcher) {

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            return _model
        }

    sealed class UiModel {
        class Loading(val value: Boolean) : UiModel()
        class BookContent(val book: Book) : UiModel()
        class UserContent(val user: User) : UiModel()
        object NavigateToHome : UiModel()
        object ServerError : UiModel()
        object NetworkError : UiModel()
        object SessionError : UiModel()

    }

    init {
        initScope()
    }

    fun getBook(bookId: String) {
        launch {
            _model.value = UiModel.Loading(true)
            val response = getBook.invoke(bookId)
            when(response){
                is DataResponse.Success ->  _model.value = UiModel.BookContent(response.data)
            }
            _model.value = UiModel.Loading(false)
        }
    }

    fun validatePages( context: Context, pages: TextInputEditText, pagesInputLayout: TextInputLayout): Boolean {
        return pagesInputLayout.validateInput( pages, context.resources.getString(R.string.create_observation_pages_error))
    }


    fun validateObservation(context: Context,observation: TextInputEditText, observationInputLayout: TextInputLayout): Boolean {
        return observationInputLayout.validateInput( observation, context.resources.getString(R.string.create_observation_observation_error))
    }

     fun createObservation(user: User, book: Book, description: String, page: String) {
        launch {

            _model.value = UiModel.Loading(true)

            val observation = Observation("", user.id, book, description, page)

            val result = createObservation.invoke(observation)

            when(result){
                is DataResponse.Success -> UiModel.NavigateToHome
                is DataResponse.NetworkError ->  UiModel.NetworkError
                is DataResponse.ServerError -> UiModel.ServerError
            }
            _model.value = UiModel.Loading(false)
        }
    }

    fun onGetUserClicked() {
        launch {
            _model.value = UiModel.Loading(true)
            val response =  getSessionUser.invoke()
            when(response){
                is DataResponse.Success -> _model.value = UiModel.UserContent(response.data)
                is DataResponse.SessionError -> _model.value = UiModel.SessionError
            }
            _model.value = UiModel.Loading(false)
        }
    }

}
