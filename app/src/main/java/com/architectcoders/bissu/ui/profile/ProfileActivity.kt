package com.architectcoders.bissu.ui.profile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.architectcoders.bissu.R
import com.architectcoders.bissu.ui.profile.fragments.ProfileFragment



class ProfileActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        startProfileFragment()
    }

    private fun startProfileFragment() {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val fragment = ProfileFragment()
        fragmentTransaction.add(R.id.content_profile, fragment)
        fragmentTransaction.commit()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }


}
