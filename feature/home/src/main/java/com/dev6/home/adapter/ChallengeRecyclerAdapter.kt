package com.dev6.home.adapter
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dev6.core.util.formatTimeString
import com.dev6.domain.model.challenge.ChallengeReviewResult
import com.dev6.home.databinding.ChallengeItemBinding
import com.dev6.home.databinding.MoreItemBinding
import com.dev6.home.databinding.PostItemBinding

class ChallengeRecyclerAdapter(
    private val items : List<ChallengeReviewResult?>,
    private val itemClick: (ChallengeReviewResult) -> Unit,
    private val getMore: (Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    inner class ChallengeViewHolder(private val binding: ChallengeItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ChallengeReviewResult?) {
            if(item != null){
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
            }
        }
    }

    //미리 만들어진 뷰홀더가 없는 경우 새로 생성하는 함수(레이아웃 생성)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
    :  RecyclerView.ViewHolder {
        var holder: RecyclerView.ViewHolder
        when (viewType) {
            0 -> {
                Log.v("afewfwef22", "포스트")
                holder = ChallengeViewHolder(
                    binding = ChallengeItemBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                    )
                )
            }
            1 -> {
                holder = MoreViewHolder(
                    binding = MoreItemBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                    )
                )
            }
            else -> {
                holder = ChallengeViewHolder(
                    binding = ChallengeItemBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                    )
                )
            }
        }
        return holder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder.itemViewType == 1){
            var moreViewHolder : ChallengeRecyclerAdapter.MoreViewHolder = holder as MoreViewHolder
            moreViewHolder.bind(position)
        }else{
            var challengeViewHolder : ChallengeRecyclerAdapter.ChallengeViewHolder = holder as ChallengeViewHolder
            challengeViewHolder.bind(items[position] ?: null)
        }
    }

    override fun getItemViewType(position: Int): Int {
        if(position == items.size){
            return 1
        }
        else{
            return 0
        }
    }

    inner class MoreViewHolder(private val binding: MoreItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.moreClick.setOnClickListener {
                getMore(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size +1
    }

}