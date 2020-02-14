package com.architectcoders.bissu.ui.observation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.architectcoders.bissu.R
import com.architectcoders.bissu.ui.observation.ObservationViewModel.UiModel
import com.architectcoders.bissu.ui.observation.ObservationViewModel.UiModel.*
import com.architectcoders.domain.Book
import kotlinx.android.synthetic.main.fragment_observation.*
import org.koin.androidx.scope.currentScope
import org.koin.androidx.viewmodel.ext.android.viewModel


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

    private val viewModel: ObservationViewModel by currentScope.viewModel(this)

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

        viewModel.model.observe(viewLifecycleOwner, Observer(::updateUi))
        viewModel.getBook(bookId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observationfragment_create.setOnClickListener(this)
    }

    private fun setupPageSpinner() {
        book?.pages?.toInt()?.let {
            val dataAdapter: ArrayAdapter<String> = ArrayAdapter(
                context,
                android.R.layout.simple_spinner_item,
                (1..it).toList().map { item -> item.toString() }
            )

            dataAdapter.insert("Select page", 0)

            observationfragment_pagenumber.adapter = dataAdapter
        }
    }

    private fun updateUi(model: UiModel?) {
        when (model) {
            is Loading -> progressVisibility(model.value)
            is Content -> observationUpdated(model.value)
            is ContentBook -> updateBookUi(model.book)
            is ValidateObservation -> validateObservation()
        }
    }

    private fun validateObservation() {
        if (observationfragment_pagenumber.selectedItemPosition == 0) {
            // TODO message select a page
        } else {
            val page = observationfragment_pagenumber.selectedItem.toString()
            val description = observationfragment_description.text
            viewModel.createObservation(book, description.toString(), page)
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
            R.id.observationfragment_create -> {
                viewModel.onCreateClicked()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.apply {
            BOOK_ID to bookId
        }
    }
}
