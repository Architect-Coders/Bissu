package com.architectcoders.bissu.ui.profile.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.architectcoders.bissu.R
import com.architectcoders.bissu.ui.common.closeSession
import com.architectcoders.bissu.ui.common.showAlertDialog
import com.architectcoders.bissu.ui.profile.ChangePasswordViewModel
import com.architectcoders.domain.entities.User
import kotlinx.android.synthetic.main.profile_change_password.*
import org.koin.androidx.scope.currentScope
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChangePasswordFragment : Fragment() {

    private lateinit var currentUser: User

    private val viewModel: ChangePasswordViewModel by currentScope.viewModel(this)

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.profile_change_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.model.observe(viewLifecycleOwner, Observer(::updateUi))

        change_password_button.setOnClickListener {
            changePassword()
        }

        viewModel.getCurrentUser()
    }

    private fun updateUi(model: ChangePasswordViewModel.UiModel) {
        progress_bar_view.visibility = if (model is ChangePasswordViewModel.UiModel.Loading) View.VISIBLE else View.GONE
        when (model) {
            is ChangePasswordViewModel.UiModel.ContentEdit -> if (model.user == null) closeSession(activity!!) else setCurrentUser(model.user)
            is ChangePasswordViewModel.UiModel.Content -> validateUpdateAccountContent(model.status)
            is ChangePasswordViewModel.UiModel.NavigationToHome ->  navigationToHome()
        }
    }

    private fun  navigationToHome(){
        closeSession(activity!!)
    }

    private fun validateUpdateAccountContent( succes : Boolean){
        if (succes) viewModel.navigateToProfile() else context?.showAlertDialog("User not registered")
    }

    private fun setCurrentUser(user : User){
        currentUser = user
    }

    private fun changePassword(){
        if (
            viewModel.validatePassword(context!!, password_edit_text, password_input_layout) &&
            viewModel.validateRepeatPassword(context!!,repeat_password_edit_text, repeat_password_input_layout) &&
            viewModel.validateInputPassword(context!!,password_edit_text, repeat_password_edit_text,repeat_password_input_layout)){
            viewModel.updateAccount(currentUser, password = password_edit_text.text.toString())
        }
    }

}