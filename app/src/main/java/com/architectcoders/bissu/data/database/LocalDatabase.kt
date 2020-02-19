package com.architectcoders.bissu.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.architectcoders.bissu.data.database.entities.*

/**
 * Created by Anibal Cortez on 2019-12-11.
 */
@Database(
    entities = [User::class, Book::class, Observation::class],
    version = 1,
    exportSchema = false
)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun bookData(): BookDao
    abstract fun observationDao(): ObservationDao
}