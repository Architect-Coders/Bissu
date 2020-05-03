package com.architectcoders.bissu.ui.login.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.architectcoders.bissu.R
import com.architectcoders.framework.database.Prefs
import com.architectcoders.bissu.ui.MainActivity
import com.architectcoders.bissu.ui.common.showAlertDialog
import com.architectcoders.bissu.ui.login.viewmodels.LoginViewModel
import kotlinx.android.synthetic.main.login_login.*
import org.koin.android.ext.android.inject
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel

/**
 * Created by Anibal Cortez on 2019-12-11.
 */
class LoginFragment : Fragment() {

    private val session: Prefs by inject()
    private val viewModel: LoginViewModel by lifecycleScope.viewModel(this)

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        return inflater.inflate(R.layout.login_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.model.observe(viewLifecycleOwner, Observer(::updateUi))

        login_button.setOnClickListener {
            doLogin()
        }
        create_account_text.setOnClickListener {
            viewModel.onCreateAccountClicked()
        }
    }
    private fun doLogin() {
        viewModel.doLogin(context!!,username_edit_text, password_edit_text,username_text_input, password_input_layout )
    }

    private fun updateUi(model: LoginViewModel.UiModel) {
        progress_bar_view.visibility = if (model is LoginViewModel.UiModel.Loading) View.VISIBLE else View.GONE
        when (model) {
            is LoginViewModel.UiModel.LoginContent -> validateLoginConten(model.success)
            is LoginViewModel.UiModel.Navigation ->  navigateToCreateAccountFragment();
        }
    }
    private fun validateLoginConten(succes : Boolean){

        if (succes) {
            session.isUserLogged = true
            navigateToHomeActivitity()
        }else context?.showAlertDialog(resources.getString(R.string.login_error))
    }

    private fun navigateToHomeActivitity(){
        val intent = Intent(activity, MainActivity::class.java)
        activity?.startActivity(intent)
        activity?.finish()
    }
    private fun navigateToCreateAccountFragment(){
        session.isUserLogged = true
        val fragmentManager = activity!!.supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val fragment = CreateAccountFragment()
        fragmentTransaction.replace(R.id.content_main, fragment)
        fragmentTransaction.commit()
    }


}