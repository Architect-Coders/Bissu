package com.architectcoders.framework.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.architectcoders.framework.database.entities.User
import com.architectcoders.framework.database.entities.UserDao

/**
 * Created by Anibal Cortez on 2019-12-11.
 */
@Database(entities = [User::class],
    version = 1,
    exportSchema = false)
abstract class LocalDatabase : RoomDatabase(){

    abstract fun userDao() : UserDao

}