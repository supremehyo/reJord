package com.dev6.home
import androidx.fragment.app.activityViewModels
import com.dev6.core.base.BindingFragment
import com.dev6.home.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val homeViewModel : HomeViewModel by activityViewModels()
    val viewPagerAdapter = HomeContentPagerAdapter(requireActivity())

    override fun initView() {
        super.initView()
        binding.pagerContent.adapter = viewPagerAdapter
    }

    override fun initViewModel() {
        super.initViewModel()
    }

    override fun initListener() {
        super.initListener()
    }

    override fun afterViewCreated() {
        super.afterViewCreated()
    }
}