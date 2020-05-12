package com.architectcoders.framework.server.category

import android.content.Context

import com.architectcoders.framework.server.RetrofitClient
import com.architectcoders.data.source.CategoryRemoteDatasource

import com.architectcoders.domain.entities.Category
import com.architectcoders.domain.entities.DataResponse
import com.architectcoders.framework.mappers.toDomainCategory
import com.architectcoders.framework.util.ErrorCode
import com.architectcoders.framework.util.isAvailableNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Anibal Cortez on 2020-02-17.
 */
class CategoryDatasource(val context: Context) : CategoryRemoteDatasource {

    override suspend fun getCategories(): DataResponse<List<Category>> =
        withContext(Dispatchers.IO) {
            if (!context.isAvailableNetwork()) return@withContext DataResponse.NetworkError

            val result = RetrofitClient().categoryService.getCategories().await()
            if (result.isSuccessful) {
                if (result.body() == null) return@withContext DataResponse.ServerError(ErrorCode.BAD_REQUEST)
                val categoryList: List<Category> =
                    result.body()!!.categories.map { it.toDomainCategory() }

                return@withContext DataResponse.Success(categoryList)
            }
            return@withContext DataResponse.ServerError(ErrorCode.SERVER_ERROR)
        }
}