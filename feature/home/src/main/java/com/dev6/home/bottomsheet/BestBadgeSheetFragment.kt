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
import com.dev6.home.databinding.FragmentBadgeBottomSheetBinding
import com.dev6.home.databinding.FragmentDeleteBottomSheetBinding
import com.dev6.home.databinding.FragmentOptionBottomSheetBinding
import com.dev6.write.R
import com.dev6.write.WriteFragment
import com.dev6.write.fragment.ChallengeEditFragment
import com.dev6.write.fragment.PostEditFragment
import com.dev6.write.viewmodel.WriteViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.cancel


class BestBadgeSheetFragment : BaseBottomSheetDialogFragment<FragmentBadgeBottomSheetBinding>(
    com.dev6.home.R.layout.fragment_badge_bottom_sheet) {

    override fun initView() {

    }

    override fun initListener() {
        binding.apply {

        }
    }
}