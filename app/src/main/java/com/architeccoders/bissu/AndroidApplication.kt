package com.architeccoders.bissu

import android.app.Application
import androidx.room.Room
import com.architeccoders.bissu.data.database.LoginDatabase

/**
 * Created by Anibal Cortez on 2019-12-11.
 */
class AndroidApplication : Application() {

    lateinit var db: LoginDatabase
        private set

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            this,
            LoginDatabase::class.java,
            "login-db"
        ).build()
    }


}