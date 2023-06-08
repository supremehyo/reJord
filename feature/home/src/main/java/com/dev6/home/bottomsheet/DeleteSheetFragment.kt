package com.dev6.home.bottomsheet
import android.os.Build
import android.util.Log
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.dev6.core.BaseBottomSheetDialogFragment
import com.dev6.domain.model.challenge.ChallengeReviewResult
import com.dev6.domain.model.post.read.Content
import com.dev6.home.databinding.FragmentDeleteBottomSheetBinding
import com.dev6.home.viewmodel.MyPageViewModel
import kotlinx.coroutines.launch


class DeleteSheetFragment : BaseBottomSheetDialogFragment<FragmentDeleteBottomSheetBinding>(
    com.dev6.home.R.layout.fragment_delete_bottom_sheet) {
    //val writeViewModel : WriteViewModel by activityViewModels()
    private val myPageViewModel: MyPageViewModel by activityViewModels()
    lateinit var challengeReviewResult : ChallengeReviewResult
    lateinit var content : Content
    var isChallenge = false

    override fun initView() {
        isChallenge =  this.tag == "CHALLENGE"
        Log.v("SAdfsdf" , isChallenge.toString())
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

    override fun initListener() {
        binding.apply {
            deleteButton.setOnClickListener {
                if(isChallenge){
                    lifecycleScope.launch{
                        myPageViewModel.challengeDelete(challengeReviewResult.challengeReviewId)
                    }
                    dismiss()
                }else{
                    lifecycleScope.launch {
                        myPageViewModel.deletePost(content.postId)
                    }
                    dismiss()
                }
            }
            deleteCancelButton.setOnClickListener {
                dismiss()
            }
        }
    }
}