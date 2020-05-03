package com.architectcoders.bissu.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer

import com.architectcoders.bissu.ui.home.HomeActivity
import com.architectcoders.bissu.ui.login.LoginActivity
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by lifecycleScope.viewModel(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.model.observe(this, Observer(::updateUi))
        viewModel.getUser()
    }

    private fun updateUi(model: MainViewModel.UiModel) {
        when (model) {
            is MainViewModel.UiModel.NavigateToHome -> navigateToHome()
            is MainViewModel.UiModel.NavigateToLogin -> navigateToLogin()
        }
    }

    private fun navigateToLogin(){
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    private fun navigateToHome() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }
}
