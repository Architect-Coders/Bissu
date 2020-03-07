package com.architectcoders.bissu.ui.common

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import java.io.ByteArrayOutputStream
import java.lang.Exception


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


