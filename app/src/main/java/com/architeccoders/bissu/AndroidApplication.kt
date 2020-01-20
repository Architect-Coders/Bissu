package com.architeccoders.bissu

import android.app.Application
import androidx.room.Room
import com.architeccoders.bissu.data.database.LocalDatabase

/**
 * Created by Anibal Cortez on 2019-12-11.
 */
class AndroidApplication : Application() {

    lateinit var db: LocalDatabase
        private set


    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            this,
            LocalDatabase::class.java,
            "bissu-db"
        ).build()
    }


}