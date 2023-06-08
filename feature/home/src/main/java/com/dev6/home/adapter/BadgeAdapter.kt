package com.dev6.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dev6.domain.model.mypage.BadgeByUidResult
import com.dev6.home.R
import com.dev6.home.databinding.BadgeItemBinding

class BadgeAdapter(private val callback: (BadgeByUidResult) -> Unit) :
    ListAdapter<BadgeByUidResult, BadgeAdapter.RecommendViewHolder>(RecommendDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendViewHolder {
        val binding = BadgeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecommendViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecommendViewHolder, position: Int) {
        holder.onBind(currentList[position])
        holder.getLayoutParams()
        holder.itemClickListener(currentList[position], callback)
    }

    class RecommendViewHolder(private val binding: BadgeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: BadgeByUidResult) {
            binding.badgeNameTv.text = item.badgeName
            Glide.with(itemView.context).load(item.imageUrl).circleCrop().error(R.drawable.main_icon).into(binding.badgeImageIv)
            //이미지 데이터 받아오면 glide 로 set
        }

        fun getLayoutParams(): ViewGroup.LayoutParams {
            return binding.root.layoutParams
        }

        fun itemClickListener(item: BadgeByUidResult, callback: (BadgeByUidResult) -> Unit) {
        }
    }

    private class RecommendDiffUtil : DiffUtil.ItemCallback<BadgeByUidResult>() {
        override fun areItemsTheSame(oldItem: BadgeByUidResult, newItem: BadgeByUidResult) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: BadgeByUidResult, newItem: BadgeByUidResult) =
            oldItem == newItem
    }
}
