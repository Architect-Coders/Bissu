package com.architeccoders.bissu.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.architeccoders.bissu.R
import com.architeccoders.bissu.session
import com.architeccoders.bissu.ui.common.app
import com.architeccoders.bissu.ui.login.LoginActivity
import com.architeccoders.bissu.ui.profile.ProfileActivity
import com.architectcoders.data.repository.UserRepository
import com.architectcoders.usecases.CreateAccount
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        validateLogin()
//        session.userName = "gaby"
//        val userTest = com.architectcoders.domain.User(
//            username = session.userName,
//            password = "test",
//            firstName = "Gabriela",
//            lastName = "Orosco Montaño"
//        )
//        val db = RoomDataSource(this!!.app.db)
//        CoroutineScope(Dispatchers.IO).launch {
//            db.saveUser(userTest)
//            showProfile()
//        }

    }

    private fun validateLogin(){
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    private fun showProfile() {
        startActivity(Intent(this, ProfileActivity::class.java))
        finish()
    }
}
