package com.architeccoders.bissu.ui.profile

import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.architeccoders.bissu.R
import com.architeccoders.bissu.ui.common.getViewModel
import com.architeccoders.bissu.ui.login.LoginActivity
import com.architeccoders.bissu.ui.login.fragments.CreateAccountFragment
import com.architectcoders.domain.User

import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.content_profile.*

class ProfileActivity : AppCompatActivity() {
    private lateinit var viewModel: ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        setSupportActionBar(toolbar)
        val userTest = User(  username = "calyr",
            password = "test",
            firstName = "Roberto Carlos",
            lastName = "Callisaya Mamani")
        viewModel = getViewModel {
            ProfileViewModel(userTest)
        }
        viewModel.model.observe(this, Observer { ::updateUi })

        fab.setOnClickListener {
            viewModel.onProfileEditClicked(userTest)
        }

        viewModel.doRefresh()
    }

    fun updateUi(model: ProfileViewModel.UiModel) {
        when ( model ) {
            is ProfileViewModel.UiModel.Navigation -> {
                Toast.makeText(this.applicationContext, "Edit User Profile", Toast.LENGTH_LONG).show()
            }
            is ProfileViewModel.UiModel.Content -> {
                with(model.user) {
                    profile_username.text = username
                    profile_name.text = firstName + lastName
                }

            }
        }
    }

}
