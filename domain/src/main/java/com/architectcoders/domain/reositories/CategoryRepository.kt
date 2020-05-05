package com.architectcoders.domain.reositories

import com.architectcoders.domain.entities.Category

/**
 * Created by Anibal Cortez on 2020-03-05.
 */
interface CategoryRepository {
    suspend fun getCategories(): List<Category>
}