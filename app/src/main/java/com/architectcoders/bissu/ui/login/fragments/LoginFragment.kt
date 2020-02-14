package com.architectcoders.bissu.ui.login.fragments

import android.content.Intent
import android.os.Bundle
import android.preference.Preference
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.architectcoders.bissu.AndroidApplication
import com.architectcoders.bissu.R
import com.architectcoders.bissu.data.database.Prefs
import com.architectcoders.bissu.data.database.login.LoginDataSource
import com.architectcoders.bissu.data.server.login.LoginDatasource
import com.architectcoders.bissu.ui.MainActivity
import com.architectcoders.bissu.ui.common.app
import com.architectcoders.bissu.ui.common.getViewModel
import com.architectcoders.bissu.ui.login.LoginViewModel
import com.architectcoders.data.repository.UserRepository
import com.architectcoders.usecases.DoLogin
import kotlinx.android.synthetic.main.login_view.*
import org.koin.android.ext.android.inject
import org.koin.androidx.scope.currentScope
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by Anibal Cortez on 2019-12-11.
 */
class LoginFragment : Fragment() {

    private val session: Prefs by inject()
    private val viewModel: LoginViewModel by currentScope.viewModel(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.model.observe(viewLifecycleOwner, Observer(::updateUi))

        login_button.setOnClickListener {
            doLogin(username_edit_text.text?.toString(), password_edit_text.text?.toString())
        }
        create_account_text.setOnClickListener {
            viewModel.onCreateAccountClicked()
        }
    }

    private fun updateUi(model: LoginViewModel.UiModel) {
        progress_bar.visibility =
            if (model is LoginViewModel.UiModel.Loading) View.VISIBLE else View.GONE
        when (model) {
            is LoginViewModel.UiModel.Content -> {
                if (model.success) {
                    session.isUserLogged = true

                    val intent = Intent(activity, MainActivity::class.java)
                    startActivity(intent)

                    activity?.finish()

                } else Toast.makeText(context, "User not registered", Toast.LENGTH_LONG).show()
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

    private fun doLogin(username: String?, password: String?) {
        if (!username.isNullOrEmpty() && !password.isNullOrEmpty())
            viewModel.doLogin(username, password)
    }

}