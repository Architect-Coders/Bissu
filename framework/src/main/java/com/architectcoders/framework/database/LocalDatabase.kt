package com.architectcoders.framework.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.architectcoders.framework.database.entities.*
import com.architectcoders.framework.database.entitiesDao.BookDao
import com.architectcoders.framework.database.entitiesDao.CategoryDao
import com.architectcoders.framework.database.entitiesDao.ObservationDao
import com.architectcoders.framework.database.entitiesDao.UserDao

/**
 * Created by Anibal Cortez on 2019-12-11.
 */
@Database(
    entities = [User::class, Book::class, Observation::class, Category::class],
    version = 1,
    exportSchema = false
)
abstract class LocalDatabase : RoomDatabase() {

    companion object {
        fun build(context: Context) = Room.databaseBuilder(
            context,
            LocalDatabase::class.java,
            "bissu-db"
        ).build()
    }

    abstract fun userDao(): UserDao
    abstract fun bookData(): BookDao
    abstract fun categoryDao() : CategoryDao
    abstract fun observationDao(): ObservationDao
}