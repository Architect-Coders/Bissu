package com.architectcoders.framework.database.entitiesDao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.architectcoders.framework.database.entities.Category

/**
 * Created by Anibal Cortez on 2020-02-17.
 */
@Dao
interface CategoryDao {
    @Query("SELECT * FROM Category")
    fun getCategories(): List<Category>

    @Query("SELECT COUNT() FROM Category")
    fun count(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCategories(categories: List<Category>)

}