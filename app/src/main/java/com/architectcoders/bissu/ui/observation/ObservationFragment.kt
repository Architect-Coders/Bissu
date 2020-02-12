package com.architectcoders.bissu.ui.observation

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.architectcoders.bissu.ui.book.BookDetailViewModel
import com.architectcoders.domain.Book
import kotlinx.android.synthetic.main.fragment_observation.*


class ObservationFragment : Fragment(), View.OnClickListener {

    private var book: Book? = null
    private lateinit var bookId: String

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

    private lateinit var viewModel: ObservationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            arguments?.let {
                bookId = it.getString(BOOK_ID, "")
            }
        } else {
            savedInstanceState.let {
                bookId = it.getString(BOOK_ID, "")
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_observation, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ObservationViewModel::class.java)

        viewModel.getBook(bookId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.model.observe(viewLifecycleOwner, Observer(::updateUi))
    }

    private fun setupPageSpinner() {
        val maxpages = book.pages.toInt()

        val dataAdapter: ArrayAdapter<String> = ArrayAdapter(
            context, R.layout.simple_spinner_item, arrayOf(1..maxpages).map { it.toString() }
        )

        observationfragment_pagetitle.adapter = dataAdapter
    }

    private fun updateUi(model: ObservationViewModel.UiModel?) {
        when (model) {
            is ObservationViewModel.UiModel.Loading -> progressVisibility(model.value)
            is ObservationViewModel.UiModel.Content -> observationUpdated(model.value)
            is ObservationViewModel.UiModel.ContentBook -> updateBookUi(model.book)
        }
    }

    private fun updateBookUi(book: Book?) {
        this.book = book
        setupPageSpinner()
    }

    private fun progressVisibility(value: Boolean) {
        observationfragment_progress.visibility = if (value) View.VISIBLE else View.GONE
    }

    private fun observationUpdated(value: Boolean) {

    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.observationfragment_create -> viewModel.createObservation()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.apply {
            BOOK_ID to bookId
        }
    }
}
