package com.architectcoders.data.source

import com.architectcoders.domain.entities.Category

/**
 * Created by Anibal Cortez on 2020-02-17.
 */
interface CategoryLocalDatasource {
    suspend fun isEmpty(): Boolean
    suspend fun getCategories(): List<Category>
    suspend fun saveCategories(list: List<Category>)
}