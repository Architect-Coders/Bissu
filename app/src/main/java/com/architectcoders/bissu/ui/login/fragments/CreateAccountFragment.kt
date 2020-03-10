package com.architectcoders.bissu.ui.login.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.architectcoders.bissu.R
import com.architectcoders.bissu.ui.common.showAlertDialog
import com.architectcoders.bissu.ui.login.viewmodels.CreateAccountViewModel
import kotlinx.android.synthetic.main.login_create_account.*
import org.koin.androidx.scope.currentScope
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by Anibal Cortez on 2019-12-16.
 */
class CreateAccountFragment : Fragment() {

    private val viewModel: CreateAccountViewModel by currentScope.viewModel(this)

    override fun onCreateView( inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.login_create_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.model.observe(viewLifecycleOwner, Observer(::updateUi))

        create_account_button.setOnClickListener {
            createAccount();
        }
    }

    private fun updateUi(model: CreateAccountViewModel.UiModel) {
        progress_bar_view.visibility =if (model is CreateAccountViewModel.UiModel.Loading) View.VISIBLE else View.GONE
        when (model) {
            is CreateAccountViewModel.UiModel.CreateAccountContent -> validateAccountContent(model.success)
            is CreateAccountViewModel.UiModel.NavigationLogin -> navigationToLoginFragment();
        }
    }

    private fun validateAccountContent( succes : Boolean){
        if (succes) viewModel.loginNavigation() else context?.showAlertDialog("User not registered")
    }

    private fun navigationToLoginFragment(){
        val fragmentManager = activity!!.supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val fragmentProfile = LoginFragment()
        fragmentTransaction.replace(R.id.content_main, fragmentProfile)
        fragmentTransaction.commit()
    }

    private fun createAccount() {
        if (viewModel.validateUsername(context!!, username_edit_text, username_input_layout) &&
            viewModel.validateEmail(context!!, email_edit_text, email_input_layout) &&
            viewModel.validateFirstName(context!!, first_name_edit_text, first_name_imput_layout) &&
            viewModel.validateLastName(context!!, last_name_edit_text, last_name_input_layout) &&
            viewModel.validatePassword(context!!, password_edit_text, password_input_layout) &&
            viewModel.validateRepeatPassword(context!!,repeat_password_edit_text, repeat_password_input_layout) &&
            viewModel.validateInputPassword(context!!,password_edit_text, repeat_password_edit_text,repeat_password_input_layout)){
            viewModel.createAccount(
                username_edit_text.text.toString(),
                email_edit_text.text.toString(),
                first_name_edit_text.text.toString(),
                last_name_edit_text.text.toString(),
                password_edit_text.text.toString(),
                null)
        }
    }

}