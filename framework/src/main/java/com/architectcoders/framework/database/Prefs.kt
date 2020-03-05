package com.architectcoders.framework.database

import android.content.Context
import android.content.SharedPreferences

class Prefs(context: Context) {
    private val PREFS_FILENAME ="com.bissu.pref"

    private val KEY_USERNAME = "username"
    private val KEY_USER_LOGGED = "user_logged"

    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME,0)

    var userName: String
        get() = prefs.getString(KEY_USERNAME, null)
        set(value) = prefs.edit().putString(KEY_USERNAME,value).apply()

    var isUserLogged: Boolean
        get() = prefs.getBoolean(KEY_USER_LOGGED, false)
        set(value) = prefs.edit().putBoolean(KEY_USER_LOGGED,value).apply()
}