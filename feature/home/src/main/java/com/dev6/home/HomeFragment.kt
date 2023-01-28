package com.dev6.home
import androidx.fragment.app.Fragment
import com.dev6.core.base.BindingFragment
import com.dev6.home.databinding.FragmentHomeBinding
import com.dev6.home.fragment.MainHomeFragment
import com.dev6.home.fragment.MyPageFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    val mainHomeFragment: MainHomeFragment by lazy { MainHomeFragment() }
    val myPageFragment: MyPageFragment by lazy { MyPageFragment() }
    lateinit var selected: Fragment

    override fun initView() {
        super.initView()
    }

    override fun initViewModel() {
        super.initViewModel()
    }

    override fun initListener() {
        super.initListener()
    }

    override fun afterViewCreated() {
        super.afterViewCreated()
        replaceFragment(mainHomeFragment)
        binding.bottomNavigation.setOnItemSelectedListener{
            when (it.itemId) {
                R.id.mainHomeFragment -> {
                   selected = mainHomeFragment
                    replaceFragment(selected)
                }
                R.id.MypageFragment -> {
                   selected = myPageFragment
                    replaceFragment(selected)
                }
            }
            true
        }

    }
    private fun replaceFragment(fragment: Fragment) {
        childFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainer, fragment)
            commit()
        }
    }
}