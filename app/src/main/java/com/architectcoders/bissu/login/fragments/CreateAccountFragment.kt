package com.architectcoders.bissu.login.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.architectcoders.bissu.R
import com.architectcoders.bissu.common.showAlertDialog
import com.architectcoders.bissu.login.viewmodels.CreateAccountViewModel
import com.architectcoders.domain.entities.User
import kotlinx.android.synthetic.main.login_create_account.*
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel

/**
 * Created by Anibal Cortez on 2019-12-16.
 */
class CreateAccountFragment : Fragment() {

    private val viewModel: CreateAccountViewModel by lifecycleScope.viewModel(this)

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
        when (model) {
            is CreateAccountViewModel.UiModel.CreateAccountContent -> validateAccountContent(model.user)
            is CreateAccountViewModel.UiModel.Loading -> progressVisibility(model.value)
            is CreateAccountViewModel.UiModel.NavigationLogin -> navigationToLoginFragment();
        }
    }

    private fun progressVisibility(value: Boolean) {
        progress_bar_view.visibility = if (value) View.VISIBLE else View.GONE
    }

    private fun validateAccountContent( user : User){
         viewModel.loginNavigation()
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