package com.architectcoders.domain.usecases

import com.architectcoders.domain.entities.Category
import com.architectcoders.domain.entities.DataResponse
import com.architectcoders.domain.repositories.CategoryRepository

/**
 * Created by Anibal Cortez on 2020-02-17.
 */
class GetCategories(private val categoryRepository : CategoryRepository) {
    suspend fun invoke() : DataResponse<List<Category>> = categoryRepository.getCategories();
}