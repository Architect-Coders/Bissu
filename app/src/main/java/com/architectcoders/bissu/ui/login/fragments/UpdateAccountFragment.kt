package com.architectcoders.bissu.ui.login.fragments

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.architectcoders.bissu.R
import com.architectcoders.bissu.ui.common.toBase64
import com.architectcoders.bissu.ui.common.toBitmap
import com.architectcoders.bissu.ui.home.profile.ProfileActivity
import com.architectcoders.bissu.ui.login.UpdateAccountViewModel
import com.architectcoders.domain.entities.Category
import com.architectcoders.domain.entities.User
import kotlinx.android.synthetic.main.login_create_account_view.*
import org.koin.androidx.scope.currentScope
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by Anibal Cortez on 2020-03-05.
 */

class UpdateAccountFragment : Fragment() {

    val REQUEST_IMAGE_CAPTURE = 1

    private lateinit var user_id : String
    private lateinit var userCategories : List<Category>

    private val viewModel: UpdateAccountViewModel by currentScope.viewModel(this)

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.login_create_account_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.model.observe(viewLifecycleOwner, Observer(::updateUi))

        create_account_title_text.text = "Edit Account"

        create_account_button.text = "Create Account"

        create_account_button.setOnClickListener {
            updateAccount(
                username_edit_text?.text.toString(),
                email_edit_text.text?.toString(),
                first_name_edit_text.text?.toString(),
                last_name_edit_text.text?.toString(),
                password_edit_text.text?.toString(),
                repeat_password_edit_text.text?.toString()
                )
        }

        user_photo.setOnClickListener {
            dispatcheTakePictureIntent()
        }

        viewModel.getCurrentUser();
    }

    private fun updateUi(model: UpdateAccountViewModel.UiModel) {
        progress_bar_view.visibility = if (model is UpdateAccountViewModel.UiModel.Loading) View.VISIBLE else View.GONE
        when (model) {

            is UpdateAccountViewModel.UiModel.ContentEdit -> {

                var imageUrl: Bitmap? = null

                model.user?.let {
                    user_id = it.id
                    userCategories = it.categories
                    email_edit_text.setText(it.email)
                    username_edit_text.setText(it.username)
                    first_name_edit_text.setText(it.firstName)
                    last_name_edit_text.setText(it.lastName)
                    it.photoUrl?.let { image ->
                        imageUrl = image.toBitmap()
                    }
                    imageUrl?.let {bitmap ->
                        user_photo.setImageBitmap(bitmap)
                    }
                }
            }
            is UpdateAccountViewModel.UiModel.NavigationProfile -> {
                val intent = Intent(activity, ProfileActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun updateAccount( username: String?, email: String?, firstName: String?, lastName: String?,
                               password: String?,repeatPassword: String?) {
        if (username.isNullOrEmpty() || firstName.isNullOrEmpty() || lastName.isNullOrEmpty()
            || password.isNullOrEmpty() || repeatPassword.isNullOrEmpty() || email.isNullOrEmpty()
        )
            Toast.makeText(context, "complete all values", Toast.LENGTH_LONG).show()
        else if (!password.equals(repeatPassword))
            Toast.makeText(context, "passwords must be the same", Toast.LENGTH_LONG).show()
        else {
            var imageUrl : String? = null
            user_photo.getDrawable()?.let {
                imageUrl = (it as BitmapDrawable).bitmap.toBase64()
            }

            viewModel.updateAccount( User(
                id = user_id,
                username = username,
                email = email,
                firstName = firstName,
                lastName = lastName,
                photoUrl = imageUrl,
                categories = userCategories
            ), password)

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
            val imageBitmap = data!!.extras.get("data") as Bitmap
            user_photo.setImageBitmap(imageBitmap)
        }
    }
}