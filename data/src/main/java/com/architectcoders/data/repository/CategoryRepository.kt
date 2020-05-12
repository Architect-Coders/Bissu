package com.architectcoders.data.repository

import com.architectcoders.data.source.CategoryLocalDatasource
import com.architectcoders.data.source.CategoryRemoteDatasource
import com.architectcoders.domain.entities.Category
import com.architectcoders.domain.entities.DataResponse
import com.architectcoders.domain.reositories.CategoryRepository

/**
 * Created by Anibal Cortez on 2020-02-17.
 */
class CategoryRepository(private val localDatasource: CategoryLocalDatasource, private val remoteDatasource: CategoryRemoteDatasource)
    : CategoryRepository{

    override suspend fun getCategories(): DataResponse<List<Category>> {
        if (localDatasource.isEmpty()) {
            val result = remoteDatasource.getCategories()
            if(result  is DataResponse.Success){
                localDatasource.saveCategories(result.data)
            }
              return result
        }
        return DataResponse.Success(localDatasource.getCategories())
    }

}