package com.dev6.home.adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dev6.domain.model.post.read.PostReadRes
import com.dev6.home.databinding.PostItemBinding


class BoardRecyclerAdapter(
    private val itemClick: (PostReadRes) -> Unit,
) : ListAdapter<PostReadRes, BoardRecyclerAdapter.PostViewHolder>(
    diffUtil
) {
    private val VIEW_TYPE_ITEM = 0
    private val VIEW_TYPE_LOADING = 1


    //뷰홀더: 내가 넣고자하는 data를 실제 레이아웃의 데이터로 연결시키는 기능
    inner class PostViewHolder(private val binding: PostItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PostReadRes) {
            //binding.item = item
            //binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : PostViewHolder {
        return PostViewHolder(
            binding = PostItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val item = getItem(position) ?: return
        holder.bind(item)
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<PostReadRes>() {
            override fun areContentsTheSame(oldItem: PostReadRes, newItem: PostReadRes) =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: PostReadRes, newItem: PostReadRes) =
                oldItem.number == newItem.number
        }
    }


}