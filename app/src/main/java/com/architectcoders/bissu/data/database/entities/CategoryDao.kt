package com.architectcoders.bissu.data.database.entities

import androidx.room.*

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