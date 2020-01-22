package com.architectcoders.bissu.ui.login.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.architectcoders.bissu.R
import com.architectcoders.framework.database.login.LoginDataSource
import com.architectcoders.framework.server.login.LoginDatasource
import com.architectcoders.bissu.ui.common.app
import com.architectcoders.bissu.ui.common.getViewModel
import com.architectcoders.bissu.ui.login.LoginCreateAccountViewModel
import com.architectcoders.data.repository.UserRepository
import com.architectcoders.domain.entities.User
import com.architectcoders.domain.usescases.CreateAccount
import kotlinx.android.synthetic.main.login_create_account_view.*
import kotlinx.android.synthetic.main.login_create_account_view.password_edit_text
import kotlinx.android.synthetic.main.login_create_account_view.username_edit_text

/**
 * Created by Anibal Cortez on 2019-12-16.
 */
class CreateAccountFragment : Fragment() {

    private lateinit var viewModel: LoginCreateAccountViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = getViewModel {
            LoginCreateAccountViewModel(
                CreateAccount(
                    UserRepository(
                        LoginDataSource(activity!!.app.db),
                        LoginDatasource()
                    )
                )
            )
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.login_create_account_view, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.model.observe(this, Observer(::updateUi))

        create_account_button.setOnClickListener {
            createAccount(
                username_edit_text?.text.toString(),
                email_edit_text.text?.toString(),
                first_name_edit_text.text?.toString(),
                last_name_edit_text.text?.toString(),
                password_edit_text.text?.toString(),
                repeat_password_edit_text.text?.toString()
            )

        }



    }

    private fun updateUi(model : LoginCreateAccountViewModel.UiModel){
        progress_bar.visibility = if (model is LoginCreateAccountViewModel.UiModel.Loading) View.VISIBLE else View.GONE
        when(model){
            is LoginCreateAccountViewModel.UiModel.Content -> {
                if (model.status){
                    // go to next screen
                }else Toast.makeText(context, "User not registered", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun createAccount(username: String?,email : String?, firstName: String?, lastName: String?, password: String?, repeatPassword: String?) {
        if (username.isNullOrEmpty() || firstName.isNullOrEmpty() || lastName.isNullOrEmpty()
            || password.isNullOrEmpty() || repeatPassword.isNullOrEmpty() || email.isNullOrEmpty())
            Toast.makeText(context, "complete all values", Toast.LENGTH_LONG).show()
        else if (!password.equals(repeatPassword))
            Toast.makeText(context, "passwords must be the same", Toast.LENGTH_LONG).show()
        else
            viewModel.createAccount(
                User(
                    username = username,
                    email = email,
                    firstName = firstName,
                    lastName = lastName,
                    photoUrl = null,
                    categories = arrayListOf()
                ),
                password
            )
    }


}