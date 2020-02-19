package com.architectcoders.data.source

import com.architectcoders.domain.Category

/**
 * Created by Anibal Cortez on 2020-02-17.
 */
interface CategoryRemoteDatasource {
    suspend fun getCategories() : List<Category>
}