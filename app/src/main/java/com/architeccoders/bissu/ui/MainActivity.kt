package com.architeccoders.bissu.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.architeccoders.bissu.ui.login.LoginActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        validateLogin()
    }

    private fun validateLogin(){
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}
