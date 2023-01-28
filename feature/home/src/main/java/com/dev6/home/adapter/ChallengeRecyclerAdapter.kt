package com.dev6.home.adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dev6.home.databinding.ChallengeItemBinding
import com.dev6.model.challenge.ChallengeResDTO


class ChallengeRecyclerAdapter(
    private val itemClick: (String) -> Unit,
) : ListAdapter<ChallengeResDTO, ChallengeRecyclerAdapter.ChallengeViewHolder>(
    diffUtil
) {
    private val VIEW_TYPE_ITEM = 0
    private val VIEW_TYPE_LOADING = 1


    //뷰홀더: 내가 넣고자하는 data를 실제 레이아웃의 데이터로 연결시키는 기능
    inner class ChallengeViewHolder(private val binding: ChallengeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ChallengeResDTO) {
            //binding.item = item
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
        val diffUtil = object : DiffUtil.ItemCallback<ChallengeResDTO>() {
            override fun areContentsTheSame(oldItem: ChallengeResDTO, newItem: ChallengeResDTO) =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: ChallengeResDTO, newItem: ChallengeResDTO) =
                oldItem.number == newItem.number
        }
    }


}