package com.dev6.model
import android.util.Log
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
        Log.v("코드" , code().toString())
        if(code() == 403){
            DefaultHandleServerStatus(Error("인증실패") , code())
        }else{
            DefaultHandleServerStatus(errorResponse.error , code())
        }

    }

    return body().executeErrorHandling(handle)
}
