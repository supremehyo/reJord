package com.dev6.rejord
import android.util.Log
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.dev6.core.BindingFragment
import com.dev6.rejord.databinding.FragmentMainBinding

class MainFragment : BindingFragment<FragmentMainBinding>(R.layout.fragment_main) {
    override fun initView() {
        super.initView()
        var refreshToken = Application.prefs.getRefreshToken()
        var accessToken =Application.prefs.getToken()
        if(refreshToken != "" && accessToken != ""){
            Log.v("토큰있어요" , "있음")
            findNavController().navigate(R.id.action_initFragment_to_home_graph)
        }
    }

    override fun initViewModel() {
        super.initViewModel()
    }

    override fun initListener() {
        super.initListener()
        binding.goJoin.setOnClickListener {
            findNavController().navigate(R.id.action_initFragment_to_join_graph)
        }

        binding.gologin.setOnClickListener {
            findNavController().navigate(R.id.action_initFragment_to_login_graph)
        }

        binding.goMain.setOnClickListener {
            findNavController().navigate(R.id.action_initFragment_to_home_graph)
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