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
import com.architectcoders.bissu.ui.common.BitMapToString
import com.architectcoders.bissu.ui.common.StringToBitMap
import com.architectcoders.bissu.ui.home.profile.ProfileActivity
import com.architectcoders.bissu.ui.login.LoginCreateAccountViewModel
import com.architectcoders.domain.entities.User
import kotlinx.android.synthetic.main.login_create_account_view.*
import org.koin.androidx.scope.currentScope
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
 * Created by Anibal Cortez on 2019-12-16.
 */
class CreateAccountFragment : Fragment() {

    val REQUEST_IMAGE_CAPTURE = 1
    var USER_ID_EDIT = ""

    companion object {
        fun newInstance() = CreateAccountFragment()
        fun newInstance(bundle: Bundle) = CreateAccountFragment().apply { arguments = bundle }
    }

    private val viewModel: LoginCreateAccountViewModel by currentScope.viewModel(this)

    private var isNew = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val texto = arguments?.getString("OPTION_FORM")
        texto?.let {
            this.isNew = !(texto == getString(R.string.edit_profile_option_form))
        }
        return inflater.inflate(R.layout.login_create_account_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.model.observe(viewLifecycleOwner, Observer(::updateUi))

        if (!this.isNew) {
            viewModel.onLoadEdit()
        }
        create_account_button.setOnClickListener {

            createAccount(
                username_edit_text?.text.toString(),
                email_edit_text.text?.toString(),
                first_name_edit_text.text?.toString(),
                last_name_edit_text.text?.toString(),
                password_edit_text.text?.toString(),
                repeat_password_edit_text.text?.toString(),
                this.isNew
            )
        }

        user_photo.setOnClickListener {
            dispatcheTakePictureIntent()
        }


    }

    private fun updateUi(model: LoginCreateAccountViewModel.UiModel) {
        progress_bar_view.visibility =
            if (model is LoginCreateAccountViewModel.UiModel.Loading) View.VISIBLE else View.GONE
        when (model) {
            is LoginCreateAccountViewModel.UiModel.Content -> {
                if (model.status) {
                    // go to next screen
                } else Toast.makeText(context, "User not registered", Toast.LENGTH_LONG).show()
            }
            is LoginCreateAccountViewModel.UiModel.ContentEdit -> {
                var bm: Bitmap? = null

                model.user.photoUrl?.let{
                    bm = StringToBitMap(it)
                }

                model.user.apply {
                    create_account_title_text.setText(getString(R.string.edit_profile_title))
                    email_edit_text.setText(email)
                    username_edit_text.setText(username)
                    first_name_edit_text.setText(firstName)
                    last_name_edit_text.setText(lastName)
                    USER_ID_EDIT = id
                    if (bm == null) {
                        user_photo.setImageResource(R.drawable.icons8_editar_64)
                    } else {
                        user_photo.setImageBitmap(bm)
                    }

                    create_account_button.setText(getString(R.string.edit_profile_btn))

                }
            }
            is LoginCreateAccountViewModel.UiModel.NavigationProfile -> {
                val intent = Intent(activity, ProfileActivity::class.java)
                startActivity(intent)
            }
            is LoginCreateAccountViewModel.UiModel.NavigationLogin -> {
                val fragmentManager = activity!!.supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()
                val fragmentProfile = LoginFragment()
                fragmentTransaction.replace(R.id.content_main, fragmentProfile)
                fragmentTransaction.commit()
            }
        }
    }

    private fun createAccount(
        username: String?,
        email: String?,
        firstName: String?,
        lastName: String?,
        password: String?,
        repeatPassword: String?,
        userNew: Boolean = true
    ) {
        if (username.isNullOrEmpty() || firstName.isNullOrEmpty() || lastName.isNullOrEmpty()
            || password.isNullOrEmpty() || repeatPassword.isNullOrEmpty() || email.isNullOrEmpty()
        )
            Toast.makeText(context, "complete all values", Toast.LENGTH_LONG).show()
        else if (!password.equals(repeatPassword))
            Toast.makeText(context, "passwords must be the same", Toast.LENGTH_LONG).show()
        else {
            var bm: Bitmap? = null

                user_photo.getDrawable().let {
                    it?.let {
                        try {
                            bm = (it as BitmapDrawable).bitmap
                        } catch (e: Exception) {
                            bm = null
                        }

                    }
                }

            var userData = User(
                id = USER_ID_EDIT,
                username = username,
                email = email,
                firstName = firstName,
                lastName = lastName,
                photoUrl = BitMapToString(bm),
                categories = arrayListOf()
            )
            if (userNew)
                viewModel.createAccount(userData, password)
            else {
                viewModel.updateAccount(userData, password)
            }
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