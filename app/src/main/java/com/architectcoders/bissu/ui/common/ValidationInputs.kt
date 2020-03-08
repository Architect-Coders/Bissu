package com.architectcoders.bissu.ui.common

import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout.validateInput(value: TextInputEditText, message: String): Boolean {
    this.apply { isErrorEnabled = false }
    if (value.text.isNullOrEmpty()) {
        this.apply { error = message; isErrorEnabled = true }
        return false
    }
    return true
}

fun TextInputLayout.validateInputPasword(password: TextInputEditText,repeatPassword : TextInputEditText, message: String): Boolean {
    this.apply { isErrorEnabled = false }
    if (!password.text!!.trim().equals(repeatPassword.text!!.trim())) {
        this.apply { error = message; isErrorEnabled = true }
        return false
    }
    return true
}