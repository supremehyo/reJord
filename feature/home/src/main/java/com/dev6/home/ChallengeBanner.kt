package com.dev6.home

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.ColorSpace.Rgb
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContentProviderCompat.requireContext
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
   fun setBannerLayout(challengeInfoRes: ChallengeInfoRes)  {
      try {
         binding.topText.text = challengeInfoRes.challengeId
         binding.bannerTitle.text = challengeInfoRes.title
         binding.bannerBackground.backgroundTintList =
            ColorStateList.valueOf(ContextCompat.getColor(context, Color.parseColor("#000000")))
         binding.bannerContent.text = challengeInfoRes.contents
         setBannerTextColor(challengeInfoRes.textColor)
         Glide.with(context)
            .load(challengeInfoRes.imgFront)
            .into(binding.bannerImage)
      }catch (e : Exception){
         Log.e("bannerError" , e.message.toString())
      }
      invalidate()
      requestLayout()
   }

   private fun setBannerTextColor(colorCode : String){
      binding.topText.setTextColor(Color.parseColor(colorCode))
      binding.bannerContent.setTextColor(Color.parseColor(colorCode))
      binding.bannerTitle.setTextColor(Color.parseColor(colorCode))
   }
}