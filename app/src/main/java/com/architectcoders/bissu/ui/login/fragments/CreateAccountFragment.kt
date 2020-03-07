package com.architectcoders.bissu.ui.login.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.architectcoders.bissu.R
import com.architectcoders.bissu.ui.login.CreateAccountViewModel
import com.architectcoders.domain.entities.User
import kotlinx.android.synthetic.main.login_create_account_view.*
import org.koin.androidx.scope.currentScope
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by Anibal Cortez on 2019-12-16.
 */
class CreateAccountFragment : Fragment() {

    private val viewModel: CreateAccountViewModel by currentScope.viewModel(this)

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        return inflater.inflate(R.layout.login_create_account_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.model.observe(viewLifecycleOwner, Observer(::updateUi))

        create_account_button.setOnClickListener {
            createAccount(
                username_edit_text?.text.toString(),
                email_edit_text.text?.toString(),
                first_name_edit_text.text?.toString(),
                last_name_edit_text.text?.toString(),
                password_edit_text.text?.toString(),
                repeat_password_edit_text.text?.toString()
            );
        }
    }
    private fun updateUi(model: CreateAccountViewModel.UiModel) {
        progress_bar_view.visibility = if (model is CreateAccountViewModel.UiModel.Loading) View.VISIBLE else View.GONE
        when (model) {
            is CreateAccountViewModel.UiModel.Content -> {
                if (!model.status)
                    Toast.makeText(context, "User not registered", Toast.LENGTH_LONG).show()
                else viewModel.loginNavigation()
            }
            is CreateAccountViewModel.UiModel.NavigationLogin -> {
                val fragmentManager = activity!!.supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()
                val fragmentProfile = LoginFragment()
                fragmentTransaction.replace(R.id.content_main, fragmentProfile)
                fragmentTransaction.commit()
            }
        }
    }

    private fun createAccount( username: String?,email: String?, firstName: String?, lastName: String?,
        password: String?,repeatPassword: String?) {
        if (username.isNullOrEmpty() || firstName.isNullOrEmpty() || lastName.isNullOrEmpty()
            || password.isNullOrEmpty() || repeatPassword.isNullOrEmpty() || email.isNullOrEmpty() )
            Toast.makeText(context, "complete all values", Toast.LENGTH_LONG).show()
        else if (!password.equals(repeatPassword))
            Toast.makeText(context, "passwords must be the same", Toast.LENGTH_LONG).show()
        else {
            val userData = User(
                id = "",
                username = username,
                email = email,
                firstName = firstName,
                lastName = lastName,
                categories = arrayListOf()
            )
           viewModel.createAccount(userData, password)
        }
    }


}