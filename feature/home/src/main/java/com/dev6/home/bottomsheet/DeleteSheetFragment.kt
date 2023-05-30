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
import com.dev6.home.databinding.FragmentDeleteBottomSheetBinding
import com.dev6.home.databinding.FragmentOptionBottomSheetBinding
import com.dev6.write.R
import com.dev6.write.WriteFragment
import com.dev6.write.fragment.ChallengeEditFragment
import com.dev6.write.fragment.PostEditFragment
import com.dev6.write.viewmodel.WriteViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.cancel


class DeleteSheetFragment : BaseBottomSheetDialogFragment<FragmentDeleteBottomSheetBinding>(
    com.dev6.home.R.layout.fragment_delete_bottom_sheet) {
    val writeViewModel : WriteViewModel by activityViewModels()
    lateinit var challengeReviewResult : ChallengeReviewResult
    lateinit var content : Content
    var isChallenge = false

    override fun initView() {
        if(this.tag == "CHALLENGE"){
            isChallenge = true
            challengeReviewResult = arguments?.getString("data") as ChallengeReviewResult
            Log.v("삭제테스트",challengeReviewResult.title)
        }else{
            content =  arguments?.getString("data") as Content
            Log.v("삭제테스트",content.contents)
        }
    }

    override fun initListener() {
        binding.apply {
            deleteButton.setOnClickListener {
                if(isChallenge){
                    writeViewModel.challengeDelete(challengeReviewResult.challengeReviewId)
                }else{
                    writeViewModel.deletePost(content.postId)
                }
            }
            deleteCancelButton.setOnClickListener {
                dismiss()
            }
        }
    }
}