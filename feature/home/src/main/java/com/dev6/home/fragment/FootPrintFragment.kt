package com.dev6.home.fragment
import android.util.Log
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dev6.common.uistate.UiState
import com.dev6.core.BindingFragment
import com.dev6.core.util.extension.repeatOnStarted
import com.dev6.home.R
import com.dev6.home.adapter.FootPrintAdapter
import com.dev6.home.databinding.FragmentFootPrintBinding
import com.dev6.home.viewmodel.MyPageViewModel


class FootPrintFragment : BindingFragment<FragmentFootPrintBinding>(R.layout.fragment_foot_print) {
    private val myPageViewModel: MyPageViewModel by activityViewModels()
    lateinit var footprintRc : RecyclerView
    lateinit var footprintRcAdapter: FootPrintAdapter
    var page = 0

    override fun initView() {
        super.initView()
        footprintRc = binding.footprintRc
        footprintRcAdapter = FootPrintAdapter({},{
            page+=1
            repeatOnStarted {
                myPageViewModel.getMyFootPrintList(page,10)
            }
        })
        footprintRc.apply {
            adapter = footprintRcAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

    }

    override fun initViewModel() {
        super.initViewModel()
        repeatOnStarted {
            myPageViewModel.getMyFootPrintList(0,10)
        }

    }

    override fun initListener() {
        super.initListener()
    }

    override fun afterViewCreated() {
        super.afterViewCreated()
        repeatOnStarted {
            myPageViewModel.myPageFlow.collect{event->
                when (event) {
                    is MyPageViewModel.MyPageEvent.GetMyFootPrintList ->{
                        when(event.uiState){
                            is UiState.Success -> {
                                footprintRcAdapter.submitList(event.uiState.data.content)
                            }
                            is UiState.Loding -> {

                            }
                            is UiState.Error -> {
                                Log.v("MyPage Error", event.toString())
                            }
                        }
                    }
                    else -> {

                    }
                }
            }
        }
    }
}