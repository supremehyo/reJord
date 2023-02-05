package com.dev6.home.adapter
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dev6.core.util.formatTimeString
import com.dev6.domain.model.challenge.ChallengeReviewResult
import com.dev6.home.databinding.ChallengeItemBinding
import com.dev6.model.challenge.ChallengeResDTO


class ChallengeRecyclerAdapter(
    private val itemClick: (String) -> Unit,
) : ListAdapter<ChallengeReviewResult, ChallengeRecyclerAdapter.ChallengeViewHolder>(
    diffUtil
) {
    private val VIEW_TYPE_ITEM = 0
    private val VIEW_TYPE_LOADING = 1

    inner class ChallengeViewHolder(private val binding: ChallengeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ChallengeReviewResult) {
            Log.v("챌챌" , item.contents)
            //시간 차 구하기
            var timeDiff = formatTimeString(
                item.createdDate[0],
                item.createdDate[1],
                item.createdDate[2],
                item.createdDate[3],
                item.createdDate[4]
            )

            binding.itemMainContent.text = item.contents
            binding.challengeDate.text =
                "${item.createdDate[0]}.${item.createdDate[1]}.${item.createdDate[2]}"+" | "+timeDiff
            //binding.executePendingBindings()
        }
    }

    //미리 만들어진 뷰홀더가 없는 경우 새로 생성하는 함수(레이아웃 생성)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
    : ChallengeViewHolder {
        return ChallengeViewHolder(
            binding = ChallengeItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    //실제로 뷰홀더가 뷰에 그려졌을때 데이터를 뿌려주는 바인드해주는 함수(뷰홀더가 재활용될때 실행)
    override fun onBindViewHolder(holder: ChallengeViewHolder, position: Int) {
        val item = getItem(position) ?: return
        holder.bind(item)
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<ChallengeReviewResult>() {
            override fun areContentsTheSame(oldItem: ChallengeReviewResult, newItem: ChallengeReviewResult) =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: ChallengeReviewResult, newItem: ChallengeReviewResult) =
                oldItem.challengeReviewId == newItem.challengeReviewId
        }
    }


}