package com.architectcoders.bissu.data.database.category

import com.architectcoders.bissu.data.database.LocalDatabase
import com.architectcoders.bissu.data.mappers.toDomainCategory
import com.architectcoders.bissu.data.mappers.toRoomCategory
import com.architectcoders.data.source.CategoryLocalDatasource
import com.architectcoders.domain.Category
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