package com.architectcoders.bissu.book.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.architectcoders.bissu.common.ScopedViewModel
import com.architectcoders.domain.entities.Category
import com.architectcoders.domain.entities.DataResponse
import com.architectcoders.domain.usecases.CreateBook
import com.architectcoders.domain.usecases.GetCategories
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

/**
 * Created by Anibal Cortez on 2020-02-18.
 */
class CreateBookViewModel(
    private val getCategories : GetCategories,
    private val createBook : CreateBook,
    uiDispacher : CoroutineDispatcher)
    :   ScopedViewModel(uiDispacher) {

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            return _model
        }

    sealed class UiModel {
        data class CategoryContent(val list : List<Category>) : UiModel()
        object CreateBookSuccess : UiModel()
        object Loading : UiModel()
        object ServerError : UiModel()
        object NetworkError : UiModel()
    }

    init {
        initScope()
    }

    fun getCategories(){
        launch {
            _model.value = UiModel.Loading
            val response =  getCategories.invoke()
            when(response){
                is DataResponse.Success ->  _model.value = UiModel.CategoryContent(response.data)
                is DataResponse.ServerError ->  _model.value = UiModel.ServerError
                is DataResponse.NetworkError ->  _model.value = UiModel.NetworkError
            }
        }
    }

    fun createBook( title: String, author: String,pages: String,editorial: String,categoryId: String, description: String, photoUrl: String?){
        launch {
            _model.value = UiModel.Loading
            val response = createBook.invoke(title,author, pages, editorial,categoryId, description, photoUrl)
            when(response){
                is DataResponse.Success -> _model.value =UiModel.CreateBookSuccess
                is DataResponse.ServerError -> _model.value =UiModel.ServerError
                is DataResponse.NetworkError -> _model.value = UiModel.NetworkError
            }

        }
    }

}