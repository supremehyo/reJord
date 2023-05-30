package com.dev6.home.adapter

import android.content.Context
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dev6.core.util.formatTimeString
import com.dev6.domain.model.post.read.Content
import com.dev6.home.R
import com.dev6.home.bottomsheet.OptionBottomSheetFragment
import com.dev6.home.databinding.MoreItemBinding
import com.dev6.home.databinding.MypostItemBinding
import com.dev6.home.databinding.PostItemBinding
import com.dev6.write.fragment.WriteBottomSheetFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


@Suppress("IMPLICIT_CAST_TO_ANY")
class BoardRecyclerAdapter(
    private val type : String,
    private val items : List<Content?>,
    private val itemClick: (Content) -> Unit,
    private val getMore: (Int) -> Unit,
    private val getOption: (Content?) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    lateinit var bottomSheet : BottomSheetDialogFragment
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

                Glide.with(itemView.context).load("").circleCrop().error(R.drawable.main_icon) .into(binding.imageView)

                binding.itemMainContent.post {
                    if(binding.itemMainContent.layout.lineCount == 3) binding.mainContentMore.visibility = View.VISIBLE
                }

                binding.apply {
                    itemTypeTv.text = "카테고리 | ${item.postType}"
                    postItemUserNickName.text = item.nickname
                    postItemCreateTime.text =
                        "${item.createdDate[0]}.${item.createdDate[1]}.${item.createdDate[2]}" + "|" + timeDiff
                    itemMainContent.text = item.contents

                    mainContentMore.setOnClickListener {
                        if(mainContentMore.text == "접기"){
                            mainContentMore.text = "..더보기"
                            itemMainContent.setEllipsize(TextUtils.TruncateAt.END)
                            itemMainContent.maxLines = 3
                        }else{
                            mainContentMore.text = "접기"
                            itemMainContent.ellipsize = null
                            itemMainContent.maxLines = 100

                        }
                    }

                    postItemRootCl.setOnClickListener {
                        itemClick(item)
                    } }
            }
        }
    }

    inner class MyPostViewHolder(private val binding:MypostItemBinding) :
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

                binding.itemMainContent.post {
                    if(binding.itemMainContent.layout.lineCount == 3) binding.mainContentMore.visibility = View.VISIBLE
                }

                binding.apply {
                    itemTypeTv.text = "게시판 | ${item.postType}"
                    myPostDate.text =
                        "${item.createdDate[0]}.${item.createdDate[1]}.${item.createdDate[2]}" + " | " + timeDiff
                    itemMainContent.text = item.contents
                    if(item.contents.length > 13){
                        myPostTitleTv.text = item.contents.substring(0,13)+""
                    }else{
                        myPostTitleTv.text = item.contents
                    }
                    mainContentMore.setOnClickListener {
                        if(mainContentMore.text == "접기"){
                            mainContentMore.text = "..더보기"
                            itemMainContent.setEllipsize(TextUtils.TruncateAt.END)
                            itemMainContent.maxLines = 3
                        }else{
                            mainContentMore.text = "접기"
                            itemMainContent.ellipsize = null
                            itemMainContent.maxLines = 100

                        }
                    }
                }
            }
            binding.optionDot.setOnClickListener {
               getOption(item)
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
        if(type == "DEFAULT"){
            if(position == items.size){
                return 1
            }
            else{
                return 0
            }
        }else {
            if(position == items.size){
                return 1
            }
            else{
                return 2
            }
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
                holder = MyPostViewHolder(
                    binding = MypostItemBinding.inflate(
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
        }else if(holder.itemViewType == 0){
            var postViewHolder : PostViewHolder = holder as PostViewHolder
            postViewHolder.bind(items[position] ?: null)
        }else{
            val myPostViewHolder : MyPostViewHolder = holder as MyPostViewHolder
            myPostViewHolder.bind(items[position] ?:null)
        }
    }

    override fun getItemCount(): Int {
        return items.size +1
    }
}