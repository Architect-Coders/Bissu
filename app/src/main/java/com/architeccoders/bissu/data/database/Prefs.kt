package com.architeccoders.bissu.data.database

import android.content.Context
import android.content.SharedPreferences

class Prefs(context: Context) {
    private val PREFS_FILENAME ="com.bissu.pref"
    private val KEY_USERNAME = "username"
    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME,0)
    var userName: String
        get() = prefs.getString(KEY_USERNAME, null)
        set(value) = prefs.edit().putString(KEY_USERNAME,value).apply()
}