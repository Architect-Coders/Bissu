package com.architectcoders.framework.database.converters

import androidx.room.TypeConverter
import com.architectcoders.framework.database.entities.Category
import com.google.gson.Gson

object CategoryConverter {
    @TypeConverter
    @JvmStatic
    fun saveCategories(category: Category?): String {
        return Gson().toJson(category)
    }

    @TypeConverter
    @JvmStatic
    fun getCategories(categories: String): Category? {
        return Gson().fromJson(categories, Category::class.java)
    }
}
