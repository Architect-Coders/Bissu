package com.architectcoders.framework.database.converters

import androidx.room.TypeConverter
import com.architectcoders.framework.database.entities.Category
import com.google.gson.Gson

/**
 * Created by Anibal Cortez on 2020-01-19.
 */
object CategoryListConverter {
    @TypeConverter
    @JvmStatic
    fun saveCategories(categories : List<Category?>) : String{
        return Gson().toJson(categories)
    }

    @TypeConverter
    @JvmStatic
    fun getCategories(categories : String) : List<Category?>{
        return Gson().fromJson(categories, Array<Category>::class.java).toList()
    }
}
