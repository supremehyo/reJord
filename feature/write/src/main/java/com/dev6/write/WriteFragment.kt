package com.dev6.write
import android.text.Editable
import android.text.TextWatcher
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import com.dev6.core.base.BindingFragment
import com.dev6.write.databinding.FragmentWriteBinding
import com.dev6.write.viewmodel.WriteViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class WriteFragment : BindingFragment<FragmentWriteBinding>(R.layout.fragment_write) {
    val writeFragment : WriteViewModel by activityViewModels()
    lateinit var bottomSheet : BottomSheetDialogFragment
    var writeType = ""
    override fun initView() {
        super.initView()
    }

    override fun initViewModel() {
        super.initViewModel()
        writeFragment.categoryLiveData.observe(viewLifecycleOwner){
            writeType = it.toString()
        }
    }

    override fun initListener() {
        super.initListener()
        binding.writeCate.setOnClickListener {
            bottomSheet.show(parentFragmentManager , bottomSheet.tag)
        }

        binding.writeComplete.setOnClickListener {

        }

        binding.writeContentEt.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                binding.writeComplete.setTextColor(ContextCompat.getColor(requireContext(),
                    com.dev6.designsystem.R.color.g2))
            }
        })
    }

    override fun afterViewCreated() {
        super.afterViewCreated()
    }
}