package com.architectcoders.bissu.ui.home.profile

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.architectcoders.bissu.R
import kotlinx.android.synthetic.main.activity_profile.*
import androidx.appcompat.widget.Toolbar
import com.architectcoders.bissu.ui.login.fragments.UpdateAccountFragment

class ProfileActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        setSupportActionBar(toolbarProfile as Toolbar?)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        startEditProfile()
    }

    private fun startEditProfile() {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val fragment = UpdateAccountFragment()
        fragmentTransaction.replace(R.id.content_profile, fragment)
        fragmentTransaction.commit()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
