package com.architectcoders.bissu.ui.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.architectcoders.bissu.R
import com.architectcoders.bissu.ui.login.fragments.LoginFragment

/**
 * Created by Anibal Cortez on 2019-12-11.
 */
class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        startLoginFragment()
    }

    private fun startLoginFragment() {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val fragment = LoginFragment()
        fragmentTransaction.add(R.id.content_main, fragment)
        fragmentTransaction.commit()
    }
}