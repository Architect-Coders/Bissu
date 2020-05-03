package com.architectcoders.bissu.ui.profile.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.architectcoders.bissu.R
import com.architectcoders.bissu.ui.common.closeSession
import com.architectcoders.bissu.ui.profile.ProfileActivity
import com.architectcoders.bissu.ui.profile.ProfileActivity.Companion.CHANGE_PASSWORD
import com.architectcoders.bissu.ui.profile.ProfileActivity.Companion.NAVIGATION
import com.architectcoders.bissu.ui.profile.ProfileActivity.Companion.UPDATE_CCOUNT
import com.architectcoders.bissu.ui.profile.viewmodels.ProfileViewModel
import kotlinx.android.synthetic.main.profile_home_profile.*
import org.koin.androidx.scope.currentScope
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.androidx.viewmodel.scope.viewModel


class ProfileFragment : Fragment() {

    companion object {
        fun newInstance() = ProfileFragment()
    }
    private val viewModel: ProfileViewModel by lifecycleScope.viewModel(this)

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.profile_home_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.model.observe(viewLifecycleOwner, Observer(::updateUi))

        edit_profile_layout.setOnClickListener {
            viewModel.updateAccountClicked()
        }
        change_password_layout.setOnClickListener {
            viewModel.changePasswordClicked()
        }
        log_out_layout.setOnClickListener {
            viewModel.logOutClicked()
        }
    }

    private fun updateUi(model: ProfileViewModel.UiModel) {
        when (model) {
            is ProfileViewModel.UiModel.UpdateAccountNavigation -> navigateToUpdateAccountFragment()
            is ProfileViewModel.UiModel.ChangePasswordNavigation -> navigateToChangePasswordFragment()
            is ProfileViewModel.UiModel.CloseSession -> logOut()
        }
    }

    private fun navigateToUpdateAccountFragment(){
        val intent = Intent(context, ProfileActivity::class.java)
        intent.putExtra(NAVIGATION,UPDATE_CCOUNT )
        startActivity(intent)
    }
    private fun navigateToChangePasswordFragment(){
        val intent = Intent(context, ProfileActivity::class.java)
        intent.putExtra(NAVIGATION,CHANGE_PASSWORD )
        startActivity(intent)
    }

    private fun logOut(){
        viewModel.closeSession(activity!!);
    }
}