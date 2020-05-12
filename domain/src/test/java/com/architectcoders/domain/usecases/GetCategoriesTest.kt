package com.architectcoders.domain.usecases

import com.architectcoders.domain.repositories.CategoryRepository
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetCategoriesTest{

    @Mock
    lateinit var categoryRepository : CategoryRepository

    lateinit var getCategories : GetCategories


    @Before
    fun init(){
        getCategories = GetCategories(categoryRepository)
    }

    @Test
    fun invoqueCategoriesRepository(){
        runBlocking {
            getCategories.invoke()

            verify(categoryRepository).getCategories()

        }
    }
}