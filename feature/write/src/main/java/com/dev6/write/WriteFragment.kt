package com.dev6.write


import android.app.Activity
import android.app.Dialog
import android.opengl.Visibility
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.NonNull
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import com.dev6.core.BaseBottomSheetDialogFragment
import com.dev6.core.enums.WriteType
import com.dev6.core.util.extension.repeatOnStarted
import com.dev6.domain.model.post.write.ChallengeWriteReq
import com.dev6.domain.model.post.write.PostWriteReq
import com.dev6.write.databinding.FragmentWriteBinding
import com.dev6.write.fragment.WriteBottomSheetFragment
import com.dev6.write.viewmodel.WriteViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip


class WriteFragment : BaseBottomSheetDialogFragment<FragmentWriteBinding>(R.layout.fragment_write) {
    val writeViewModel : WriteViewModel by activityViewModels()
    lateinit var bottomSheet : BottomSheetDialogFragment

    var challengeReviewType : String? = ""
    var writeType : String? = ""
    var contens = ""
    var challengeId = ""

    override fun initView() {
        writeType = arguments?.getString("writeType")

        if(this.tag == "CHALLENGE"){
            binding.challCateCl.visibility = View.VISIBLE
            writeViewModel.updateCateGoryValue(WriteType.CHALLENGE)
        }else{
            binding.challCateCl.visibility = View.GONE
        }


        writeViewModel.categoryLiveData.observe(viewLifecycleOwner){
            writeType = it.toString()
            when(it){
                WriteType.CHALLENGE ->{
                    writeType = "CHALLENGE"
                    binding.challCateCl.visibility = View.VISIBLE
                    binding.challengeChipGroup.check(R.id.feelChip)
                    Log.v("zzzzz" , "zzzzz3")
                }
                WriteType.SHARE ->{
                    writeType = "SHARE"
                    binding.challCateCl.visibility = View.GONE
                    Log.v("zzzzz" , "zzzzz4")
                }
                WriteType.ETC->{
                    writeType = "ETC"
                    binding.challCateCl.visibility = View.GONE
                    Log.v("zzzzz" , "zzzzz5")
                }
            }
        }

        writeViewModel.challengeId.observe(viewLifecycleOwner){
            challengeId = it
        }
    }


    override fun initListener() {
        binding.writeCate.setOnClickListener {
            bottomSheet =  WriteBottomSheetFragment()
            bottomSheet.show(parentFragmentManager , bottomSheet.tag)
        }
        binding.writeComplete.setOnClickListener {
            if(writeType == "CHALLENGE"){
                repeatOnStarted {
                    writeViewModel.challengeWrite(ChallengeWriteReq(
                        challengeId,
                        challengeReviewType = challengeReviewType ?: "FEELING",
                        contents = contens
                    ))
                }
                binding.writeContentEt.text.clear()
                this.dismiss()
            }else{
                repeatOnStarted {
                    writeViewModel.postWrite(PostWriteReq(
                        contents = contens,
                        postType = writeType ?: "SHARE",
                        postId = ""
                    ))
                }
                binding.writeContentEt.text.clear()
                this.dismiss()
            }
        }
        binding.closeIv.setOnClickListener {
            writeViewModel.initWriteData()
            this.dismiss()
        }
        binding.writeContentEt.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                contens = p0.toString()
                if(contens.isNotEmpty()){
                    binding.writeComplete.setTextColor(ContextCompat.getColor(requireContext(),
                        com.dev6.designsystem.R.color.g2))
                }else{
                    binding.writeComplete.setTextColor(ContextCompat.getColor(requireContext(),
                        com.dev6.designsystem.R.color.bg1))
                }
            }
        })


        binding.challengeChipGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.feelChip -> {
                    challengeReviewType = "FEELING"
                    Log.v("testst" , challengeReviewType!!)
                }
                R.id.DiffChip -> {
                    challengeReviewType = "DIFF"
                }
                R.id.tipChip -> {
                    challengeReviewType = "TIP"
                }
            }
        }
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setOnShowListener { dialogInterface ->
            val bottomSheetDialog =
                dialogInterface as BottomSheetDialog
            setupFullHeight(bottomSheetDialog)
        }
        return dialog
    }


    private fun setupFullHeight(bottomSheetDialog: BottomSheetDialog) {
        val bottomSheet =
            bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout
        val behavior: BottomSheetBehavior<*> = BottomSheetBehavior.from(bottomSheet)
        val layoutParams: ViewGroup.LayoutParams? = bottomSheet!!.layoutParams
        val windowHeight = getWindowHeight()
        if (layoutParams != null) {
            layoutParams.height = windowHeight
        }
        bottomSheet.layoutParams = layoutParams
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
        behavior.saveFlags = BottomSheetBehavior.SAVE_HIDEABLE

        behavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(@NonNull bottomSheet: View, newState: Int) {
                if (newState == BottomSheetBehavior.STATE_DRAGGING) {
                    behavior.state = BottomSheetBehavior.STATE_EXPANDED
                }
            }

            override fun onSlide(@NonNull bottomSheet: View, slideOffset: Float) {}
        })
    }

    private fun getWindowHeight(): Int {
        val displayMetrics = DisplayMetrics()
        (context as Activity?)!!.windowManager.getDefaultDisplay().getMetrics(displayMetrics)
        return displayMetrics.heightPixels
    }


}