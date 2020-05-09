import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.architectcoders.bissu.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_main_pager.*


class MainPagerFragment : Fragment(), BottomNavigationView.OnNavigationItemSelectedListener {
    private lateinit var adapter: MainPagerAdapter

    companion object {
        fun newInstance() = MainPagerFragment()
    }

    private lateinit var viewModel: MainPagerViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main_pager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = MainPagerAdapter(childFragmentManager)

        vPager.adapter = adapter
        vPager.currentItem = 0
        vPager.offscreenPageLimit = 3

        vNavigation.setOnNavigationItemSelectedListener(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainPagerViewModel::class.java)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_books -> {
                vPager.currentItem = 0
            }
            R.id.action_yours -> {
                vPager.currentItem = 1
            }
            R.id.action_profile -> {
                vPager.currentItem = 2
            }
        }
        return true
    }
}
