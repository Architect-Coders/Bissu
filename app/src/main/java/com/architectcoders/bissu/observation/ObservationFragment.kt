package com.architectcoders.bissu.observation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.architectcoders.bissu.R
import com.architectcoders.bissu.common.showToast
import com.architectcoders.bissu.observation.ObservationViewModel.UiModel
import com.architectcoders.bissu.observation.ObservationViewModel.UiModel.*
import com.architectcoders.domain.entities.Book
import com.architectcoders.domain.entities.User
import kotlinx.android.synthetic.main.fragment_observation.*
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel

class ObservationFragment : Fragment() {

    private lateinit var book: Book
    private lateinit var bookId: String
    private lateinit var sessionUser: User

    companion object {
        private const val BOOK_ID = "bookId"

        fun newInstance(bookId: String): ObservationFragment {
            val fragment = ObservationFragment()
            fragment.arguments = bundleOf(
                BOOK_ID to bookId
            )
            return fragment
        }
    }

    private val viewModel: ObservationViewModel by lifecycleScope.viewModel(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            if (it.containsKey(BOOK_ID)) {
                bookId = it.getString(BOOK_ID)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_observation, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.model.observe(viewLifecycleOwner, Observer(::updateUi))
        create_observation_button.setOnClickListener {
            getSessionUser();
        }
        viewModel.getBook(bookId)
    }

    private fun updateUi(model: UiModel?) {
        when (model) {
            is Loading -> progressVisibility(model.value)
            is BookContent -> updateBookUi(model.book)
            is UserContent -> sessionContent(model.user)
            is ServerError -> createAccountError()
            is CreateObservationSuccess -> navigateToHome()
            is NavigateToHome -> activity?.onBackPressed()
        }
    }

    private fun navigateToHome(){
        viewModel.navigateToHome()
    }
    private fun sessionContent(user: User) {
        sessionUser = user
        createObservation()
    }

    private fun createAccountError() {
        context?.showToast(R.string.observationfragment_cantcreateobservation)
    }

    private fun updateBookUi(book: Book) {
        this.book = book
    }

    private fun progressVisibility(value: Boolean) {
        progress_bar_layout.visibility = if (value) View.VISIBLE else View.GONE
    }


    private fun getSessionUser() {
        if (viewModel.validatePages(context!!, pages_edit_text, pages_text_input) &&
            viewModel.validateObservation(context!!, observation_edit_text, observation_text_input)
        ) {
            viewModel.onGetUserClicked()
        }
    }

    private fun createObservation() {
        viewModel.createObservation(
            sessionUser.id,
            book.id,
            observation_edit_text.text.toString(),
            pages_edit_text.text.toString()
        )
    }


}
