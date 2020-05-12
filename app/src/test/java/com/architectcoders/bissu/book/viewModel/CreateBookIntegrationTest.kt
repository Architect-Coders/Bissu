package com.architectcoders.bissu.book.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.architectcoders.bissu.FakeCategoryLocalDatasource
import com.architectcoders.bissu.FakeCategoryRemoteDatasource
import com.architectcoders.bissu.initMockedDi
import com.architectcoders.data.source.CategoryLocalDatasource
import com.architectcoders.data.source.CategoryRemoteDatasource
import com.architectcoders.domain.entities.DataResponse
import com.architectcoders.domain.usecases.CreateBook
import com.architectcoders.domain.usecases.GetBook
import com.architectcoders.domain.usecases.GetCategories
import com.example.testshared.mockedBook
import com.example.testshared.mockedCategory
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest
import org.koin.test.get
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CreateBookIntegrationTest : AutoCloseKoinTest() {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var observer: Observer<CreateBookViewModel.UiModel>

    private lateinit var viewModel: CreateBookViewModel

    @Before
    fun setUp() {
        val vmModule = module {
            factory { CreateBookViewModel(get(), get(), get()) }
            factory { GetCategories(get()) }
            factory { CreateBook(get()) }
        }
        initMockedDi(vmModule)
        viewModel = get()
    }

    @Test
    fun createBookSuccess(){

        viewModel.model.observeForever(observer)

        viewModel.createBook(
            mockedBook.title,
            mockedBook.author,
            mockedBook.pages,
            mockedBook.editorial,
            mockedBook.category.id,
            mockedBook.description,
            mockedBook.photoUrl
        )
        verify(observer).onChanged(CreateBookViewModel.UiModel.CreateBookSuccess)
    }

    @Test
    fun getCategoriesListByRemoteDatasource(){
        runBlocking {
            val categoryList = listOf(mockedCategory, mockedCategory)
            val resultExpected = DataResponse.Success(categoryList)

            val remoteDataSource = get<CategoryRemoteDatasource>() as FakeCategoryRemoteDatasource
            remoteDataSource.mockedResponse = resultExpected

            val locaDataSource = get<CategoryLocalDatasource>() as FakeCategoryLocalDatasource
            locaDataSource.isEmpty = true

            viewModel.model.observeForever(observer)

            viewModel.getCategories()

             verify(observer).onChanged(CreateBookViewModel.UiModel.CategoryContent(categoryList))

        }
    }

    @Test
    fun getCategoriesListByLocalDatasource(){
        runBlocking {

            val categoryList = listOf(mockedCategory,mockedCategory)

            val locaDataSource = get<CategoryLocalDatasource>() as FakeCategoryLocalDatasource
            locaDataSource.isEmpty = false
            locaDataSource.categoyList = categoryList.toMutableList()

            viewModel.model.observeForever(observer)

            viewModel.getCategories()

            verify(observer).onChanged(CreateBookViewModel.UiModel.CategoryContent(categoryList))

        }
    }

}