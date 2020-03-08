package com.architectcoders.bissu.ui.common

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import com.architectcoders.bissu.R

fun Context.showAlertDialog(message : String){
    // Use the Builder class for convenient dialog construction
    val builder = AlertDialog.Builder(this)
    builder.setMessage(message)
        .setPositiveButton(
            R.string.agree,
            DialogInterface.OnClickListener { dialog, id ->
                // FIRE ZE MISSILES!
            })
    builder.create()
    builder.show()
}