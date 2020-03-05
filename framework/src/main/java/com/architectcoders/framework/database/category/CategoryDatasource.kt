package com.architectcoders.framework.database.category

import com.architectcoders.framework.database.LocalDatabase

import com.architectcoders.data.source.CategoryLocalDatasource
import com.architectcoders.domain.entities.Category
import com.architectcoders.framework.mappers.toDomainCategory
import com.architectcoders.framework.mappers.toRoomCategory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Anibal Cortez on 2020-02-17.
 */
class CategoryDatasource(db: LocalDatabase) : CategoryLocalDatasource {

    private val categoryDao = db.categoryDao()

    override suspend fun isEmpty(): Boolean =
        withContext(Dispatchers.IO) { categoryDao.count() <= 0 }

    override suspend fun getCategories(): List<Category> =
        withContext(Dispatchers.IO) {
            categoryDao.getCategories().map {
                it.toDomainCategory()
            }
        }
    override suspend fun saveCategories(list: List<Category>) =
        withContext(Dispatchers.IO) {
            categoryDao.insertCategories(list.map { it.toRoomCategory() })
        }

}