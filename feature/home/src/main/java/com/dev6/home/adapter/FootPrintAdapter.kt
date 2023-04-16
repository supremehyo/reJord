package com.dev6.home.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.ColorInt
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dev6.designsystem.CustomDecoration
import com.dev6.home.databinding.BadgeItemBinding
import com.dev6.home.databinding.FootprintItemBinding

class FootPrintAdapter(private val callback: (String) -> Unit) :
    ListAdapter<String, FootPrintAdapter.RecommendViewHolder>(RecommendDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendViewHolder {
        val binding = FootprintItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecommendViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecommendViewHolder, position: Int) {
        holder.onBind(currentList[position])
        holder.getLayoutParams()
        holder.itemClickListener(currentList[position], callback)
    }

    class RecommendViewHolder(private val binding: FootprintItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: String) {

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

@BindingAdapter(
    value = ["dividerHeight", "dividerPadding", "dividerColor"],
    requireAll = false
)
fun RecyclerView.setDivider(dividerHeight: Float?, dividerPadding: Float?, @ColorInt dividerColor: Int?) {
    val decoration = CustomDecoration(
        height = dividerHeight ?: 0f,
        padding = dividerPadding ?: 0f,
        color = dividerColor ?: Color.TRANSPARENT
    )

    addItemDecoration(decoration)
}
