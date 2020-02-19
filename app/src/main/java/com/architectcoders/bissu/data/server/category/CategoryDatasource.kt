package com.architectcoders.bissu.data.server.category

import com.architectcoders.bissu.data.mappers.toDomainCategory
import com.architectcoders.bissu.data.server.RetrofitClient
import com.architectcoders.data.source.CategoryRemoteDatasource
import com.architectcoders.domain.Category
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Anibal Cortez on 2020-02-17.
 */
class CategoryDatasource : CategoryRemoteDatasource {

    override suspend fun getCategories(): List<Category> =
        withContext(Dispatchers.IO) {
            RetrofitClient().categoryService
                .getCategories()
                .await()
                .categories.map {
                it.toDomainCategory()
            }

        }
}