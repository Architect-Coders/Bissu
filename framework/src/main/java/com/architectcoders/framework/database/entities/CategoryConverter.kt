package com.architectcoders.framework.database.entities

import androidx.room.TypeConverter
import com.google.gson.Gson

/**
 * Created by Anibal Cortez on 2020-01-19.
 */
object CategoryConverter {
    @TypeConverter
    @JvmStatic
    fun saveCategories(categories : List<Category>) : String{
        val gson = Gson()
        return gson.toJson(categories)
    }

    @TypeConverter
    @JvmStatic
    fun getCategories(categories : String) : List<Category>{
        val gson = Gson()
        return gson.fromJson(categories, Array<Category>::class.java).toList()
    }
}
