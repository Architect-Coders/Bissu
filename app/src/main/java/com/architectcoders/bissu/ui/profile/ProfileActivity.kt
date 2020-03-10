package com.architectcoders.bissu.ui.profile

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.architectcoders.bissu.R
import kotlinx.android.synthetic.main.activity_profile.*
import androidx.appcompat.widget.Toolbar
import com.architectcoders.bissu.ui.profile.fragments.ChangePasswordFragment
import com.architectcoders.bissu.ui.profile.fragments.UpdateAccountFragment

class ProfileActivity : AppCompatActivity() {

    companion object {
        val NAVIGATION : String = "NAVIGATION"
        val UPDATE_CCOUNT: String = "UPDATE_CCOUNT"
        val CHANGE_PASSWORD: String = "CHANGE_PASSWORD"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        setSupportActionBar(toolbarProfile as Toolbar?)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
        intent.extras?.let {
            if (it.containsKey(NAVIGATION)) {
                validateNavigation(it.get(NAVIGATION) as String)
            }
        }
    }

    private fun validateNavigation(navigation: String) {
        when (navigation) {
            in UPDATE_CCOUNT -> navigateToUpdateAccount()
            in CHANGE_PASSWORD -> navigateToChangePassword()
        }
    }

    private fun navigateToUpdateAccount() {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val fragment = UpdateAccountFragment()
        fragmentTransaction.replace(R.id.content_profile, fragment)
        fragmentTransaction.commit()
    }

    private fun navigateToChangePassword() {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val fragment = ChangePasswordFragment()
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
