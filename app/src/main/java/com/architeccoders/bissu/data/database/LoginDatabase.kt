package com.architeccoders.bissu.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Created by Anibal Cortez on 2019-12-11.
 */
@Database(entities = [User::class], version = 1)
abstract class LoginDatabase : RoomDatabase(){

    abstract fun userDao() : UserDao

}