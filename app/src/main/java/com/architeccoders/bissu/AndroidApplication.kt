package com.architeccoders.bissu

import android.app.Application
import androidx.room.Room
import com.architeccoders.bissu.data.database.LocalDatabase
import com.google.firebase.firestore.FirebaseFirestore

/**
 * Created by Anibal Cortez on 2019-12-11.
 */
class AndroidApplication : Application() {

    lateinit var db: LocalDatabase
        private set
    lateinit var firebaseDB: FirebaseFirestore
        private set

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            this,
            LocalDatabase::class.java,
            "login-db"
        ).build()
        firebaseDB =  FirebaseFirestore.getInstance()
    }


}