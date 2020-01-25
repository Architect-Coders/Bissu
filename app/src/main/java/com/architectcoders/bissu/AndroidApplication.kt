package com.architectcoders.bissu

import android.app.Application
import androidx.room.Room
import com.architectcoders.bissu.data.database.LocalDatabase
import com.architectcoders.bissu.data.database.Prefs

/**
 * Created by Anibal Cortez on 2019-12-11.
 */

val session: Prefs by lazy {
    AndroidApplication.prefs!!
}
class AndroidApplication : Application() {

    lateinit var db: LocalDatabase
        private set
    companion object {
        var prefs: Prefs? = null
    }

    override fun onCreate() {
        super.onCreate()
        prefs = Prefs((applicationContext))
        db = Room.databaseBuilder(
            this,
            LocalDatabase::class.java,
            "bissu-db"
        ).build()
    }


}