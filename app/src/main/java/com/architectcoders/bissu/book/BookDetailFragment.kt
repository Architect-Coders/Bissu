package com.architectcoders.bissu.book

import android.os.Bundle
import android.view.*
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.architectcoders.bissu.R
import com.architectcoders.bissu.book.BookDetailViewModel.UiModel
import com.architectcoders.bissu.observation.ObservationFragment
import com.architectcoders.domain.entities.Book
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_book_detail.*
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel

class BookDetailFragment : Fragment() {

    private lateinit var book: Book
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            bookId = it.getString(BOOK_ID, "")
        }
    }

    private val viewModel: BookDetailViewModel by lifecycleScope.viewModel(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_book_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.model.observe(viewLifecycleOwner, Observer(::updateUi))
       // initializeAdapter()
        create_observation.setOnClickListener {
            navigateToNewObservation();
        }
        viewModel.getBook(bookId)
       // viewModel.fetchObservations(bookId)
    }

    /*private fun initializeAdapter() {
        observations_recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = observationAdapter}
    } */

    private fun navigateToNewObservation(){
        val fragmentManager = activity!!.supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val fragment = ObservationFragment.newInstance(bookId)
        fragmentTransaction.replace(R.id.content_main, fragment)
        fragmentTransaction.addToBackStack(fragment.tag)
        fragmentTransaction.commit()
    }

    private fun updateUi(model: UiModel?) {
        when (model) {
            is UiModel.Loading -> mainProgressVisibility(model.value)
            is UiModel.Content -> updateBookUi(model.book)
            is UiModel.LoadingObservations -> mainProgressVisibility(model.value)
          //  is UiModel.Observations -> processObservations(model.observations)
        }
    }

   /* private fun processObservations(observations: List<Observation>) {
        observationAdapter.submitList(observations.map { ObservationItem(it) })
        observations_recycler_view.visibility = if (observationAdapter.itemCount > 0) View.VISIBLE else View.GONE
    } */
    private fun mainProgressVisibility(value: Boolean) {
        progress_bar_layout.visibility = if (value) View.VISIBLE else View.GONE
    }

    private fun updateBookUi(book: Book?) {
            if (book?.photoUrl.isNullOrEmpty()) {
                Glide.with(this).load(book?.photoUrl).centerCrop().into(bookdetail_bookimg)
            }else{
                bookdetail_bookimg.setImageResource(R.mipmap.ic_launcher)
            }
            book_title_text.text = book?.title
            book_author_text.text = book?.author
            book_description_text.text = book?.description
    }
}
