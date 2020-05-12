package com.architectcoders.bissu.profile.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.architectcoders.bissu.R
import com.architectcoders.bissu.common.showAlertDialog
import com.architectcoders.bissu.profile.viewmodels.ChangePasswordViewModel
import com.architectcoders.domain.entities.User
import kotlinx.android.synthetic.main.profile_change_password.*
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel

class ChangePasswordFragment : Fragment() {

    private lateinit var currentUser: User

    private val viewModel: ChangePasswordViewModel by lifecycleScope.viewModel(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
        progress_bar_view.visibility =
            if (model is ChangePasswordViewModel.UiModel.Loading) View.VISIBLE else View.GONE
        when (model) {
            is ChangePasswordViewModel.UiModel.UserSessionContent -> setCurrentUser(model.user)
            is ChangePasswordViewModel.UiModel.ChangePasswordContent -> validateUpdateAccountContent(model.user)
            is ChangePasswordViewModel.UiModel.NavigationToHome -> navigationToHome()
        }
    }

    private fun navigationToHome() {
        viewModel.closeSession(activity!!)
    }

    private fun validateUpdateAccountContent(user: User) {
         viewModel.navigateToHome()
    }

    private fun setCurrentUser(user: User) {
        currentUser = user
    }

    private fun changePassword() {
        if (
            viewModel.validatePassword(context!!, password_edit_text, password_input_layout) &&
            viewModel.validateRepeatPassword(
                context!!,
                repeat_password_edit_text,
                repeat_password_input_layout
            ) &&
            viewModel.validateInputPassword(
                context!!,
                password_edit_text,
                repeat_password_edit_text,
                repeat_password_input_layout
            )
        ) {
            viewModel.updateAccount(
                User(
                    id = currentUser.id,
                    username = currentUser.username,
                    password = password_edit_text.text.toString(),
                    email = currentUser.email,
                    firstName = currentUser.firstName,
                    lastName = currentUser.lastName,
                    photoUrl = currentUser.photoUrl,
                    categories = currentUser.categories
                    )

            )
        }
    }

}