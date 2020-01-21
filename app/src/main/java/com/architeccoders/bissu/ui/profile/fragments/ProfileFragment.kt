package com.architeccoders.bissu.ui.profile.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.architeccoders.bissu.R
import com.architeccoders.bissu.session
import com.architeccoders.bissu.ui.common.app
import com.architeccoders.bissu.ui.profile.ProfileViewModel
import com.architeccoders.bissu.ui.common.getViewModel
import com.architectcoders.data.repository.UserRepository
import com.architectcoders.data.source.LoginLocalDataSource
import com.architectcoders.domain.User
import com.architectcoders.usecases.GetAccount
import kotlinx.android.synthetic.main.view_profile.*
import kotlinx.coroutines.withContext


class ProfileFragment: Fragment() {
    private lateinit var viewModel: ProfileViewModel
//    val userTest = User(  username = "calyr",
//        password = "test",
//        firstName = "Roberto Carlos",
//        lastName = "Callisaya Mamani")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        viewModel = getViewModel {
////            ProfileViewModel(GetAccount(UserRepository(
////                RoomDataSource(activity!!.app.db),
////                LoginFirebaseDBDatasource(activity!!.app.firebaseDB)
////                )))
//        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.view_profile, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.model.observe( this, Observer(::updateUi))
        viewModel.doRefresh(session.userName)

        btn_edit_profile.setOnClickListener {
            viewModel.onProfileEditClicked(session.userName)
        }
    }

    private fun updateUi(model: ProfileViewModel.UiModel) {
        when ( model ) {
            is ProfileViewModel.UiModel.Navigation -> {
                val fragmentManager = activity!!.supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()
                val fragment = ProfileEditFragment()
                fragmentTransaction.replace(R.id.content_profile, fragment)
                fragmentTransaction.commit()

            }
            is ProfileViewModel.UiModel.Content -> {
                profile_username.text = model.user.username
                profile_name.text = model.user.firstName + model.user.lastName
            }
        }
    }
}