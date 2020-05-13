package com.architectcoders.framework.database.converters

import androidx.room.TypeConverter
import com.architectcoders.framework.database.entities.ObservationBook
import com.google.gson.Gson

object ObservationBookConverter{

    @TypeConverter
    @JvmStatic
    fun saveBook(book : ObservationBook) : String{
        return Gson().toJson(book)
    }

    @TypeConverter
    @JvmStatic
    fun getBook(book : String) : ObservationBook{
        return Gson().fromJson(book, ObservationBook::class.java)
    }
}
