package com.dev6.home.adapter
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dev6.home.fragment.BoardFragment
import com.dev6.home.fragment.ChallengeFragment

class HomeContentPagerAdapter(fragment: Fragment):
    FragmentStateAdapter(fragment) {

    val fragmentList = listOf<Fragment>(ChallengeFragment(), BoardFragment())

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }
}