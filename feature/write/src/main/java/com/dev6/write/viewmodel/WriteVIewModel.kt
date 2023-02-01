package com.dev6.write.viewmodel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dev6.core.enums.WriteType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WriteViewModel @Inject constructor(
) : ViewModel(){

    private val _categoryLiveData = MutableLiveData<WriteType>()
    val categoryLiveData : LiveData<WriteType>
        get() = _categoryLiveData

    init {
        _categoryLiveData.value = WriteType.SHARE
    }

    fun updateCateGoryValue(type : WriteType){
        _categoryLiveData.value = type
    }
}