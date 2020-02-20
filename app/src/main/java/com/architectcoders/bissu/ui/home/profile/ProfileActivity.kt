package com.architectcoders.bissu.ui.home.profile

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.architectcoders.bissu.R
import com.architectcoders.bissu.ui.login.fragments.CreateAccountFragment
import kotlinx.android.synthetic.main.activity_profile.*
import androidx.appcompat.widget.Toolbar

class ProfileActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        setSupportActionBar(toolbarProfile as Toolbar?)

        supportActionBar.let {
            it?.setDisplayHomeAsUpEnabled(true)
            it?.setDisplayShowHomeEnabled(true)
        }

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
