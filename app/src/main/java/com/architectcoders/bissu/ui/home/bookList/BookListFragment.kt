
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.architectcoders.bissu.R
import com.architectcoders.bissu.ui.book.BookDetailActivity
import com.architectcoders.bissu.ui.book.activities.CreateBookActivity
import com.architectcoders.bissu.ui.common.base.adapters.AdapterClick
import com.architectcoders.bissu.ui.common.base.adapters.AdapterListener
import com.architectcoders.bissu.ui.home.bookList.BookAdapter
import com.architectcoders.bissu.ui.home.bookList.BookItem
import com.architectcoders.bissu.ui.home.bookList.BookListViewModel
import com.architectcoders.bissu.ui.home.bookList.BookListViewModel.UiModel
import com.architectcoders.domain.entities.Book
import kotlinx.android.synthetic.main.fragment_book_list.*
import org.koin.androidx.scope.currentScope
import org.koin.androidx.viewmodel.ext.android.viewModel

class BookListFragment : Fragment(), AdapterListener {

    private val bookAdapter by lazy { BookAdapter(this) }

    companion object {
        fun newInstance() = BookListFragment()
    }

    private val viewModel: BookListViewModel by currentScope.viewModel(this)

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        return inflater.inflate(R.layout.fragment_book_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.model.observe(viewLifecycleOwner, Observer(::updateUi))

        vRefreshLayout.setOnRefreshListener {
            viewModel.refreshBooks()
        }

        fab.setOnClickListener {
            viewModel.addBookClicked();
        }

        initializeAdapter()

        viewModel.getBooks()
    }

    private fun initializeAdapter() {
        booklistfragment_books.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = bookAdapter
        }
    }

    private fun updateUi(model: UiModel?) {
        when (model) {
            is UiModel.Refresh -> swipeRefresh(model.value)
            is UiModel.Loading -> progressVisibility(model.value)
            is UiModel.Content -> processBooks(model.books)
            is UiModel.Navigation -> {
                val intent = Intent(activity, CreateBookActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun swipeRefresh(value :Boolean){
        vRefreshLayout.isRefreshing = if (value) true else false;
    }

    private fun progressVisibility(value: Boolean) {
        booklistfragment_progress.visibility = if (value) View.VISIBLE else View.GONE
    }

    private fun processBooks(books: List<Book>) {
        bookAdapter.submitList(books.map { BookItem(it) })
        booklistfragment_books.visibility =
            if (bookAdapter.itemCount > 0) View.VISIBLE else View.GONE
    }

    override fun listen(click: AdapterClick?) {
        when (click) {
            is BookItem -> {
                val intent = Intent(activity, BookDetailActivity::class.java)
                intent.putExtra(BookDetailActivity.BOOK_ID, click.id)

                startActivity(intent)
            }
        }
    }
}
