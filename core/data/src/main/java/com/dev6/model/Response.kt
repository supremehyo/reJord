package com.dev6.model
import com.dev6.network.DefaultHandleServerStatus
import com.google.gson.Gson
import com.jydev.rest_api_util.extension.executeErrorHandling
import retrofit2.Response

data class ErrorResponse(
    val error: Error
)

data class Error(
    val message: String
)


suspend fun <T> Response<T>.executeNetworkHandling(): T {
    val handle = if (isSuccessful) {
        null
    } else {
        val gson = Gson()
        val errorBody = errorBody()!!.string()
        val errorResponse = gson.fromJson(errorBody, ErrorResponse::class.java)
        DefaultHandleServerStatus(errorResponse.error)
    }

    return body().executeErrorHandling(handle)
}
