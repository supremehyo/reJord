package com.dev6.home
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dev6.home.fragment.BoardFragment
import com.dev6.home.fragment.ChallengeFragment

class HomeContentPagerAdapter(fragmentActivity: FragmentActivity):
    FragmentStateAdapter(fragmentActivity) {

    val fragmentList = listOf<Fragment>(ChallengeFragment(), BoardFragment())

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}