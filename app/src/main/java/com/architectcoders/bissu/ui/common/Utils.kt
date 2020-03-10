package com.architectcoders.bissu.ui.common

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import com.architectcoders.bissu.ui.MainActivity
import java.io.ByteArrayOutputStream


fun closeSession(activity : Activity){
    val intent = Intent(activity, MainActivity::class.java)
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
    //remove all models
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


