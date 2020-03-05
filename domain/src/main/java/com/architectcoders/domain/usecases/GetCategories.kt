package com.architectcoders.domain.usecases

import com.architectcoders.domain.entities.Category
import com.architectcoders.domain.interfaces.CategoryRepository

/**
 * Created by Anibal Cortez on 2020-02-17.
 */
class GetCategories(private val categoryRepository : CategoryRepository) {
    suspend fun invoke() : List<Category> = categoryRepository.getCategories();
}