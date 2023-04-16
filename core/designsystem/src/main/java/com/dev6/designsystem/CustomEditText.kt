package com.dev6.designsystem
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.dev6.designsystem.databinding.CustomEditTextBinding


class CustomEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var binding: CustomEditTextBinding

    init {
        binding = CustomEditTextBinding.inflate(LayoutInflater.from(context), this)
    }


    fun setHint(hint: String) {
        binding.editText.hint = "이메일 주소"
    }



}