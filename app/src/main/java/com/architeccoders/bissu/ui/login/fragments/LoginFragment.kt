package com.architeccoders.bissu.ui.login.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.architeccoders.bissu.R
import com.architeccoders.bissu.data.database.login.LoginDataSource
import com.architeccoders.bissu.data.server.login.LoginDatasource
import com.architeccoders.bissu.ui.common.app
import com.architeccoders.bissu.ui.common.getViewModel
import com.architeccoders.bissu.ui.login.LoginViewModel
import com.architectcoders.data.repository.UserRepository
import com.architectcoders.usecases.DoLogin
import kotlinx.android.synthetic.main.login_view.*

/**
 * Created by Anibal Cortez on 2019-12-11.
 */
class LoginFragment : Fragment()  {

    private lateinit var viewModel : LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getViewModel {
            LoginViewModel(
                DoLogin(
                    UserRepository(
                        LoginDataSource(activity!!.app.db),
                        LoginDatasource()
                    )
                )
            )
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.login_view, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.model.observe(this, Observer(::updateUi))

        login_button.setOnClickListener {
            doLogin(username_edit_text.text?.toString(),password_edit_text.text?.toString())
        }
        create_account_text.setOnClickListener {
            viewModel.onCreateAccountClicked()
        }
    }

    private fun updateUi(model : LoginViewModel.UiModel){
        progress_bar.visibility = if (model is LoginViewModel.UiModel.Loading) View.VISIBLE else View.GONE
        when(model){
             is LoginViewModel.UiModel.Content -> {
                 if (model.success) {
                    // go to home
                 }
                 else   Toast.makeText(context, "User not registered", Toast.LENGTH_LONG).show()
             }
            is LoginViewModel.UiModel.Navigation -> {
                val fragmentManager = activity!!.supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()
                val fragment = CreateAccountFragment()
                fragmentTransaction.replace(R.id.content_main, fragment)
                fragmentTransaction.commit()
            }
        }
    }

    private fun doLogin(username : String?, password : String? ){
        if (!username.isNullOrEmpty() && !password.isNullOrEmpty())
            viewModel.doLogin(username , password)
    }

}