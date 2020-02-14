import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import com.architectcoders.bissu.ui.home.myObservations.MyObservationsFragment
import com.architectcoders.bissu.ui.home.profile.fragments.ProfileFragment

@SuppressLint("WrongConstant")
class MainPagerAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(
    fragmentManager,
    FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return BookListFragment.newInstance()
            1 -> return MyObservationsFragment.newInstance()
            2 -> return ProfileFragment.newInstance()
        }
        return BookListFragment.newInstance()
    }

    override fun getCount(): Int {
        return 3
    }
}