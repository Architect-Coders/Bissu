package com.architectcoders.data.source

import com.architectcoders.domain.entities.Category
import com.architectcoders.domain.entities.DataResponse

/**
 * Created by Anibal Cortez on 2020-02-17.
 */
interface CategoryRemoteDatasource {
    suspend fun getCategories() : DataResponse<List<Category>>
}