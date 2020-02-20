package com.architectcoders.bissu.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.architectcoders.bissu.AndroidApplication
import com.architectcoders.bissu.R
import com.architectcoders.bissu.data.database.Prefs
import com.architectcoders.bissu.ui.login.LoginActivity
import org.koin.android.ext.android.inject
import com.architectcoders.bissu.ui.login.fragments.CreateAccountFragment
import com.architectcoders.bissu.ui.profile.ProfileActivity
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : AppCompatActivity() {

    private val session: Prefs by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar.let {
            setSupportActionBar(toolbar)
            it?.setDisplayShowHomeEnabled(true)
            it?.setDisplayHomeAsUpEnabled(true)
        }

        if(session.isUserLogged){
            goToHome()
        }else{
            goToLogin()
        }
    }

    private fun goToLogin(){
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    private fun goToHome() {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val fragment = MainPagerFragment.newInstance()
        fragmentTransaction.replace(R.id.content_main, fragment)
        fragmentTransaction.commit()
    }
}
