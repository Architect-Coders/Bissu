package com.architectcoders.bissu.data.database.entities

import androidx.room.TypeConverter
import com.google.gson.Gson

object CategoryConverter {
    @TypeConverter
    @JvmStatic
    fun saveCategories(categorie: Category): String {
        return Gson().toJson(categorie)
    }

    @TypeConverter
    @JvmStatic
    fun getCategories(categories: String): Category {
        return Gson().fromJson(categories, Category::class.java)
    }
}
