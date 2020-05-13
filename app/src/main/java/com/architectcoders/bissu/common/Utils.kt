package com.architectcoders.bissu.common

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.ConnectivityManager
import android.util.Base64
import com.architectcoders.bissu.main.MainActivity
import com.architectcoders.framework.database.LocalDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream


suspend fun  closeSession(activity : Activity){
    val intent = Intent(activity, MainActivity::class.java)
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
    deleteAllModels(activity)
    activity.startActivity(intent)
    activity.finish()
}

private suspend fun deleteAllModels(activity: Activity){
    withContext(Dispatchers.IO) {
        val database = LocalDatabase.build(activity.applicationContext)
        database.bookData().deleteAllBooks();
        database.categoryDao().deleteAllCategories()
        database.observationDao().deleteAllObservations()
        database.userDao().deleteAllUser()
    }
}

fun Context.isAvailableNetwork(): Boolean {
    val connectivityManager =this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = connectivityManager.activeNetworkInfo
    return networkInfo != null && networkInfo.isConnected
}

fun launchMainActivity(activity : Activity){
    val intent = Intent(activity, MainActivity::class.java)
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
    activity.startActivity(intent)
}

fun Bitmap.toBase64(): String {
    val baos = ByteArrayOutputStream()
    this.compress(Bitmap.CompressFormat.PNG, 100, baos)
    val b = baos.toByteArray()
    return Base64.encodeToString(b, Base64.DEFAULT)
}

fun String.toBitmap(): Bitmap? {
    return try {
        val encodedByte = Base64.decode(this, Base64.DEFAULT)
        val bitmap = BitmapFactory.decodeByteArray(encodedByte, 0, encodedByte.size)
        return bitmap
    } catch (e: Exception) {
        null
    }
}


