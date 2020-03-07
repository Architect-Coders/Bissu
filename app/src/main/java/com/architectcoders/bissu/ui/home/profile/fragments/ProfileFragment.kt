package com.architectcoders.bissu.ui.home.profile.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.architectcoders.bissu.R
import com.architectcoders.bissu.ui.common.toBitmap
import com.architectcoders.bissu.ui.home.profile.ProfileActivity
import com.architectcoders.bissu.ui.home.profile.ProfileViewModel
import kotlinx.android.synthetic.main.view_profile.*
import org.koin.androidx.scope.currentScope
import org.koin.androidx.viewmodel.ext.android.viewModel


class ProfileFragment : Fragment() {

    private val viewModel: ProfileViewModel by currentScope.viewModel(this)

    companion object {
        fun newInstance() = ProfileFragment()
    }

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.view_profile, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.model.observe(viewLifecycleOwner, Observer(::updateUi))

        btn_edit_profile.setOnClickListener {
            viewModel.onProfileEditClicked()
        }

        viewModel.getAccount()
    }

    private fun updateUi(model: ProfileViewModel.UiModel) {
        when (model) {
            is ProfileViewModel.UiModel.Navigation -> {
                startActivity(Intent(context, ProfileActivity::class.java))
            }
            is ProfileViewModel.UiModel.Content -> {

                model.user?.let {
                    profile_username.text = it.username
                    profile_name.text = it.firstName + it.lastName
                    profile_email.text = model.user.email

                    it.photoUrl?.let { image ->
                        profile_image.setImageBitmap(image.toBitmap())
                    }
                }
            }
        }
    }
}