package com.dev6.home
import androidx.lifecycle.ViewModel
import com.dev6.domain.usecase.HomeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel  @Inject constructor(
    private val homeUseCase: HomeUseCase
) : ViewModel() {


}