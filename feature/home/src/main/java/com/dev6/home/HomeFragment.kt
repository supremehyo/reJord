package com.dev6.home
import android.app.Activity
import android.util.DisplayMetrics
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.dev6.core.BindingFragment
import com.dev6.core.enums.WriteType
import com.dev6.home.databinding.FragmentHomeBinding
import com.dev6.home.fragment.MainHomeFragment
import com.dev6.home.fragment.MyPageFragment
import com.dev6.home.viewmodel.BoardViewModel
import com.dev6.write.WriteFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    val boardViewModel : BoardViewModel by activityViewModels()
    val mainHomeFragment: MainHomeFragment by lazy { MainHomeFragment() }
    val myPageFragment: MyPageFragment by lazy { MyPageFragment() }
    lateinit var selected: Fragment
    var writeType : WriteType = WriteType.CHALLENGE
    lateinit var bottomSheet : BottomSheetDialogFragment

    override fun initView() {
        super.initView()
        bottomSheet =  WriteFragment()
    }

    override fun initViewModel() {
        super.initViewModel()
        boardViewModel.boardTabTypeFlag.observe(viewLifecycleOwner){
                writeType = it
        }
    }

    override fun initListener() {
        super.initListener()
        binding.writeButtonIv.setOnClickListener {

            bottomSheet.show(parentFragmentManager,writeType.toString())

        }
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