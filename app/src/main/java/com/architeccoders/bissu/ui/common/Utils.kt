package com.architeccoders.bissu.ui.common

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import java.io.ByteArrayOutputStream


inline fun BitMapToString(bitmap: Bitmap): String? {
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos)
        val b = baos.toByteArray()
        return Base64.encodeToString(b, Base64.DEFAULT)
}

inline fun StringToBitMap(encodedString: String) : Bitmap? {
    try {
            val encodedByte = Base64.decode(encodedString, Base64.DEFAULT)
            val bitmap = BitmapFactory.decodeByteArray(encodedByte, 0, encodedByte.size)
            return bitmap

    } catch (e: Exception) {
            Log.d("Error", e.message)
            return null
    }
}
