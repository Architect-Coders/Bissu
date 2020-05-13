package com.architectcoders.bissu.common

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import com.architectcoders.bissu.R

fun Context.showAlertDialog(message : String){
    val builder = AlertDialog.Builder(this)
    builder.setMessage(message)
        .setPositiveButton(
            R.string.agree, DialogInterface.OnClickListener
            { dialog, id ->
                dialog.dismiss()
            })
    builder.create()
    builder.show()
}