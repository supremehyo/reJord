package com.dev6.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dev6.home.databinding.BadgeItemBinding

class BadgeAdapter(private val callback: (String) -> Unit) :
    ListAdapter<String, BadgeAdapter.RecommendViewHolder>(RecommendDiffUtil()) {

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
        fun onBind(item: String) {
            binding.badgeNameTv.text = item
            //이미지 데이터 받아오면 glide 로 set
        }

        fun getLayoutParams(): ViewGroup.LayoutParams {
            return binding.root.layoutParams
        }

        fun itemClickListener(item: String, callback: (String) -> Unit) {
        }
    }

    private class RecommendDiffUtil : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: String, newItem: String) =
            oldItem == newItem
    }
}
