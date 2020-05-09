package com.architectcoders.bissu.profile.fragments

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.architectcoders.bissu.R
import com.architectcoders.bissu.common.showAlertDialog
import com.architectcoders.bissu.common.toBase64
import com.architectcoders.bissu.profile.viewmodels.UpdateAccountViewModel
import com.architectcoders.domain.entities.User
import kotlinx.android.synthetic.main.login_create_account.email_edit_text
import kotlinx.android.synthetic.main.login_create_account.email_input_layout
import kotlinx.android.synthetic.main.login_create_account.first_name_edit_text
import kotlinx.android.synthetic.main.login_create_account.first_name_imput_layout
import kotlinx.android.synthetic.main.login_create_account.last_name_edit_text
import kotlinx.android.synthetic.main.login_create_account.last_name_input_layout
import kotlinx.android.synthetic.main.login_create_account.progress_bar_view
import kotlinx.android.synthetic.main.login_create_account.user_photo
import kotlinx.android.synthetic.main.login_create_account.username_edit_text
import kotlinx.android.synthetic.main.login_create_account.username_input_layout
import kotlinx.android.synthetic.main.profile_update_account.*
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel

/**
 * Created by Anibal Cortez on 2020-03-05.
 */

class UpdateAccountFragment : Fragment() {

    val REQUEST_IMAGE_CAPTURE = 1

    private lateinit var currentUser: User
    private var photoUrl : Bitmap? = null
    private val viewModel: UpdateAccountViewModel by lifecycleScope.viewModel(this)

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.profile_update_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.model.observe(viewLifecycleOwner, Observer(::updateUi))

        user_photo.setOnClickListener {
            dispatcheTakePictureIntent()
        }

        update_account_button.setOnClickListener {
            updateAccount();
        }

        viewModel.getCurrentUser();
    }

    private fun updateUi(model: UpdateAccountViewModel.UiModel) {
        progress_bar_view.visibility = if (model is UpdateAccountViewModel.UiModel.Loading) View.VISIBLE else View.GONE
        when (model) {
            is UpdateAccountViewModel.UiModel.ContentEdit -> if (model.user == null) navigationToHome() else setCurrentUser(model.user)
            is UpdateAccountViewModel.UiModel.Content -> validateUpdateAccountContent(model.status)
            is UpdateAccountViewModel.UiModel.NavigationToHome ->  navigationToHome()
        }
    }

    private fun  navigationToHome(){
        viewModel.closeSession(activity!!)
    }

    private fun validateUpdateAccountContent( succes : Boolean){
        if (succes) viewModel.navigateToProfile() else context?.showAlertDialog("User not registered")
    }

    private fun setCurrentUser(user : User){
        currentUser = user
        email_edit_text.setText(user.email)
        username_edit_text.setText(user.username)
        first_name_edit_text.setText(user.firstName)
        last_name_edit_text.setText(user.lastName)
        user.photoUrl?.let { image ->
            // image.toBitmap()?.apply {  user_photo.setImageBitmap(this)}
        }
    }


    private fun updateAccount() {
      if(  viewModel.validateUsername(context!!, username_edit_text, username_input_layout) &&
                viewModel.validateEmail(context!!, email_edit_text, email_input_layout) &&
                viewModel.validateFirstName(context!!, first_name_edit_text, first_name_imput_layout) &&
                viewModel.validateLastName(context!!, last_name_edit_text, last_name_input_layout) ){

         val userPhoto : String? = if (photoUrl == null) currentUser.photoUrl  else photoUrl!!.toBase64()
          viewModel.updateAccount(
              currentUser,
              username_edit_text.text.toString(),
              email_edit_text.text.toString(),
              first_name_edit_text.text.toString(),
              last_name_edit_text.text.toString(),
              userPhoto
          )
      }
    }

    private fun dispatcheTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(activity!!.packageManager)?.also {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data!!.extras?.get("data") as Bitmap
            photoUrl = imageBitmap
           // user_photo.setImageBitmap(imageBitmap)
        }
    }
}