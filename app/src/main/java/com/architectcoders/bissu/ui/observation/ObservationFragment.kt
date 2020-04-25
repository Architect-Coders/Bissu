package com.architectcoders.bissu.ui.observation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.architectcoders.bissu.R
import com.architectcoders.bissu.ui.common.showToast
import com.architectcoders.bissu.ui.observation.ObservationViewModel.UiModel
import com.architectcoders.bissu.ui.observation.ObservationViewModel.UiModel.*
import com.architectcoders.domain.entities.Book
import kotlinx.android.synthetic.main.fragment_observation.*
import org.koin.androidx.scope.currentScope
import org.koin.androidx.viewmodel.ext.android.viewModel

class ObservationFragment : Fragment() {

    private var book: Book? = null
    private lateinit var bookId: String

    companion object {
        private const val BOOK_ID = "bookId"

        fun newInstance(bookId: String): ObservationFragment {
            val fragment = ObservationFragment()
            fragment.arguments = bundleOf(
                BOOK_ID to bookId)
            return fragment
        }
    }

    private val viewModel: ObservationViewModel by currentScope.viewModel(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            arguments?.let {
                if (it.containsKey(BOOK_ID)){
                    bookId = it.getString(BOOK_ID )
                }
            }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_observation, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.model.observe(viewLifecycleOwner, Observer(::updateUi))
        create_observation_button.setOnClickListener{
            createObservation();
        }

        viewModel.getBook(bookId)
    }

    private fun updateUi(model: UiModel?) {
        when (model) {
            is Loading -> progressVisibility(model.value)
            is ContentBook -> updateBookUi(model.book)
            is CreateAccountError -> createAccountError()
            is NavigateToHome -> activity?.onBackPressed()
        }
    }

    private fun createAccountError(){
        context?.showToast(R.string.observationfragment_cantcreateobservation)
    }

    private fun updateBookUi(book: Book?) {
        this.book = book
    }

    private fun progressVisibility(value: Boolean) {
        progress_bar_layout.visibility = if (value) View.VISIBLE else View.GONE
    }


    private fun createObservation(){
        if(viewModel.validatePages(context!!, pages_edit_text, pages_text_input) &&
            viewModel.validateObservation(context!!,observation_edit_text,observation_text_input)){
            book?.let {
                viewModel.onCreateClicked(it, pages_edit_text.text.toString(),observation_edit_text.text.toString() )
            }
        }

    }
}
