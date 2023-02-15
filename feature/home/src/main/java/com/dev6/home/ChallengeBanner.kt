package com.dev6.home

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
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

      binding.apply {

      }

      /*
      이런식으로 게터세터를 만들어서 값 동적제어
      public boolean isShowText() {
   return mShowText;
}

public void setShowText(boolean showText) {
   mShowText = showText;
   invalidate();
   requestLayout();
}

       */
   }

}