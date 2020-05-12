package com.architectcoders.domain.reositories

import com.architectcoders.domain.entities.Category
import com.architectcoders.domain.entities.DataResponse

/**
 * Created by Anibal Cortez on 2020-03-05.
 */
interface CategoryRepository {
    suspend fun getCategories(): DataResponse<List<Category>>
}