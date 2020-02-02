package com.architectcoders.bissu.ui.profile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.architectcoders.bissu.R
import com.architectcoders.bissu.ui.login.fragments.CreateAccountFragment


class ProfileActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        startEditProfile()
    }

    private fun startEditProfile() {
        val bundle = Bundle()
        bundle.putString("OPTION_FORM", getString(R.string.edit_profile_option_form))
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val fragment = CreateAccountFragment.newInstance(bundle)
        fragmentTransaction.replace(R.id.content_profile, fragment)
        fragmentTransaction.commit()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }
}
