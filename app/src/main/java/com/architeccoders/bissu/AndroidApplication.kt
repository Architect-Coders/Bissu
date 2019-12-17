package com.architeccoders.bissu

import android.app.Application
import androidx.room.Room
import com.architeccoders.bissu.data.database.LoginDatabase
import com.google.firebase.firestore.FirebaseFirestore

/**
 * Created by Anibal Cortez on 2019-12-11.
 */
class AndroidApplication : Application() {

    lateinit var db: LoginDatabase
        private set
    lateinit var firebaseDB: FirebaseFirestore
        private set

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            this,
            LoginDatabase::class.java,
            "login-db"
        ).build()
        firebaseDB =  FirebaseFirestore.getInstance()
    }


}