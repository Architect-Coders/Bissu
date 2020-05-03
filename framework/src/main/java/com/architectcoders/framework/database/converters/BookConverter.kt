package com.architectcoders.framework.database.converters

import androidx.room.TypeConverter
import com.architectcoders.framework.database.entities.Book
import com.google.gson.Gson

object BookConverter{

    @TypeConverter
    @JvmStatic
    fun saveBook(book : Book) : String{
        return Gson().toJson(book)
    }

    @TypeConverter
    @JvmStatic
    fun getBook(book : String) : Book{
        return Gson().fromJson(book, Book::class.java)
    }
}
