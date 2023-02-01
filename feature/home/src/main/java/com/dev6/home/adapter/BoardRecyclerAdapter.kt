package com.dev6.home.adapter
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dev6.core.util.formatTimeString
import com.dev6.domain.model.post.read.Content
import com.dev6.domain.model.post.read.PostReadRes
import com.dev6.home.databinding.PostItemBinding


class BoardRecyclerAdapter(
    private val itemClick: (Content) -> Unit,
) : ListAdapter<Content, BoardRecyclerAdapter.PostViewHolder>(
    diffUtil
) {
    private val VIEW_TYPE_ITEM = 0
    private val VIEW_TYPE_LOADING = 1


    //뷰홀더: 내가 넣고자하는 data를 실제 레이아웃의 데이터로 연결시키는 기능
    inner class PostViewHolder(private val binding: PostItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Content) {
            Log.v("dsfsdfsf" , item.contents)

            //시간 차 구하기
            var timeDiff = formatTimeString(
                item.createdDate[0],
                item.createdDate[1],
                item.createdDate[2],
                item.createdDate[3],
                item.createdDate[4]
            )

            binding.apply {
                postItemUserNickName.text = item.nickname
                postItemCreateTime.text =
                    "${item.createdDate[0]}.${item.createdDate[1]}.${item.createdDate[2]}"+"|"+timeDiff

                itemMainContent
                    .setAnimationDuration(500)
                    .setReadMoreText("\n\n...더보기")
                    .setReadLessText("\n\n...접기")
                    .setCollapsedLines(3)
                    .setIsExpanded(true)
                    .setIsUnderlined(true)
                itemMainContent.text = item.contents
                itemMainContentMore.setOnClickListener {

                }
                postItemRootCl.setOnClickListener {
                    itemClick(item)
                }
            }
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
        val diffUtil = object : DiffUtil.ItemCallback<Content>() {
            override fun areContentsTheSame(oldItem: Content, newItem: Content) =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: Content, newItem: Content) =
                oldItem.postId == newItem.postId
        }
    }


}