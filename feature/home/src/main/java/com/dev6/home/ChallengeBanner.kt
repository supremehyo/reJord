package com.dev6.home

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.dev6.domain.model.challenge.ChallengeInfoRes
import com.dev6.home.databinding.ChallengeBannerItemBinding


class ChallengeBanner(
   context : Context,
   attrs : AttributeSet
) : ConstraintLayout(context, attrs) {

   private var binding: ChallengeBannerItemBinding =
      ChallengeBannerItemBinding.inflate(LayoutInflater.from(context),
         this, true)


   init {
      val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ChallengeBanner)
      val textColorInActive = typedArray.getColor(R.styleable.ChallengeBanner_textColorInActivedz ,0)
      val textColorActive = typedArray.getColor(R.styleable.ChallengeBanner_textColorActivedz ,0)
      val title = typedArray.getString(R.styleable.ChallengeBanner_titlez)
      val content = typedArray.getString(R.styleable.ChallengeBanner_contenzt)
      val imageURL = typedArray.getString(R.styleable.ChallengeBanner_imageURz)
   }


   @SuppressLint("ResourceType")
   fun setBannerLayout(challengeInfoRes: ChallengeInfoRes = ChallengeInfoRes(
      badgeCode = "",
      challengeId = "",
      contents = "",
      footprintAmount = "15",
      imgBack = "",
      imgFront = "",
      textColor = "",
      title = "타이틀"
   ))  {
      try {
         binding.normalBanner.visibility = View.VISIBLE
         binding.changedbannerFl.visibility = View.GONE
         binding.topText.text = challengeInfoRes.challengeId
         binding.bannerTitle.text = challengeInfoRes.title
         binding.bannerBackground.backgroundTintList=
            ColorStateList.valueOf(ContextCompat.getColor(context, Color.parseColor(challengeInfoRes.textColor)))
         binding.bannerContent.text = challengeInfoRes.contents
         Glide.with(context).load(challengeInfoRes.imgFront).into(binding.bannerImage)
      }catch (e : Exception){
         Log.e("bannerError" , e.message.toString())
      }
      invalidate()
      requestLayout()
   }




   //클릭시 배너 컬러 바꾸는 코드
   fun changeBanner(callback: ()->Unit , challengeInfoRes: ChallengeInfoRes){
      var color = challengeInfoRes.textColor
      if(binding.changedbannerFl.visibility != View.VISIBLE){
         binding.normalBanner.visibility = View.GONE
         binding.changedtopText.setTextColor(Color.parseColor(color))
         binding.changedbannerTitle.setTextColor(Color.parseColor(color))
         binding.changedbannerSubContent.setTextColor(Color.parseColor(color))
         binding.changedbannerContent.setTextColor(Color.parseColor(color))
         binding.changedbannerButton.backgroundTintList = ColorStateList.valueOf(Color.parseColor(color))
         val gd = binding.changedbannerBackground.background as GradientDrawable
         gd.setStroke(2, Color.parseColor(color))
         binding.changedbannerBackground.setBackgroundDrawable(gd)
         binding.changedbannerFl.visibility = View.VISIBLE
      }else{
         binding.changedbannerButton.setOnClickListener {
            if(binding.changedbannerFl.visibility == View.VISIBLE){
               callback()
            }
         }
      }
   }



   private fun setBannerTextColor(colorCode : String){
      binding.topText.setTextColor(Color.parseColor(colorCode))
      binding.bannerContent.setTextColor(Color.parseColor(colorCode))
      binding.bannerTitle.setTextColor(Color.parseColor(colorCode))
   }
}