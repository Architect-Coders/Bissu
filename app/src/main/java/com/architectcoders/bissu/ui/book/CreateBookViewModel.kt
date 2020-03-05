package com.architectcoders.bissu.ui.book

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.architectcoders.bissu.ui.common.ScopedViewModel
import com.architectcoders.domain.entities.Category
import com.architectcoders.domain.usecases.CreateBook
import com.architectcoders.domain.usecases.GetCategories
import kotlinx.coroutines.launch

/**
 * Created by Anibal Cortez on 2020-02-18.
 */
class CreateBookViewModel(private val getCategories : GetCategories, private val createBook : CreateBook) :   ScopedViewModel() {

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            return _model
        }

    sealed class UiModel {
        object Loading : UiModel()
        class Content(val list : List<Category>) : UiModel()
        class CreateBook(var boolean: Boolean) : UiModel()
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

    fun createBook( title: String, author: String,pages: String,editorial: String,categoryId: String, description: String){
        launch {
            _model.value = UiModel.Loading
            _model.value = UiModel.CreateBook(createBook.invoke(title,author, pages, editorial,categoryId, description))
        }
    }

}