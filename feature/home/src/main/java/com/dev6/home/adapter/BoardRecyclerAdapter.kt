package com.dev6.home.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dev6.core.util.formatTimeString
import com.dev6.domain.model.post.read.Content
import com.dev6.home.databinding.MoreItemBinding
import com.dev6.home.databinding.PostItemBinding


@Suppress("IMPLICIT_CAST_TO_ANY")
class BoardRecyclerAdapter(
    private val items : List<Content?>,
    private val itemClick: (Content) -> Unit,
    private val getMore: (Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    //뷰홀더: 내가 넣고자하는 data를 실제 레이아웃의 데이터로 연결시키는 기능
    inner class PostViewHolder(private val binding: PostItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Content?) {
            //시간 차 구하기
            if(item != null){
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
                        "${item.createdDate[0]}.${item.createdDate[1]}.${item.createdDate[2]}" + "|" + timeDiff

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
                    } }
            }else{

            }
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

    override fun getItemViewType(position: Int): Int {
        if(position ==  items.size){
            return 1
        }
        else{
            return 0
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : RecyclerView.ViewHolder {
        var holder: RecyclerView.ViewHolder
        when (viewType) {
            0 -> {
                Log.v("afewfwef22" ,"포스트")
                holder = PostViewHolder(
                    binding = PostItemBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                    )
                )
            }
            1 -> {
                Log.v("afewfwef22" ,"몰")
                holder = MoreViewHolder(
                    binding = MoreItemBinding.inflate(
                        LayoutInflater.from(parent.context),parent,false
                    )
                )
            }
            else -> {
                holder = PostViewHolder(
                    binding = PostItemBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                    )
                )}
        }
        return holder
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder.itemViewType == 1){
            var moreViewHolder : MoreViewHolder = holder as MoreViewHolder
            moreViewHolder.bind(position)
        }else{
            var postViewHolder : PostViewHolder = holder as PostViewHolder
            postViewHolder.bind(items[position] ?: null)
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Content>() {
            override fun areContentsTheSame(oldItem: Content, newItem: Content) =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: Content, newItem: Content) =
                oldItem.postId == newItem.postId
        }
    }

    override fun getItemCount(): Int {
        return items.size +1
    }
}