package com.dev6.home.bottomsheet
import com.dev6.core.BaseBottomSheetDialogFragment
import com.dev6.home.databinding.FragmentBadgeBottomSheetBinding
import com.dev6.home.databinding.FragmentWithdrawalBottomSheetBinding


class WithdrawalSheetFragment : BaseBottomSheetDialogFragment<FragmentWithdrawalBottomSheetBinding>(
    com.dev6.home.R.layout.fragment_withdrawal_bottom_sheet) {

    override fun initView() {

    }

    override fun initListener() {
        binding.apply {
            okButton.setOnClickListener {
                //회원탈퇴 api 호출
            }
            cancelButton.setOnClickListener {
                dismiss()
            }
        }
    }
}