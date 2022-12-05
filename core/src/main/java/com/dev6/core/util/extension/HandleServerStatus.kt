package com.dev6.core.util.extension

interface HandleServerStatus {

    //성공
    fun isSuccess()
    //실패
    fun isServerFail(): HandleServerResult

    //권한없음
    fun isNotAutority(): HandleServerResult

    //이미 존재
    fun isAleadyExit(): HandleServerResult

    //옳지 않다
    fun isNotCorrect(): HandleServerResult

}

data class HandleServerResult(val message: String, val status: Boolean)