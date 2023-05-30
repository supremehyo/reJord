package com.dev6.home.bottomsheet
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.dev6.common.uistate.UiState
import com.dev6.core.BaseBottomSheetDialogFragment
import com.dev6.core.enums.WriteType
import com.dev6.core.util.extension.repeatOnStarted
import com.dev6.domain.model.challenge.ChallengeReviewResult
import com.dev6.domain.model.post.read.Content
import com.dev6.home.databinding.FragmentOptionBottomSheetBinding
import com.dev6.write.R
import com.dev6.write.WriteFragment
import com.dev6.write.fragment.ChallengeEditFragment
import com.dev6.write.fragment.PostEditFragment
import com.dev6.write.viewmodel.WriteViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.cancel


class OptionBottomSheetFragment : BaseBottomSheetDialogFragment<FragmentOptionBottomSheetBinding>(
    com.dev6.home.R.layout.fragment_option_bottom_sheet) {
    val writeViewModel : WriteViewModel by activityViewModels()
    lateinit var bottomSheet : BottomSheetDialogFragment
    lateinit var challengeReviewResult : ChallengeReviewResult
    lateinit var content : Content
    var isChallenge = false

    override fun initView() {
        isChallenge =  arguments?.getString("type") == "CHALLENGE"
        if(isChallenge){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                 requireArguments().getSerializable("data", ChallengeReviewResult::class.java)?.run {
                     challengeReviewResult = this
                 }
            } else {
                (requireArguments().getSerializable("data") as? ChallengeReviewResult)?.run {
                    challengeReviewResult = this
                }
            }
        }else{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                requireArguments().getSerializable("data", Content::class.java)?.run {
                    content = this
                }
            } else {
                (requireArguments().getSerializable("data") as? Content)?.run {
                    content = this
                }
            }
        }

    }
    //TODO 수정 삭제 연동
    override fun initListener() {
        binding.editCl.setOnClickListener {
            dismiss()
            if(isChallenge){
                bottomSheet =  ChallengeEditFragment()
                val args = Bundle()
                args.putSerializable("data", challengeReviewResult)
                bottomSheet.arguments = args
                bottomSheet.show(parentFragmentManager,"CHALLENGE")
            }else{
                bottomSheet =  PostEditFragment()
                val args = Bundle()
                args.putSerializable("data", content)
                bottomSheet.arguments = args
                bottomSheet.show(parentFragmentManager,"POST")
            }
        }

        binding.deleteCl.setOnClickListener {
            if(isChallenge){
                bottomSheet =  DeleteSheetFragment()
                val args = Bundle()
                args.putSerializable("data", challengeReviewResult)
                bottomSheet.arguments = args
                bottomSheet.show(parentFragmentManager,"CHALLENGE")
            }else{
                bottomSheet =  DeleteSheetFragment()
                val args = Bundle()
                args.putSerializable("data", content)
                bottomSheet.arguments = args
                bottomSheet.show(parentFragmentManager,"POST")
                //삭제
                //writeViewModel.deletePost(content.postId)
            }
        }
    }
}