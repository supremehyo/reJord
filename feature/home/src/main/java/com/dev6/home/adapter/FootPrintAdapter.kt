package com.dev6.home.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorInt
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dev6.designsystem.CustomDecoration
import com.dev6.domain.model.mypage.FootPrintRes
import com.dev6.domain.model.mypage.FootPrintResult
import com.dev6.home.databinding.BadgeItemBinding
import com.dev6.home.databinding.FootprintItemBinding
import com.dev6.home.databinding.MoreItemBinding

class FootPrintAdapter(
    private val callback: (FootPrintResult) -> Unit,
    private val getMore: (Int) -> Unit
) :
    ListAdapter<FootPrintResult, RecyclerView.ViewHolder>(RecommendDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var holder: RecyclerView.ViewHolder
        when (viewType) {
            0 -> {
                holder = FootPrintViewHolder(
                    binding = FootprintItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
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
                holder = FootPrintViewHolder(
                    binding = FootprintItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }
        return holder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.itemViewType == 1) {
            var moreViewHolder : MoreViewHolder = holder as MoreViewHolder
            moreViewHolder.bind(position)
        } else {
            var footPrintViewHolder: FootPrintViewHolder = holder as FootPrintViewHolder
            footPrintViewHolder.onBind(currentList[position])
            footPrintViewHolder.getLayoutParams()
            footPrintViewHolder.itemClickListener(currentList[position], callback)
        }
    }


    inner class MoreViewHolder(private val binding: MoreItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            if(currentList.size == 0){
                //binding.moreText.text = "탄소 발자국을 만들지 않았습니다"
            }
            binding.moreClick.setOnClickListener {
                getMore(position)
            }
        }
    }


    class FootPrintViewHolder(private val binding: FootprintItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: FootPrintResult) {
            binding.footprintDetailTv.text = "${item.createdDate[0]}.${item.createdDate[1]}.${item.createdDate[2]} | 탄소발자국 ${item.footprintAmount}걸음 | ${item.footprintAcquirementType}"
            binding.footprintTitleTv.text = item.title
        }

        fun getLayoutParams(): ViewGroup.LayoutParams {
            return binding.root.layoutParams
        }

        fun itemClickListener(item: FootPrintResult, callback: (FootPrintResult) -> Unit) {
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (position == currentList.size) {
            return 1
        } else {
            return 0
        }
    }

    override fun getItemCount(): Int {
        return currentList.size +1
    }


    private class RecommendDiffUtil : DiffUtil.ItemCallback<FootPrintResult>() {
        override fun areItemsTheSame(oldItem: FootPrintResult, newItem: FootPrintResult) =
            oldItem.footprintId == newItem.footprintId

        override fun areContentsTheSame(oldItem: FootPrintResult, newItem: FootPrintResult) =
            oldItem.badgeCode == newItem.badgeCode
    }
}

@BindingAdapter(
    value = ["dividerHeight", "dividerPadding", "dividerColor"],
    requireAll = false
)
fun RecyclerView.setDivider(
    dividerHeight: Float?,
    dividerPadding: Float?,
    @ColorInt dividerColor: Int?
) {
    val decoration = CustomDecoration(
        height = dividerHeight ?: 0f,
        padding = dividerPadding ?: 0f,
        color = dividerColor ?: Color.TRANSPARENT
    )

    addItemDecoration(decoration)
}
