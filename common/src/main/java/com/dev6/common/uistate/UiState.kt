package com.dev6.common.uistate

sealed class UiState<out T : Any> {
    object Loding : UiState<Nothing>()
    data class Success<T : Any>(val data : T) : UiState<T>()
    data class Error(val error: Throwable?) : UiState<Nothing>()
}