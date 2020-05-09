package com.architectcoders.bissu.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.architectcoders.bissu.R
import kotlinx.android.synthetic.main.toolbar.*

class HomeActivity :   AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        navigateToHome();
    }

    private fun navigateToHome() {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val fragment = MainPagerFragment.newInstance()
        fragmentTransaction.replace(R.id.content_main, fragment)
        fragmentTransaction.commit()
    }

}