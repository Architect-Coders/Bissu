package com.architectcoders.data.repository

import com.architectcoders.data.source.CategoryLocalDatasource
import com.architectcoders.data.source.CategoryRemoteDatasource
import com.architectcoders.domain.Category

/**
 * Created by Anibal Cortez on 2020-02-17.
 */
class CategoryRepository(private val localDatasource: CategoryLocalDatasource, private val remoteDatasource: CategoryRemoteDatasource) {

    suspend fun getCategories(): List<Category> {
        if (localDatasource.isEmpty()) {
            val categories = remoteDatasource.getCategories()
            localDatasource.saveCategories(categories)
        }
        return localDatasource.getCategories()
    }


}