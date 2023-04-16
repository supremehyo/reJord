package com.dev6.write.fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.fragment.app.activityViewModels
import com.dev6.core.enums.WriteType
import com.dev6.write.R
import com.dev6.write.viewmodel.WriteViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class WriteBottomSheetFragment : BottomSheetDialogFragment() {
    val writeFragment : WriteViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_write_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var radioGroup = view.findViewById<RadioGroup>(R.id.writeBottomSheetRadioGroup)

        when(writeFragment.getCateGoryValue() ?: WriteType.SHARE){
            WriteType.SHARE->{
                radioGroup.check(R.id.shareRadio)
            }
            WriteType.CHALLENGE->{
                radioGroup.check(R.id.challRadio)
            }
            WriteType.ETC->{
                radioGroup.check(R.id.etcRadio)
            }
        }


        radioGroup.setOnCheckedChangeListener {
                _, checkedId->
            when(checkedId){
                R.id.shareRadio ->{ writeFragment.updateCateGoryValue(WriteType.SHARE) }
                R.id.challRadio ->{writeFragment.updateCateGoryValue(WriteType.CHALLENGE)}
                R.id.etcRadio ->{writeFragment.updateCateGoryValue(WriteType.ETC)}
            }
            dismiss()
        }
    }
}