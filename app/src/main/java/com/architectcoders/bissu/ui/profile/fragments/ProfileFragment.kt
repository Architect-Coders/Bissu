package com.architectcoders.bissu.ui.profile.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.architectcoders.bissu.R
import com.architectcoders.bissu.data.database.login.LoginDataSource as local
import com.architectcoders.bissu.data.server.login.LoginDatasource as remote
import com.architectcoders.bissu.ui.common.StringToBitMap
import com.architectcoders.bissu.ui.common.app
import com.architectcoders.bissu.ui.profile.ProfileViewModel
import com.architectcoders.bissu.ui.common.getViewModel
import com.architectcoders.bissu.ui.login.fragments.CreateAccountFragment
import com.architectcoders.data.repository.UserRepository
import com.architectcoders.usecases.GetAccount
import kotlinx.android.synthetic.main.view_profile.*


class ProfileFragment: Fragment() {
    private lateinit var viewModel: ProfileViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getViewModel {
            ProfileViewModel(GetAccount(UserRepository(
                local(activity!!.app.db),
                remote()
                )))
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.view_profile, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.model.observe( this, Observer(::updateUi))
        viewModel.doRefresh()

        btn_edit_profile.setOnClickListener {
            viewModel.onProfileEditClicked()
        }
    }

    private fun updateUi(model: ProfileViewModel.UiModel) {
        when ( model ) {
            is ProfileViewModel.UiModel.Navigation -> {
                val bundle = Bundle()
                bundle.putString("OPTION_FORM", getString(R.string.edit_profile_option_form))
                val fragmentManager = activity!!.supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()
                val fragment = CreateAccountFragment()
                fragment.arguments = bundle
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.replace(R.id.content_profile, fragment)
                fragmentTransaction.commit()

            }
            is ProfileViewModel.UiModel.Content -> {
                profile_username.text = model.user.username
                profile_name.text = model.user.firstName + model.user.lastName
                profile_email.text = model.user.email
                profile_image.setImageBitmap(StringToBitMap(model.user.photoUrl!!))
            }
        }
    }
}