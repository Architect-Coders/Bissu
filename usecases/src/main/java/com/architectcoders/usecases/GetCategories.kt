package com.architectcoders.usecases

import com.architectcoders.data.repository.CategoryRepository
import com.architectcoders.domain.Category

/**
 * Created by Anibal Cortez on 2020-02-17.
 */
class GetCategories(private val categoryRepository : CategoryRepository) {
    suspend fun invoke() : List<Category> = categoryRepository.getCategories();
}