package com.architectcoders.data.repository


import com.architectcoders.data.source.CategoryLocalDatasource
import com.architectcoders.data.source.CategoryRemoteDatasource
import com.example.testshared.mockedCategory
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CategoryRepositoryTest {


    @Mock
    lateinit var localDatasource : CategoryLocalDatasource

    @Mock
    lateinit var remoteDatasource: CategoryRemoteDatasource

    lateinit var cagoryRepository : CategoryRepository

    @Before
    fun init(){
        cagoryRepository = CategoryRepository(localDatasource,remoteDatasource)
    }

    @Test
    fun getCategoriesFromRemoteDatasource() {
       runBlocking {
           val categories = listOf(mockedCategory)
           whenever(localDatasource.isEmpty()).thenReturn(true)
           whenever(remoteDatasource.getCategories()).thenReturn(categories)

           cagoryRepository.getCategories()

           verify(remoteDatasource).getCategories()
       }

    }
    @Test
    fun saveCategoriesFromRemoteDatasource() {
        runBlocking {
            val categories = listOf(mockedCategory)
            whenever(localDatasource.isEmpty()).thenReturn(true)
            whenever(remoteDatasource.getCategories()).thenReturn(categories)

            cagoryRepository.getCategories()

            verify(localDatasource).saveCategories(categories)
        }

    }


    @Test
    fun getCategoriesFromLocalDatasource(){
        runBlocking {
            //val categories = listOf(mockedCategory)
            whenever(localDatasource.isEmpty()).thenReturn(false)
            //whenever(localDatasource.getCategories()).thenReturn(categories)
            cagoryRepository.getCategories()
            verify(localDatasource).getCategories()
        }

    }

}