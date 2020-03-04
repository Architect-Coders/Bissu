package com.architectcoders.bissu.ui.book

import android.os.Bundle
import android.view.*
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.architectcoders.bissu.R
import com.architectcoders.bissu.data.database.book.BookDataSource
import com.architectcoders.bissu.data.database.observation.ObservationDataSource
import com.architectcoders.bissu.data.server.book.BookDatasource
import com.architectcoders.bissu.data.server.observation.ObservationDatasource
import com.architectcoders.bissu.ui.book.BookDetailViewModel.UiModel
import com.architectcoders.bissu.ui.common.app
import com.architectcoders.bissu.ui.common.getViewModel
import com.architectcoders.bissu.ui.observation.ObservationFragment
import com.architectcoders.data.repository.BookRepository
import com.architectcoders.data.repository.ObservationRepository
import com.architectcoders.domain.Book
import com.architectcoders.domain.Observation
import com.architectcoders.usecases.GetBook
import com.architectcoders.usecases.GetObservations
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_book_detail.*
import org.koin.androidx.scope.currentScope
import org.koin.androidx.viewmodel.ext.android.viewModel

class BookDetailFragment : Fragment() {

    private var book: Book? = null
    private lateinit var bookId: String
    private val observationAdapter by lazy { ObservationAdapter() }

    companion object {
        private const val BOOK_ID = "bookId"

        fun newInstance(bookId: String): BookDetailFragment {
            val fragment = BookDetailFragment()
            fragment.arguments = bundleOf(
                BOOK_ID to bookId
            )
            return fragment
        }
    }

    private val viewModel: BookDetailViewModel by currentScope.viewModel(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true);

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

    private fun initializeAdapter() {
        bookdetail_observations.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = observationAdapter
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_book_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.model.observe(viewLifecycleOwner, Observer(::updateUi))
        initializeAdapter()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.getBook(bookId)
        viewModel.fetchObservations(bookId)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_book_detail, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val book = this.book

        if(book == null){
            // TODO unavailable function
        }else{
            val fragmentManager = activity!!.supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            val fragment = ObservationFragment.newInstance(book.id)
            fragmentTransaction.replace(R.id.content_main, fragment, fragment.tag)
            fragmentTransaction.commit()

        }

        return super.onOptionsItemSelected(item)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.apply {
            BOOK_ID to bookId
        }
    }

    private fun updateUi(model: UiModel?) {
        when (model) {
            is UiModel.Loading -> mainProgressVisibility(model.value)
            is UiModel.Content -> updateBookUi(model.book)
            is UiModel.LoadingObservations -> observationProgressVisibility(model.value)
            is UiModel.Observations -> processObservations(model.observations)
        }
    }

    private fun processObservations(observations: ArrayList<Observation>) {
        observationAdapter.submitList(observations.map { ObservationItem(it) })
        bookdetail_observations.visibility =
            if (observationAdapter.itemCount > 0) View.VISIBLE else View.GONE
    }

    private fun observationProgressVisibility(value: Boolean) {
        bookdetail_observationsprogress.visibility = if (value) View.VISIBLE else View.GONE
    }

    private fun mainProgressVisibility(value: Boolean) {
        bookdetailfragment_progress.visibility = if (value) View.VISIBLE else View.GONE
    }

    private fun updateBookUi(book: Book?) {
        this.book = book

        if (book != null) {
            if (!book.photoUrl.isNullOrEmpty()) {
                Glide
                    .with(this)
                    .load(book.photoUrl)
                    .centerCrop()
                    .into(bookdetail_bookimg)
            }

            bookdetail_booktitle.text = book.title
            bookdetail_bookpages.text = book.pages
            bookdetail_bookauthor.text = book.author
            bookdetail_bookeditorial.text = book.editorial
            bookdetail_bookcategory.text = book.category?.name
            bookdetail_bookdescription.text = book.description
        }
    }
}
