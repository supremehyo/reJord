package com.dev6.rejord
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.dev6.core.base.BindingFragment
import com.dev6.rejord.databinding.FragmentMainBinding

class MainFragment : BindingFragment<FragmentMainBinding>(R.layout.fragment_main) {
    override fun initView() {
        super.initView()
    }

    override fun initViewModel() {
        super.initViewModel()
    }

    override fun initListener() {
        super.initListener()
        binding.goJoin.setOnClickListener {
            findNavController().navigate(R.id.action_random_fragment_to_settings_nav_graph)
        }

        binding.gologin.setOnClickListener {
            findNavController().navigate(R.id.action_Mainfragment_to_login_graph4)
        }
    }

    override fun afterViewCreated() {
        super.afterViewCreated()
    }
}

















/*
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

 */