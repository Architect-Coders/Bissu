package com.architectcoders.bissu

import android.app.Application

/**
 * Created by Anibal Cortez on 2019-12-11.
 */

class AndroidApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        initDI()
    }
}